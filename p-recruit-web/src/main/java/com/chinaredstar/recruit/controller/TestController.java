package com.chinaredstar.recruit.controller;

import com.chinaredstar.recruit.api.common.ServiceResult;
import com.chinaredstar.recruit.api.service.OperationLogService;
import com.chinaredstar.recruit.api.vo.OperationLogVo;
import com.chinaredstar.recruit.common.Result;
import com.chinaredstar.recruit.common.ResultCode;
import com.chinaredstar.recruit.utils.ResultUtil;
import com.wordnik.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yuxin.zou on 2017/11/22.
 */
@RestController
@RequestMapping("/test")
@Api(value = "test", description = "测试页面")
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private OperationLogService operationLogService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "返回数据为空"),
            @ApiResponse(code = 415, message = "请求参数错误"),
            @ApiResponse(code = 422, message = "校验错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @ApiOperation(value = "getOperationLogById", notes = "根据id获取操作日志信息")
    @RequestMapping(value = "/operationLog/{id}", method = RequestMethod.GET)
    public Result<OperationLogVo> getOperationLogById(@ApiParam("操作日志id") @PathVariable("id") Integer id) {
        if (id == null) {
            return ResultUtil.error(ResultCode.C415, "操作日志ID不能为空");
        }
        try {
            ServiceResult<OperationLogVo> serviceResult = operationLogService.selectByPrimaryKey(id);
            if (serviceResult.isSuccess()) {
                return ResultUtil.success(serviceResult.getData());
            } else {
                return ResultUtil.error(ResultCode.C500, serviceResult.getMessage());
            }
        } catch (Exception e) {
            LOGGER.error("根据id{id}获取操作日志信息异常", id, e);
            return ResultUtil.error(ResultCode.C500, "根据id获取操作日志信息异常");
        }
    }

}
