package com.chinaredstar.recruit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.Session;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionManager;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.session.web.http.MultiHttpSessionStrategy;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:杨果
 * @date:16/8/26 下午2:26
 *
 * Description:
 *
 * session id cookie和header都返回
 */
@Configuration
public class CustomHttpSessionStrategy implements HttpSessionStrategy, MultiHttpSessionStrategy, HttpSessionManager {
    private HeaderHttpSessionStrategy headerHttpSessionStrategy = new HeaderHttpSessionStrategy();
    public CookieHttpSessionStrategy cookieHttpSessionStrategy = new CookieHttpSessionStrategy();

    @Override
    public String getRequestedSessionId(HttpServletRequest request) {
        if (headerHttpSessionStrategy.getRequestedSessionId(request) != null) {
            return headerHttpSessionStrategy.getRequestedSessionId(request);
        } else {
            return cookieHttpSessionStrategy.getRequestedSessionId(request);
        }
    }

    @Override
    public void onNewSession(Session session, HttpServletRequest request, HttpServletResponse response) {
        headerHttpSessionStrategy.onNewSession(session, request, response);
        cookieHttpSessionStrategy.onNewSession(session, request, response);
    }

    @Override
    public void onInvalidateSession(HttpServletRequest request, HttpServletResponse response) {
        headerHttpSessionStrategy.onInvalidateSession(request, response);
        cookieHttpSessionStrategy.onInvalidateSession(request, response);
    }

    @Override
    public String getCurrentSessionAlias(HttpServletRequest request) {
        return cookieHttpSessionStrategy.getCurrentSessionAlias(request);
    }

    @Override
    public Map<String, String> getSessionIds(HttpServletRequest request) {
        return cookieHttpSessionStrategy.getSessionIds(request);
    }

    @Override
    public String encodeURL(String url, String sessionAlias) {
        return cookieHttpSessionStrategy.encodeURL(url, sessionAlias);
    }

    @Override
    public String getNewSessionAlias(HttpServletRequest request) {
        return cookieHttpSessionStrategy.getNewSessionAlias(request);
    }

    @Override
    public HttpServletRequest wrapRequest(HttpServletRequest request, HttpServletResponse response) {
        return cookieHttpSessionStrategy.wrapRequest(request, response);
    }

    @Override
    public HttpServletResponse wrapResponse(HttpServletRequest request, HttpServletResponse response) {
        return cookieHttpSessionStrategy.wrapResponse(request, response);
    }

    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("SESSION");
        serializer.setCookiePath("/");
        serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");

        CustomHttpSessionStrategy customHttpSessionStrategy = new CustomHttpSessionStrategy();
        customHttpSessionStrategy.cookieHttpSessionStrategy.setCookieSerializer(serializer);
        return customHttpSessionStrategy;
    }
}
