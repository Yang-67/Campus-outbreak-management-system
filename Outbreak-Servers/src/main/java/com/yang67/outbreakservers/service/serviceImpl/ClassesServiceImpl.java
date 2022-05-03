package com.yang67.outbreakservers.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang67.outbreakservers.entity.Classes;
import com.yang67.outbreakservers.mapper.ClassMapper;
import com.yang67.outbreakservers.service.ClassesService;
import org.springframework.stereotype.Service;

@Service
public class ClassesServiceImpl extends ServiceImpl<ClassMapper, Classes> implements ClassesService {
}
