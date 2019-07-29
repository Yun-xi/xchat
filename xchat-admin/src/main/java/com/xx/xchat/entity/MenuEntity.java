package com.xx.xchat.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xx.xchat.base.BaseEntity;
import com.xx.xchat.enums.MenuTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("menu")
public class MenuEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -3581873359488354887L;

    private String name;

    private String url;

    private String perms;

    private MenuTypeEnum type;

    private String icon;
}
