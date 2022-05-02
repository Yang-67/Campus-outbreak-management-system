package com.yang67.outbreakservers.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang67.outbreakservers.entity.Health;
import com.yang67.outbreakservers.mapper.HealthMapper;
import com.yang67.outbreakservers.service.HealthService;
import org.springframework.stereotype.Service;

@Service
public class HealthServiceImpl extends ServiceImpl<HealthMapper, Health> implements HealthService {
}
