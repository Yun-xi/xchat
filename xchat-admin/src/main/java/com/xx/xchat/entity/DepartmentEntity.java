package com.xx.xchat.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xx.xchat.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("department")
public class DepartmentEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 7186580233523882741L;

    private String name;

    private Integer parentId;
}
