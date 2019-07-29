package com.xx.xchat.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.xx.xchat.base.BaseEntity;
import com.xx.xchat.enums.StateEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;


/**
 * @author xieyaqi
 * @mail 987159036@qq.com
 * @date 2019-07-26 17:50
 */

@Data
@Accessors(chain = true)
@TableName("user")
public class UserEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 5404786868951132035L;

    private String name;

    private String mail;

    private String phone;

    private String password;

    private String salt;

    private String departmentId;

    private StateEnum state;
}
