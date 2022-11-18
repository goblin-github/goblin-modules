package com.goblin.utils;

import com.goblin.base.BusinessException;
import com.goblin.enums.ResultCodeEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Objects;

/**
 * 断言工具类
 *
 * @author wangpeng
 * @version v1.0
 * @since 2022/11/17
 */
public final class Assert {

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new BusinessException(ResultCodeEnum.PARAM_ERROR.getCode(), message);
        }
    }

    public static void isTrue(boolean expression, String message, int code) {
        if (!expression) {
            throw new BusinessException(code, message);
        }
    }

    public static void isNull(Object object, String message) {
        if (Objects.nonNull(object)) {
            throw new BusinessException(ResultCodeEnum.PARAM_ERROR.getCode(), message);
        }
    }

    public static void nonNull(Object object, String message) {
        if (Objects.isNull(object)) {
            throw new BusinessException(ResultCodeEnum.PARAM_ERROR.getCode(), message);
        }
    }

    public static void isBlank(String str, String message) {
        if (StringUtils.isNotBlank(str)) {
            throw new BusinessException(ResultCodeEnum.PARAM_ERROR.getCode(), message);
        }
    }

    public static void isNotBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new BusinessException(ResultCodeEnum.PARAM_ERROR.getCode(), message);
        }
    }

    public static void isEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isNotEmpty(collection)) {
            throw new BusinessException(ResultCodeEnum.PARAM_ERROR.getCode(), message);
        }
    }

    public static void isNotEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BusinessException(ResultCodeEnum.PARAM_ERROR.getCode(), message);
        }
    }
}
