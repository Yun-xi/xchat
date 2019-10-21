package com.xx.xchat.entity.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

/**
 * 
 * @Description: 添加好友前置状态 枚举
 */
public enum SearchFriendsStatusEnum implements IEnum<Integer> {
	
	SUCCESS(0, "OK"),
	USER_NOT_EXIST(1, "无此用户..."),	
	NOT_YOURSELF(2, "不能添加你自己..."),			
	ALREADY_FRIENDS(3, "该用户已经是你的好友...");

	private final Integer value;
	private final String desc;

	SearchFriendsStatusEnum(final int value, final String desc) {
		this.value = value;
		this.desc = desc;
	}

	@Override
	public Integer getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}

	public static SearchFriendsStatusEnum parseValue(Integer value) {
		return Stream.of(SearchFriendsStatusEnum.values())
				.filter(val -> val.getValue().equals(value))
				.findFirst()
				.orElse(null);
	}
	
}
