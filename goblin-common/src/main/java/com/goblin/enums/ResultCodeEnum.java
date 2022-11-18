package com.goblin.enums;

/**
 * 系统接口响应状态码
 *
 * @author wangpeng
 * @version v1.0
 * @since 2022/11/17
 */
public enum ResultCodeEnum {
    /**
     * 成功
     */
    SUCCESS(200, "success"),
    /**
     * 失败
     */
    ERROR(500, "系统异常"),
    /**
     * 传参出错
     */
    PARAM_ERROR(400, "参数异常"),
    /**
     * 记录不存在
     */
    RECORD_NOT_EXIST(501, "记录不存在");

    private final Integer code;

    private final String msg;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
