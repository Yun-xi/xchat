package com.xx.xchat.base;

import lombok.Data;

/**
 * @author xieyaqi
 * @mail xieyaqi11@gmail.com
 * @date 2019-08-16 16:15
 */
@Data
public class BasePageRequest {

    /**
     * 每页显示条数
     */
    private Integer pageSize = 10;

    /**
     * 当前页数
     */
    private Integer pageNumber = 0;
}
