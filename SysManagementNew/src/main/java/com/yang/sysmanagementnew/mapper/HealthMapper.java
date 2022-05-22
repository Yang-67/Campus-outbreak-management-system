package com.yang.sysmanagementnew.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.sysmanagementnew.domain.Health;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HealthMapper extends BaseMapper<Health> {

    List<String> selectUser(String typeList);
}
