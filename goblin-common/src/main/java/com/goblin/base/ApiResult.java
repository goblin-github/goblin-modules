package com.goblin.base;

import com.goblin.enums.ResultCodeEnum;

/**
 * 封装接口返回值
 *
 * @author wangpeng
 * @version v1.0
 * @since 2022/11/17
 */
public class ApiResult<T> {
    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 提示信息
     */
    private final String msg;

    /**
     * 具体的内容
     */
    private T data;

    /**
     * 成功时调用, 没有data内容
     *
     * @return ApiResult
     */
    public static <String> ApiResult<String> success() {
        return ApiResult.build(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMsg(), null);
    }

    /**
     * 成功时候的调用
     *
     * @param <T> 返回实体的类型
     * @return ApiResult
     */
    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(data);
    }

    /**
     * 根据返回的状态枚举, 构建返回结果
     *
     * @param resultEnum {@link ResultCodeEnum} 返回状态枚举
     * @return ApiResult
     */
    public static <T> ApiResult<T> build(ResultCodeEnum resultEnum) {
        return new ApiResult<>(resultEnum);
    }

    /**
     * 根据自定义状态码{@code code}和自定义提示信息{@code msg}构建返回结果
     *
     * @param code 自定义状态码
     * @param msg  自定义提示信息
     * @return ApiResult
     */
    public static <T> ApiResult<T> build(Integer code, String msg) {
        return new ApiResult<>(code, msg);
    }

    /**
     * 根据自定义状态码{@code code}, 自定义提示信息{@code msg}以及返回实体{@code T}构建返回结果
     *
     * @param code 自定义状态码
     * @param msg  自定义提示信息
     * @param <T>  返回实体的类型
     * @return ApiResult
     */
    public static <T> ApiResult<T> build(Integer code, String msg, T data) {
        return new ApiResult<>(code, msg, data);
    }

    /**
     * 出错时调用, 自定义提示信息{@code msg}
     *
     * @param msg 自定义提示信息
     * @param <T> 返回实体的类型
     * @return ApiResult
     */
    public static <T> ApiResult<T> error(String msg) {
        return new ApiResult<T>(msg);
    }

    /**
     * 出错时调用, 自定义业务异常{@code msg}
     *
     * @param businessException 业务异常
     * @param <T>               返回实体的类型
     * @return ApiResult
     */
    public static <T> ApiResult<T> error(BusinessException businessException) {
        return new ApiResult<>(businessException.getErrorCode(), businessException.getMessage());
    }

    /**
     * 出错时调用, 根据返回实体{@code T}构建返回结果
     *
     * @param data 返回实体
     * @param <T>  返回实体的类型
     * @return ApiResult
     */
    public static <T> ApiResult<T> error(T data) {
        return new ApiResult<>(ResultCodeEnum.ERROR.getCode(), ResultCodeEnum.ERROR.getMsg(), data);
    }

    private ApiResult(T data) {
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.msg = ResultCodeEnum.SUCCESS.getMsg();
        this.data = data;
    }

    private ApiResult(String msg) {
        this.code = ResultCodeEnum.ERROR.getCode();
        this.data = null;
        this.msg = msg;
    }

    private ApiResult(ResultCodeEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    private ApiResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ApiResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
