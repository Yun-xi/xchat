package com.xx.xchat.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xx.xchat.base.BaseEntity;
import com.xx.xchat.enums.StateEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
@ApiModel("用户新增、修改")
public class UserEntity extends BaseEntity implements Serializable {

    @ApiModelProperty(hidden = true)
    private static final long serialVersionUID = 5404786868951132035L;

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    private String name;

    @ApiModelProperty("邮箱")
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String mail;

    @ApiModelProperty("手机号")
    @NotBlank(message = "手机号不能为空")
    @Size(min = 11, max = 11)
    private String phone;

//    @JsonIgnore // 在在序列化和反序列化的时候都会忽略这个属性，最直接的效果就是返回的JSON属性是没有这个属性的
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为恐慌")
    private String password;

    @JsonIgnore
    private String salt;

    @ApiModelProperty("部门编号")
    private String departmentId;

    @JsonIgnore
    private StateEnum state;

    @JsonIgnore
    @TableLogic // 逻辑删除
    @TableField(value="delete_state", fill = FieldFill.INSERT)  // 自动填充
    private Integer deleteState;
}
