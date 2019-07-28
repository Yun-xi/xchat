package com.xx.xchat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xx.xchat.enums.StateEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;


/**
 * @author xieyaqi
 * @mail 987159036@qq.com
 * @date 2019-07-26 17:50
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 5404786868951132035L;

    /**
     * AUTO	                数据库自增
     * INPUT	            自行输入
     * ID_WORKER	        分布式全局唯一ID 长整型类型
     * UUID	                32位UUID字符串
     * NONE	                无状态
     * ID_WORKER_STR	    分布式全局唯一ID 字符串类型
     *
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    private Date createTime;

    private Date updateTime;

    private String name;

    private String mail;

    private String phone;

    private String password;

    private String salt;

    private Integer departmentId;

    /**
     * @see StateEnum
     */
    private Integer state;
}
