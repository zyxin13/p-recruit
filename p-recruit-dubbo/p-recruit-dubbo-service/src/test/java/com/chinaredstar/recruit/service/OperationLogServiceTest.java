package com.chinaredstar.recruit.service;

import com.chinaredstar.recruit.api.common.ServiceResult;
import com.chinaredstar.recruit.api.service.OperationLogService;
import com.chinaredstar.recruit.api.vo.OperationLogVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yuxin.zou on 2017/11/22.
 */
public class OperationLogServiceTest extends BaseTest {
    @Autowired
    private OperationLogService operationLogService;

    @Test
    public void testSelectByPrimaryKey() {
        ServiceResult<OperationLogVo> serviceResult = operationLogService.selectByPrimaryKey(540);
        Assert.assertTrue(serviceResult.isSuccess());
    }

}
