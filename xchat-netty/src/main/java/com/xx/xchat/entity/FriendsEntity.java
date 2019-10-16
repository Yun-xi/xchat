package com.xx.xchat.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xx.xchat.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xieyaqi
 * @mail xieyaqi11@gmail.com
 * @date 2019-10-16 16:28
 */
@Data
@Accessors(chain = true)
@TableName("n_friends")
@ApiModel("用户加好友请求")
public class FriendsEntity extends BaseEntity {

    @ApiModelProperty("用户编号")
    private Integer myUserId;

    @ApiModelProperty("我的好友用户编号")
    private Integer myFriendUserId;
}
