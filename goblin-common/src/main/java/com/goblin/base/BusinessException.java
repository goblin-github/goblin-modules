package com.goblin.base;

import com.goblin.enums.ResultCodeEnum;

/**
 * 自定义系统业务异常
 *
 * @author wangpeng
 * @version v1.0
 * @since 2022/11/17
 */
public class BusinessException extends RuntimeException {
    /**
     * 错误码
     */
    private int errorCode;
    /**
     * 错误信息
     */
    private String message;

    public BusinessException(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public BusinessException(ResultCodeEnum resultEnum) {
        this.errorCode = resultEnum.getCode();
        this.message = resultEnum.getMsg();
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
