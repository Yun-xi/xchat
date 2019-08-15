package com.xx.xchat.conf;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author xieyaqi
 * @mail xieyaqi11@gmail.com
 * @date 2019-07-29 10:59
 */
@Component
public class ModelMetaObjectHandler implements MetaObjectHandler {

    /**
     * metaObject是页面传递过来的参数的包装对象，不是从数据库取的持久化对象，因此页面传过来哪些值，metaObject里就有哪些值。
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();
        Object createTime = this.getFieldValByName("createTime", metaObject);
        if(null == createTime){
            /**
             * 设置实体属性setter进去的值，优先级要高于自动填充的值。
             * 如果实体没有设置该属性，就给默认值，防止entity的setter值被覆盖。
             */
            this.setFieldValByName("createTime", now, metaObject);
        }
        Object updateTime = this.getFieldValByName("updateTime", metaObject);
        if(null == updateTime){
            this.setFieldValByName("updateTime", now, metaObject);
        }

        Object version = this.getFieldValByName("version", metaObject);
        if(null == version){
            this.setFieldValByName("version", 1L, metaObject);
        }

        Object deleteState = this.getFieldValByName("deleteState", metaObject);
        if(null == version){
            this.setFieldValByName("deleteState", 0, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date now = new Date();
        Object updateTime = this.getFieldValByName("updateTime", metaObject);
        if(null == updateTime){
            this.setFieldValByName("updateTime", now, metaObject);
        }
    }

}
