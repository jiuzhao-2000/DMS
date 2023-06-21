package com.jiuzhao73.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@ControllerAdvice
public class ControllerException {
    private final static String EXCEPTION_MSG_KEY = "Exception message : ";

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleValidException(MethodArgumentNotValidException e) {
        //日志记录错误信息
        // log.error(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        //将错误信息返回给前台
        // return BaseResult.fail(500, Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        return Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
    }
}
