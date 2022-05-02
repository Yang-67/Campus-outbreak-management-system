package com.yang67.outbreakservers.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang67.outbreakservers.entity.Inform;
import com.yang67.outbreakservers.mapper.InformMapper;
import com.yang67.outbreakservers.service.InformService;
import org.springframework.stereotype.Service;

@Service
public class InformServiceImpl extends ServiceImpl<InformMapper, Inform> implements InformService {
}
