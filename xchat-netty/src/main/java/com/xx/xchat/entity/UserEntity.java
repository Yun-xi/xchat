package com.xx.xchat.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xx.xchat.base.BaseEntity;
import com.xx.xchat.validator.AddGroup;
import com.xx.xchat.validator.UpdateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author xieyaqi
 * @mail xieyaqi11@gmail.com
 * @date 2019-10-16 15:45
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("n_user")
@ApiModel("用户新增、修改")
public class UserEntity extends BaseEntity implements Serializable {

    @ApiModelProperty(hidden = true)
    private static final long serialVersionUID = -2272292459433939245L;

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空", groups = AddGroup.class)
    @Size(min = 6, message = "密码不能少于6位", groups = AddGroup.class)
    @Null(message = "当前不允许修改密码，请到修改密码处进行修改", groups = UpdateGroup.class)
    private String password;

    @JsonIgnore
    private String salt;

    @ApiModelProperty("小头像")
    private String faceImage;

    @ApiModelProperty("大头像")
    private String faceImageBig;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("二维码")
    private String qrcode;

    @ApiModelProperty("设备编号")
    private String cid;

    @JsonIgnore
    @TableLogic // 逻辑删除
    @TableField(value="delete_state", fill = FieldFill.INSERT)  // 自动填充
    private Integer deleteState;
}
