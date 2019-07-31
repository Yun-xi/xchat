package com.xx.xchat.exception;

import com.baomidou.mybatisplus.extension.api.R;
import com.xx.xchat.utils.BaseResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xieyaqi
 * @mail 987159036@qq.com
 * @date 2019-07-31 18:00
 */
@Slf4j
@RestControllerAdvice
public class XExceptionHandler {

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
        return ResponseEntity.ok(BaseResp.fail("数据库中已存在该记录");
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<Object> handleAuthorizationException(AuthorizationException e){
        log.error(e.getMessage(), e);
        return R.error("没有权限，请联系管理员授权");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e){
        log.error(e.getMessage(), e);
        return R.error();
    }

}
