package com.xx.xchat.base;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * @author xieyaqi
 * @mail 987159036@qq.com
 * @date 2019-07-29 11:08
 */
@Data
public class BaseEntity {
    /**
     * AUTO	                数据库自增
     * INPUT	            自行输入
     * ID_WORKER	        分布式全局唯一ID 长整型类型
     * UUID	                32位UUID字符串
     * NONE	                无状态
     * ID_WORKER_STR	    分布式全局唯一ID 字符串类型
     *
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 更新日期<br>
     * @TableField注解说明：<br>
     *
     * <h1>1、value属性：</h1>
     * 生成sql时，实体属性（例如modifyDate）对应数据库表的列名（表字段，例如modify_date）。<br>
     *
     * <h1>2、fill属性：</h1>
     * 指定数据库表的字段的填充策略，各枚举值含义请自己看源码。<br>
     * 配置该属性，在执行insert、update等SQL时，如果策略匹配成功，就会在生成的SQL中自动追加新增、更新该字段的内容，<br>
     * 并且该字段的取值会通过MetaObjectHandler类的insertFill、updateFill等对应方法中的设置进行赋值。<br>
     * 如果通过该填充策略给数据库表字段自动填充值时，必须自己实现MetaObjectHandler抽象类，并注册到mybatis的全局配置中去。<br>
     * 请参考mybatis-plug自动填充功能：{@link https://mp.baomidou.com/guide/auto-fill-metainfo.html}<br>
     * springBoot的配置：{@link https://www.cnblogs.com/lqtbk/p/9843401.html}<br>
     * 本项目中MetaObjectHandler的实现类未：{@link com.xx.xchat.conf.ModelMetaObjectHandler}<br>
     * <b>注意：</b><br>
     * 如果该注解同时设置了“update属性”(见下)，且fill属性取值为UPDATE或INSERT_UPDATE，那么fill属性优先级高于update属性，会导致设置的update属性策略失效！！！<br>
     *
     * <h1>3、update属性：</h1>
     * 对实体进行update操作时，生成的update SQL，会注入该字段的更新。<br>
     * 例如：<br>
     * update="NOW()"，则生成的SQL中会含“update ... set ..., update_time = NOW()”<br>
     * update="%s+1"，则生成的SQL中会含“update ... set ..., version = version + 1<br>
     * <b>注意：</b><br>
     * 如果该注解同时设置了“fill属性”(见上)，且fill属性取值为UPDATE或INSERT_UPDATE，那么fill属性优先级高于update属性，会导致设置的update属性策略失效！！！<br>
     *
     * <h1>优先级总结：</h1>
     * 实体属性更新到数据库时，属性值来源的优先级：实体类对象setter设置的值 &gt; fill策略填充的值 &gt; update策略SQL注入的值。<br>
     * <b>注意：</b><br>
     * setter优先级和fill策略优先级，是在MetaObjectHandler中自己手动实现的。
     **/
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time", update = "now()", fill = FieldFill.INSERT)
    private Date updateTime;

    /**
     * @Version注解说明：<br>
     * 更新时，实体对象的version属性必须有值，才会生成SQL update ... WHERE ... and version=?
     */
    @JsonIgnore
    @Version
    @TableField(value="version", fill = FieldFill.INSERT, update="%s+1")
    protected Long version;
}
