package com.xx.xchat.pojo.vo;

import lombok.Data;

/**
 * @Description: 好友请求发送方的信息
 */
@Data
public class FriendRequestVO {
	
    private String sendUserId;
    private String sendUsername;
    private String sendFaceImage;
    private String sendNickname;
}