package com.goblin.exception;

import com.goblin.base.ApiResult;
import com.goblin.base.BusinessException;
import com.goblin.enums.ResultCodeEnum;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * 系统全局异常拦截器
 *
 * @author wangpeng
 * @version v1.0
 * @since 2022/11/17
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = BusinessException.class)
    public ApiResult<String> businessHandle(BusinessException e) {
        logger.error("业务异常！errorMessage:{}", ExceptionUtils.getMessage(e), e);
        return ApiResult.error(e);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ApiResult<String> exceptionHandle(Exception e) {
        logger.error("未知异常！errorMessage:{}", ExceptionUtils.getMessage(e), e);
        return ApiResult.build(ResultCodeEnum.ERROR);
    }

    @ExceptionHandler(value = {BindException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult<String> validException(Exception e) {
        logger.error("参数校验异常！errorMessage:{}", ExceptionUtils.getMessage(e), e);
        if (e instanceof ConstraintViolationException) {
            ConstraintViolationException ex = (ConstraintViolationException) e;
            String message = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
            return ApiResult.build(ResultCodeEnum.PARAM_ERROR.getCode(), message);
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            String message = ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
            return ApiResult.build(ResultCodeEnum.PARAM_ERROR.getCode(), message);
        }
        return ApiResult.build(ResultCodeEnum.PARAM_ERROR);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult<String> missException(MissingServletRequestParameterException exception) {
        logger.error("missException！errorMessage:{}", ExceptionUtils.getMessage(exception), exception);
        return ApiResult.build(ResultCodeEnum.PARAM_ERROR);
    }

}
