package com.xx.xchat.service.impl;

import com.xx.xchat.ApplicationTests;
import com.xx.xchat.entity.DepartmentEntity;
import com.xx.xchat.service.DepartmentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class DepartmentServiceImplTest extends ApplicationTests {

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void testSaveDept() {
        DepartmentEntity departmentEntity = new DepartmentEntity()
                .setName("XX集团")
                .setParentId(0);

        departmentService.save(departmentEntity);
    }
}