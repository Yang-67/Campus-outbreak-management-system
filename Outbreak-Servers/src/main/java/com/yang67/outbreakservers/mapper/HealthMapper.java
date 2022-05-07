package com.yang67.outbreakservers.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang67.outbreakservers.entity.Health;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HealthMapper extends BaseMapper<Health> {
}
