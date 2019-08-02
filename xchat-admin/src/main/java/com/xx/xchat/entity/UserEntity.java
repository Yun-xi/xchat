package com.xx.xchat.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xx.xchat.base.BaseEntity;
import com.xx.xchat.entity.enums.StateEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


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
    @Size(message = "手机号格式不正确", min = 11, max = 11)
    private String phone;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, message = "密码不能少于6位")
    private String password;

    @JsonIgnore // 在在序列化和反序列化的时候都会忽略这个属性，最直接的效果就是返回的JSON属性是没有这个属性的
    private String salt;

    @ApiModelProperty("部门编号")
    private Integer departmentId;

    @JsonIgnore
    private StateEnum state;

    @JsonIgnore
    @TableLogic // 逻辑删除
    @TableField(value="delete_state", fill = FieldFill.INSERT)  // 自动填充
    private Integer deleteState;

    @ApiModelProperty("角色编号数组")
    @TableField(exist = false)
    private List<Integer> roleIds;
}
