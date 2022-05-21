package com.yang.sysmanagementnew.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.sysmanagementnew.domain.Classes;
import com.yang.sysmanagementnew.mapper.ClassMapper;
import com.yang.sysmanagementnew.service.ClassesService;
import org.springframework.stereotype.Service;

@Service
public class ClassesServiceImpl extends ServiceImpl<ClassMapper, Classes> implements ClassesService {
}
