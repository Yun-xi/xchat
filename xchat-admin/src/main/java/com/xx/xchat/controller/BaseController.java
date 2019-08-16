package com.xx.xchat.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xx.xchat.base.BasePageResponse;
import com.xx.xchat.entity.UserEntity;
import org.apache.shiro.SecurityUtils;


/**
 * @author xieyaqi
 * @mail xieyaqi11@gmail.com
 * @date 2019-08-16 11:55
 */
public class BaseController {

    protected UserEntity getUser() {
        return (UserEntity)SecurityUtils.getSubject().getPrincipal();
    }

    protected Integer getUserId() {
        return getUser().getId();
    }

    protected <T> BasePageResponse<T> unPacking(IPage<T> page){
        return new BasePageResponse<T>()
                .setTotalCount(page.getTotal())
                .setRecords(page.getRecords());
    }
}