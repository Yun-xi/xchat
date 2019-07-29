package com.xx.xchat.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xx.xchat.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("role")
public class RoleEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1639012087470680225L;

    private String name;

    private String remark;

}
