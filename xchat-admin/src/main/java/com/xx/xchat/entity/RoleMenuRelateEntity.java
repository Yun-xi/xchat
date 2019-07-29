package com.xx.xchat.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xx.xchat.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("role_menu_relate")
public class RoleMenuRelateEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 3792236463356513690L;

    private String roleId;

    private String menuId;
}
