package com.xx.xchat.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xx.xchat.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.xml.ws.BindingType;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("user_role_relate")
public class UserRoleRelateEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 2018209356447386393L;

    private String roleId;

    private String menuId;
}
