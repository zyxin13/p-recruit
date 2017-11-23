package com.chinaredstar.recruit.service;

import com.chinaredstar.recruit.api.common.ServiceResult;
import com.chinaredstar.recruit.api.service.PushDataLogService;
import com.chinaredstar.recruit.api.vo.PushDataLogVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yuxin.zou on 2017/11/23.
 */
public class PushDataLogServiceTest extends BaseTest {
    @Autowired
    private PushDataLogService pushDataLogService;

    @Test
    public void testSelectByPrimaryKey() {
        ServiceResult<PushDataLogVo> serviceResult = pushDataLogService.selectByPrimaryKey(430);
        Assert.assertTrue(serviceResult.isSuccess());
    }
}
