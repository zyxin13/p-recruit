package com.chinaredstar.recruit.api.service;

import com.chinaredstar.recruit.api.common.ServiceResult;
import com.chinaredstar.recruit.api.vo.PushDataLogVo;

/**
 * Created by yuxin.zou on 2017/11/23.
 */
public interface PushDataLogService {
    ServiceResult<Boolean> deleteByPrimaryKey(Integer id);

    ServiceResult<Boolean> insertSelective(PushDataLogVo record);

    ServiceResult<PushDataLogVo> selectByPrimaryKey(Integer id);

    ServiceResult<Boolean> updateByPrimaryKeySelective(PushDataLogVo record);
}
