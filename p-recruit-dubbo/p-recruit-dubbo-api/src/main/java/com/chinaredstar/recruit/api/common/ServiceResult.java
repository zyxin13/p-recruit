package com.chinaredstar.recruit.api.common;

import java.io.Serializable;


/**
 * @author yuxin.zou
 */
public class ServiceResult<T> implements Serializable {
    private static final long serialVersionUID = 1112149670302251011L;

    /**
     * 是否成功获取数据
     */
    private final Boolean success;

    /**
     * 具体详细msg
     */
    private final String message;

    /**
     * 服务端返回数据
     */
    private final T data;

    private ServiceResult(Boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    /**
     * 没有返回数据的成功结果
     *
     * @return
     */
    public static ServiceResult success() {
        return new ServiceResult(true, null, null);
    }

    /**
     * 返回数据的成功结果
     *
     * @return
     */
    public static <T> ServiceResult<T> success(T data) {
        return new ServiceResult<>(true, null, data);
    }

    /**
     * 返回消息的失败结果
     *
     * @return
     */
    public static ServiceResult error(String message) {
        return new ServiceResult(false, message, null);
    }

    /**
     * 返回默认消息的失败结果
     *
     * @return
     */
    public static ServiceResult error() {
        return new ServiceResult(false, "error", null);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ServiceResult [")
                .append("success=").append(this.success)
                .append(",message=").append(this.message)
                .append(",data=").append(this.data)
                .append("]");
        return builder.toString();
    }
}
