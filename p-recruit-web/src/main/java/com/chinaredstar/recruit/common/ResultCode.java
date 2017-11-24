package com.chinaredstar.recruit.common;

/**
 * @author:杨果
 * @date:16/3/10 上午9:29
 * <p/>
 * Description:
 * <p/>
 * REST接口返回的结果状态码,这些结果状态码参照HTTP协议
 */
public enum ResultCode {
    /**
     * 请求成功
     */
    C200(200, "请求成功"),
    /**
     * 禁止访问
     */
    C403(403, "禁止访问"),
    /**
     * 返回数据为空
     */
    C404(404, "返回数据为空"),
    /**
     * 请求参数错误
     */
    C415(415, "请求参数错误"),
    /**
     * 校验错误
     */
    C422(422, "校验错误"),
    /**
     * 服务器错误
     */
    C500(500, "服务器错误");

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 描述
     */
    private String desc;

    ResultCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
