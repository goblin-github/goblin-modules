package com.goblin.utils;

import com.goblin.base.BusinessException;
import com.goblin.enums.ResultCodeEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 参数校验工具类
 *
 * @author wangpeng
 * @version v1.0
 * @since 2022/11/17
 */
public class ValidateUtils {
    private static final Validator validator = Validation.byProvider(HibernateValidator.class)
            .configure()
            .failFast(true)
            .buildValidatorFactory()
            .getValidator();
    //private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 参数校验
     *
     * @param param  待校验参数
     * @param groups 具体校验组
     */
    public static void validate(Object param, Class<?>... groups) {
        Set<ConstraintViolation<Object>> validates = validator.validate(param, groups);
        if (CollectionUtils.isEmpty(validates)) {
            return;
        }
        String errorMessage = validates.iterator().next().getMessage();
        throw new BusinessException(ResultCodeEnum.PARAM_ERROR.getCode(), errorMessage);
    }
}
