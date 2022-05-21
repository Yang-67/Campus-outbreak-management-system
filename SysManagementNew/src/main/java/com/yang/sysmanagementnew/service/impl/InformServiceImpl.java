package com.yang.sysmanagementnew.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.sysmanagementnew.domain.Inform;
import com.yang.sysmanagementnew.mapper.InformMapper;
import com.yang.sysmanagementnew.service.InformService;
import org.springframework.stereotype.Service;

@Service
public class InformServiceImpl extends ServiceImpl<InformMapper, Inform> implements InformService {
}
