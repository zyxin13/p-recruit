package com.chinaredstar.recruit.api.service;

import com.chinaredstar.recruit.api.common.ServiceResult;
import com.chinaredstar.recruit.api.vo.OperationLogVo;

/**
 * Created by yuxin.zou on 2017/11/22.
 */
public interface OperationLogService {
    ServiceResult<Boolean> deleteByPrimaryKey(Integer id);

    ServiceResult<Boolean> insertSelective(OperationLogVo record);

    ServiceResult<OperationLogVo> selectByPrimaryKey(Integer id);

    ServiceResult<Boolean> updateByPrimaryKeySelective(OperationLogVo record);
}
