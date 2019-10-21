package com.xx.xchat.controller.request;

import com.xx.xchat.base.BasePageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xieyaqi
 * @mail xieyaqi11@gmail.com
 * @date 2019-08-16 16:18
 */
@Data
@ApiModel("用户查询")
public class UserQueryRequest extends BasePageRequest implements Serializable {

    @ApiModelProperty(hidden = true)
    private static final long serialVersionUID = 5404786889351132035L;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String mail;

    @ApiModelProperty("部门编号")
    private String departmentId;
}
