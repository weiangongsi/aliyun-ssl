package com.dcssn.ali.ssl.common.exception;

import com.dcssn.ali.ssl.common.result.HttpResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 全局异常处理
 * - 参数校验
 * - 业务异常统一抛出处理
 * - 异常拦截
 * </p>
 *
 * @author lhy
 * @since 2024-02-21
 */
@Slf4j
@Configuration
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public HttpResult<Object> globalExceptionHandler(Exception ex) {
        log.error("系统异常", ex);
        HttpResult<Object> httpResult = new HttpResult<>();
        return httpResult.fail(ProjectResultMessage.FAIL.code, ProjectResultMessage.FAIL.message());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public HttpResult<Object> handleValidException(MethodArgumentNotValidException exception) throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        BindingResult bindingResult = exception.getBindingResult();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            String message = fieldError.getDefaultMessage();
            String field = fieldError.getField();
            map.put(field, message);
        });
        HttpResult<Object> httpResult = new HttpResult<>();
        return httpResult.fail(ProjectResultMessage.CODE_400.code, objectMapper.writeValueAsString(map));
    }

    @ExceptionHandler({ProjectException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public HttpResult<Object> globalExceptionHandler(ProjectException ex) {
        ExpResultMessage result = ex.getResult();
        log.error("自定义异常", ex);
        HttpResult<Object> httpResult = new HttpResult<>();
        return httpResult.fail(result);
    }

}