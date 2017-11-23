package com.chinaredstar.recruit.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.session.ExpiringSession;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author:杨果
 * @date:16/9/1 下午3:43
 * <p>
 * Description:
 */
final class RedisSessionExpirationPolicy2 {

    private static final Log logger = LogFactory.getLog(RedisSessionExpirationPolicy2.class);
    private static final String expiresSessionKey = "UC-MaxInactiveIntervalInSeconds";

    private final RedisOperations<Object, Object> redis;

    private final RedisOperationsSessionRepository2 redisSession;

    public RedisSessionExpirationPolicy2(
            RedisOperations<Object, Object> sessionRedisOperations, RedisOperationsSessionRepository2 redisSession) {
        super();
        this.redis = sessionRedisOperations;
        this.redisSession = redisSession;
    }

    static long expiresInMillis(ExpiringSession session) {
        Integer maxInactiveInSeconds = session.getAttribute(expiresSessionKey);
        if (maxInactiveInSeconds == null) {
            maxInactiveInSeconds = session.getMaxInactiveIntervalInSeconds();
        }
        if (session.getMaxInactiveIntervalInSeconds() == 0) {
            maxInactiveInSeconds = 0;
        }
        long lastAccessedTimeInMillis = session.getLastAccessedTime();
        return lastAccessedTimeInMillis + TimeUnit.SECONDS.toMillis(maxInactiveInSeconds);
    }

    static long roundUpToNextMinute(long timeInMs) {

        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(timeInMs);
        date.add(Calendar.MINUTE, 1);
        date.clear(Calendar.SECOND);
        date.clear(Calendar.MILLISECOND);
        return date.getTimeInMillis();
    }

    static long roundDownMinute(long timeInMs) {
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(timeInMs);
        date.clear(Calendar.SECOND);
        date.clear(Calendar.MILLISECOND);
        return date.getTimeInMillis();
    }

    public void onDelete(ExpiringSession session) {
        long toExpire = roundUpToNextMinute(expiresInMillis(session));
        String expireKey = getExpirationKey(toExpire);
        redis.boundSetOps(expireKey).remove("expires:" + session.getId());
    }

    public void onExpirationUpdated(Long originalExpirationTimeInMilli, ExpiringSession session) {
        String keyToExpire = "expires:" + session.getId();
        long toExpire = roundUpToNextMinute(expiresInMillis(session));

        if (originalExpirationTimeInMilli != null) {
            long originalRoundedUp = roundUpToNextMinute(originalExpirationTimeInMilli);
            if (toExpire != originalRoundedUp) {
                String expireKey = getExpirationKey(originalRoundedUp);
                redis.boundSetOps(expireKey).remove(keyToExpire);
            }
        }

        String expireKey = getExpirationKey(toExpire);
        BoundSetOperations<Object, Object> expireOperations = redis.boundSetOps(expireKey);
        expireOperations.add(keyToExpire);

        Integer sessionExpireInSeconds = session.getAttribute(expiresSessionKey);
        if (sessionExpireInSeconds == null) {
            sessionExpireInSeconds = session.getMaxInactiveIntervalInSeconds();
        }
        if (session.getMaxInactiveIntervalInSeconds() == 0) {
            sessionExpireInSeconds = 0;
        }
        long fiveMinutesAfterExpires = sessionExpireInSeconds + TimeUnit.MINUTES.toSeconds(5);
        String sessionKey = getSessionKey(keyToExpire);

        expireOperations.expire(fiveMinutesAfterExpires, TimeUnit.SECONDS);
        if (sessionExpireInSeconds == 0) {
            redis.delete(sessionKey);
        } else {
            redis.boundValueOps(sessionKey).append("");
            redis.boundValueOps(sessionKey).expire(sessionExpireInSeconds, TimeUnit.SECONDS);
        }
        redis.boundHashOps(getSessionKey(session.getId())).expire(fiveMinutesAfterExpires, TimeUnit.SECONDS);
    }

    String getExpirationKey(long expires) {
        return this.redisSession.getExpirationsKey(expires);
    }

    String getSessionKey(String sessionId) {
        return this.redisSession.getSessionKey(sessionId);
    }

    public void cleanExpiredSessions() {
        long now = System.currentTimeMillis();
        long prevMin = roundDownMinute(now);

        if (logger.isDebugEnabled()) {
            logger.debug("Cleaning up sessions expiring at " + new Date(prevMin));
        }

        String expirationKey = getExpirationKey(prevMin);
        Set<Object> sessionsToExpire = redis.boundSetOps(expirationKey).members();
        redis.delete(expirationKey);
        for (Object session : sessionsToExpire) {
            String sessionKey = getSessionKey((String) session);
            touch(sessionKey);
        }
    }

    /**
     * By trying to access the session we only trigger a deletion if it the TTL is expired. This is
     * done to handle https://github.com/spring-projects/spring-session/issues/93
     */
    private void touch(String key) {
        redis.hasKey(key);
    }

}
