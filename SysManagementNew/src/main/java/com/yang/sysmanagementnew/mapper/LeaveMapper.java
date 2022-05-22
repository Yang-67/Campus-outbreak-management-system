package com.yang.sysmanagementnew.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.sysmanagementnew.domain.Leave;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface LeaveMapper extends BaseMapper<Leave> {
    int UpdLeaveInfo(Map<String,String> map);
}
