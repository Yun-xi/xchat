package com.xx.xchat.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author xieyaqi
 * @mail xieyaqi11@gmail.com
 * @date 2019-08-16 17:19
 */
@Data
@Accessors(chain = true)
public class BasePageResponse<T> implements Serializable {

    /**
     * 总条数
     */
    private long totalCount = 0;

    /**
     * 记录
     */
    private List<T> records = Collections.emptyList();
}
