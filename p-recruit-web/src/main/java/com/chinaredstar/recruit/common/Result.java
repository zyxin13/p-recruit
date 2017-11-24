package com.chinaredstar.recruit.common;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author:杨果
 * @date:15/12/21 下午2:24
 * <p/>
 * Description:
 */
@ApiModel(value = "Rest result", description = "请求结果")
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -4696898674758059398L;
    @ApiModelProperty(value = "结果代码")
    private final Integer code;
    @ApiModelProperty(value = "错误消息，返回失败情况下填充")
    private final String message;
    @ApiModelProperty(value = "结果对象")
    private final T value;

    private Result(int code, String message, T value) {
        this.code = code;
        this.message = message;
        this.value = value;
    }

    /**
     * 没有返回数据的成功结果
     *
     * @return
     */
    public static <T> Result<T> success() {
        return new Result<>(ResultCode.C200.getCode(), null, null);
    }

    /**
     * 返回数据的成功结果
     *
     * @return
     */
    public static <T> Result<T> success(T value) {
        return new Result<>(ResultCode.C200.getCode(), null, value);
    }

    /**
     * 返回错误码和消息的失败结果
     *
     * @return
     */
    public static <T> Result<T> error(ResultCode errorCode, String message) {
        return new Result<>(errorCode.getCode(), message, null);
    }

    /**
     * 返回错误码和默认对应消息的失败结果
     *
     * @return
     */
    public static <T> Result<T> error(ResultCode errorCode) {
        return new Result<>(errorCode.getCode(), errorCode.getDesc(), null);
    }

    /**
     * 返回500错误码和消息的失败结果
     *
     * @return
     */
    public static <T> Result<T> error(String message) {
        return new Result<>(ResultCode.C500.getCode(), message, null);
    }

    /**
     * 返回500错误码和默认消息的失败结果
     *
     * @return
     */
    public static <T> Result<T> error() {
        return new Result<>(ResultCode.C500.getCode(), ResultCode.C500.getDesc(), null);
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getValue() {
        return value;
    }
}
