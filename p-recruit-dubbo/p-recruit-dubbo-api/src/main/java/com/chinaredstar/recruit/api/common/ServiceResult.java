package com.chinaredstar.recruit.api.common;

import java.io.Serializable;


/**
 * @author yuxin.zou
 */
public class ServiceResult<T> implements Serializable{
    private static final long serialVersionUID = 1112149670302251011L;

    /** 是否成功获取数据 */

    private boolean success;

    /** 具体详细msg */
    private String message;

    /** 服务端返回数据 */
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ServiceResult [")
                .append("isSuccess=").append(isSuccess())
                .append(",msg=").append(getMessage())
                .append(",data=").append(getData())
                .append("]");
        return builder.toString();
    }
}
