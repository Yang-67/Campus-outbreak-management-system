package com.yang.sysmanagementnew.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.sysmanagementnew.domain.Leave;
import com.yang.sysmanagementnew.mapper.LeaveMapper;
import com.yang.sysmanagementnew.service.LeaveService;
import org.springframework.stereotype.Service;

@Service
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, Leave> implements LeaveService {
}
