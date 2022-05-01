package com.yang67.outbreakservers.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang67.outbreakservers.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
