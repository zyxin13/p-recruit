package com.chinaredstar.recruit.service;

import com.chinaredstar.recruit.api.common.ServiceResult;
import com.chinaredstar.recruit.api.model.OperationLog;
import com.chinaredstar.recruit.api.service.OperationLogService;
import com.chinaredstar.recruit.api.vo.OperationLogVo;
import com.chinaredstar.recruit.mapper.OperationLogMapper;
import com.chinaredstar.recruit.utils.ServiceResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yuxin.zou on 2017/11/22.
 */
@Service("operationLogService")
public class OperationLogServiceImpl implements OperationLogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OperationLogServiceImpl.class);

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Override
    public ServiceResult<Boolean> deleteByPrimaryKey(Integer id) {
        if (id == null) {
            return ServiceResultUtil.error("id不能为空");
        }
        try {
            operationLogMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            LOGGER.error("根据id删除操作日志失败{}", e);
            return ServiceResultUtil.error("根据id删除操作日志失败");
        }
        return ServiceResultUtil.success(true);
    }

    @Override
    public ServiceResult<Boolean> insertSelective(OperationLogVo record) {
        if (record == null) {
            return ServiceResultUtil.error("插入对象不能为空");
        }
        try {
            OperationLog operationLog = new OperationLog();
            BeanUtils.copyProperties(record, operationLog);
            operationLogMapper.insertSelective(operationLog);
        } catch (Exception e) {
            LOGGER.error("添加操作日志失败{}", e);
            return ServiceResultUtil.error("添加操作日志失败");
        }
        return ServiceResultUtil.success(true);
    }

    @Override
    public ServiceResult<OperationLogVo> selectByPrimaryKey(Integer id) {
        if (id == null) {
            return ServiceResultUtil.error("id不能为空");
        }
        OperationLog operationLog;
        try {
            operationLog = operationLogMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            LOGGER.error("查询操作日志失败{}", e);
            return ServiceResultUtil.error("查询操作日志失败");
        }
        if (operationLog == null) {
            return ServiceResultUtil.success(null);
        }
        OperationLogVo operationLogVo = new OperationLogVo();
        BeanUtils.copyProperties(operationLog, operationLogVo);
        return ServiceResultUtil.success(operationLogVo);
    }

    @Override
    public ServiceResult<Boolean> updateByPrimaryKeySelective(OperationLogVo record) {
        if (record == null) {
            return ServiceResultUtil.error("更新对象不能为空");
        }
        if (record.getId() == null) {
            return ServiceResultUtil.error("id不能为空");
        }
        try {
            OperationLog operationLog = new OperationLog();
            BeanUtils.copyProperties(record, operationLog);
            operationLogMapper.updateByPrimaryKeySelective(operationLog);
        } catch (Exception e) {
            LOGGER.error("更新操作日志失败{}{}", record.getId(), e);
            return ServiceResultUtil.error("更新操作日志失败");
        }
        return ServiceResultUtil.success(true);
    }
}
