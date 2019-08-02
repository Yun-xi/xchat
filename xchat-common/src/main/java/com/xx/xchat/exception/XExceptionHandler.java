package com.xx.xchat.exception;

import com.baomidou.mybatisplus.extension.api.R;
import com.xx.xchat.utils.BaseResp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xieyaqi
 * @mail 987159036@qq.com
 * @date 2019-07-31 18:00
 */
@Slf4j
@RestControllerAdvice
public class XExceptionHandler extends ResponseEntityExceptionHandler{

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(XException.class)
    public ResponseEntity<Object> handleXException(XException e){
        return ResponseEntity.ok(BaseResp.fail(e.getErrCode(), e.getDescription()));
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Object> handleDuplicateKeyException(DuplicateKeyException e){
        log.error(e.getMessage(), e);
        return ResponseEntity.ok(BaseResp.fail("数据库中已存在该记录"));
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<Object> handleAuthorizationException(AuthorizationException e){
        log.error(e.getMessage(), e);
        return ResponseEntity.ok(BaseResp.fail("没有权限，请联系管理员授权"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e){
        log.error(e.getMessage(), e);
        return ResponseEntity.ok(BaseResp.fail());
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
                                                         WebRequest request) {
        return ResponseEntity.ok(BaseResp.fail(buildMessages(ex.getBindingResult())));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.ok(buildMessages(ex.getBindingResult()));
    }

    @Override
    public ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                       HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.ok(ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {
        return ResponseEntity.ok(ex.getMessage());
    }

    private String buildMessages(BindingResult result) {
        List<ObjectError> errors = result.getAllErrors();

        String resultString = null;
        if (CollectionUtils.isNotEmpty(errors)) {
            resultString = errors.stream()
                    .filter(error -> error instanceof FieldError)
                    .map(error -> {
                        FieldError fieldError = (FieldError) error;
                        String fieldName = fieldError.getField();
                        String fieldErrMsg = fieldError.getDefaultMessage();
                        return fieldName + fieldErrMsg;
                    }).collect(Collectors.joining(";  "));
        }

        return resultString;
    }
}
