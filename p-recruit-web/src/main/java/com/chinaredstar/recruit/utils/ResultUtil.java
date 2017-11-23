package com.chinaredstar.recruit.utils;

import com.chinaredstar.recruit.common.Result;
import com.chinaredstar.recruit.common.ResultCode;

import java.io.Serializable;

/**
 * Created by yuxin.zou on 2017/11/22.
 */
public class ResultUtil implements Serializable {
    private static final long serialVersionUID = 3262813962838556084L;

    /**
     * 没有返回数据的成功结果
     *
     * @return
     */
    public static Result success() {
        Result result = new Result();
        result.setCode(ResultCode.C200.getCode());
        return result;
    }

    /**
     * 返回数据的成功结果
     *
     * @return
     */
    public static <T> Result<T> success(T value) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.C200.getCode());
        result.setValue(value);
        return result;
    }

    /**
     * 返回错误码和消息的失败结果
     *
     * @return
     */
    public static Result error(ResultCode errorCode, String message) {
        Result result = new Result();
        result.setMessage(message);
        result.setCode(errorCode.getCode());
        return result;
    }

    /**
     * 返回错误码和默认对应消息的失败结果
     *
     * @return
     */
    public static Result error(ResultCode errorCode) {
        Result result = new Result();
        result.setMessage(errorCode.getDesc());
        result.setCode(errorCode.getCode());
        return result;
    }

    /**
     * 返回500错误码和消息的失败结果
     *
     * @return
     */
    public static Result error(String message) {
        Result result = new Result();
        result.setMessage(message);
        result.setCode(ResultCode.C500.getCode());
        return result;
    }

    /**
     * 返回500错误码和默认消息的失败结果
     *
     * @return
     */
    public static Result error() {
        Result result = new Result();
        result.setMessage(ResultCode.C500.getDesc());
        result.setCode(ResultCode.C500.getCode());
        return result;
    }
}
