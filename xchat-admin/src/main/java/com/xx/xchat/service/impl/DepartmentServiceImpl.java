package com.xx.xchat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xx.xchat.dao.DepartmentMapper;
import com.xx.xchat.entity.DepartmentEntity;
import com.xx.xchat.service.DepartmentService;
import org.springframework.stereotype.Service;

/**
 * @author xieyaqi
 * @mail xieyaqi11@gmail.com
 * @date 2019-07-30 13:50
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, DepartmentEntity> implements DepartmentService {
}
