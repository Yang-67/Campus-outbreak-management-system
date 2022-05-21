package com.yang.sysmanagementnew.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.sysmanagementnew.domain.Health;
import com.yang.sysmanagementnew.mapper.HealthMapper;
import com.yang.sysmanagementnew.service.HealthService;
import org.springframework.stereotype.Service;

@Service
public class HealthServiceImpl extends ServiceImpl<HealthMapper, Health> implements HealthService {
}
