package com.xx.xchat.entity;

import com.xx.xchat.base.BaseIdentityEntity;
import com.xx.xchat.enums.StateEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author xieyaqi
 * @mail 987159036@qq.com
 * @date 2019-07-26 17:50
 *
 * @Table catalog和schema屬性是指定数据库实例名
 * 当catalog属性不指定时，新创建的表将出现在url指定的数据库实例中
 * 当catalog属性设置名称时，若数据库存在和指定名称一致的实例，新创建的表将出现在该实例中
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity extends BaseIdentityEntity {

    @Column(name = "name", columnDefinition = "VARCHAR(32) COMMENT '用戶姓名'")
    private String name;

    @Column(name = "mail", columnDefinition = "VARCHAR(32) COMMENT '邮箱'")
    private String mail;

    @Column(name = "phone", columnDefinition = "VARCHAR(32) COMMENT '手机号'")
    private String phone;

    @Column(name = "password", columnDefinition = "VARCHAR(32) COMMENT '密码'")
    private String password;

    @Column(name = "salt", columnDefinition = "VARCHAR(32) COMMENT '盐'")
    private String salt;

    @Column(name = "department_id", columnDefinition = "INT COMMENT '部门编号'")
    private Integer departmentId;

    /**
     * @see StateEnum
     */
    @Column(name = "state", columnDefinition = "SMALLINT COMMENT '状态 1:正常 2:禁用'")
    private Integer state;
}
