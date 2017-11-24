package com.chinaredstar.recruit.service;

import com.chinaredstar.recruit.api.common.ServiceResult;
import com.chinaredstar.recruit.api.model.PushDataLog;
import com.chinaredstar.recruit.api.service.PushDataLogService;
import com.chinaredstar.recruit.api.vo.PushDataLogVo;
import com.chinaredstar.recruit.mapper.PushDataLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yuxin.zou on 2017/11/23.
 */
@Service("pushDataLogService")
public class PushDataLogServiceImpl implements PushDataLogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OperationLogServiceImpl.class);

    @Autowired
    private PushDataLogMapper pushDataLogMapper;

    @Override
    public ServiceResult<Boolean> deleteByPrimaryKey(Integer id) {
        if (id == null) {
            return ServiceResult.error("id不能为空");
        }
        try {
            pushDataLogMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            LOGGER.error("根据id删除推送数据日志失败{}", e);
            return ServiceResult.error("根据id删除操作日志失败");
        }
        return ServiceResult.success(true);
    }

    @Override
    public ServiceResult<Boolean> insertSelective(PushDataLogVo record) {
        if (record == null) {
            return ServiceResult.error("插入对象不能为空");
        }
        try {
            PushDataLog pushDataLog = new PushDataLog();
            BeanUtils.copyProperties(record, pushDataLog);
            pushDataLogMapper.insertSelective(pushDataLog);
        } catch (Exception e) {
            LOGGER.error("添加推送数据日志失败{}", e);
            return ServiceResult.error("添加推送数据日志失败");
        }
        return ServiceResult.success(true);
    }

    @Override
    public ServiceResult<PushDataLogVo> selectByPrimaryKey(Integer id) {
        if (id == null) {
            return ServiceResult.error("id不能为空");
        }
        PushDataLog pushDataLog;
        try {
            pushDataLog = pushDataLogMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            LOGGER.error("查询推送数据日志失败{}", e);
            return ServiceResult.error("查询推送数据日志失败");
        }
        if (pushDataLog == null) {
            return ServiceResult.success(null);
        }
        PushDataLogVo pushDataLogVo = new PushDataLogVo();
        BeanUtils.copyProperties(pushDataLog, pushDataLogVo);
        return ServiceResult.success(pushDataLogVo);
    }

    @Override
    public ServiceResult<Boolean> updateByPrimaryKeySelective(PushDataLogVo record) {
        if (record == null) {
            return ServiceResult.error("更新对象不能为空");
        }
        if (record.getId() == null) {
            return ServiceResult.error("id不能为空");
        }
        try {
            PushDataLog pushDataLog = new PushDataLog();
            BeanUtils.copyProperties(record, pushDataLog);
            pushDataLogMapper.updateByPrimaryKeySelective(pushDataLog);
        } catch (Exception e) {
            LOGGER.error("更新推送数据日志失败{}{}", record.getId(), e);
            return ServiceResult.error("更新推送数据日志失败");
        }
        return ServiceResult.success(true);
    }
}
