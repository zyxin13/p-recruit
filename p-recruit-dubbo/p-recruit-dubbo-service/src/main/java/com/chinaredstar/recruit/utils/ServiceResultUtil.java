package com.chinaredstar.recruit.utils;

import com.chinaredstar.recruit.api.common.ServiceResult;

import java.io.Serializable;

/**
 * Created by yuxin.zou on 2017/11/22.
 */
public class ServiceResultUtil implements Serializable {
    private static final long serialVersionUID = 6638805453017891791L;

    /**
     * 没有返回数据的成功结果
     * @return
     */
    public static ServiceResult success() {
        ServiceResult result = new ServiceResult();
        result.setSuccess(true);
        return result;
    }

    /**
     * 返回数据的成功结果
     * @return
     */
    public static <T> ServiceResult<T> success(T data) {
        ServiceResult<T> result = new ServiceResult<>();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    /**
     * 返回消息的失败结果
     * @return
     */
    public static ServiceResult error(String message) {
        ServiceResult result = new ServiceResult();
        result.setMessage(message);
        result.setSuccess(false);
        return result;
    }

    /**
     * 返回默认消息的失败结果
     * @return
     */
    public static ServiceResult error() {
        ServiceResult result = new ServiceResult();
        result.setMessage("error");
        result.setSuccess(false);
        return result;
    }
}
