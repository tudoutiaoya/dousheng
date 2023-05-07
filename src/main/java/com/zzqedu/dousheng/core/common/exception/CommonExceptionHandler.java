package com.zzqedu.dousheng.core.common.exception;

import com.zzqedu.dousheng.core.common.resp.RestResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.stream.Collectors;

/**
 * 通用的异常处理器
 */
@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {


    /**
     * 处理 hibernate 校验异常
     */
    @ExceptionHandler(value = {BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<RestResp> handleValidatedException(Exception e) {
        RestResp resp = null;
        if (e instanceof MethodArgumentNotValidException) {
            // json validate error
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            resp = RestResp.fail(HttpStatus.BAD_REQUEST.value(),
                ex.getBindingResult().getAllErrors().stream()
                        .map(ObjectError::getDefaultMessage)
                        .collect(Collectors.joining("; "))
            );
        } else if (e instanceof ConstraintViolationException) {
            // simple param error
            ConstraintViolationException ex = (ConstraintViolationException) e;
            resp = RestResp.fail(HttpStatus.BAD_REQUEST.value(),
                    ex.getConstraintViolations().stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.joining("; ")));
        } else if (e instanceof BindException) {
            // form error
            BindException ex = (BindException) e;
            resp = RestResp.fail(HttpStatus.BAD_REQUEST.value(),
                    ex.getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .collect(Collectors.joining("; ")));
        }
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }


    /**
     * 处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public RestResp handlerBusinessException(BusinessException e) {
        log.error(e.getMessage(), e);
        return RestResp.fail(e.getErrorCodeEnum());
    }

    /**
     * 处理系统异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResp handlerException(Exception e) {
        log.error(e.getMessage(), e);
        return RestResp.error();
    }

}
