package com.yang67.outbreakservers.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang67.outbreakservers.entity.Leave;
import com.yang67.outbreakservers.mapper.LeaveMapper;
import com.yang67.outbreakservers.service.LeaveService;
import org.springframework.stereotype.Service;

@Service
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, Leave> implements LeaveService{
}
