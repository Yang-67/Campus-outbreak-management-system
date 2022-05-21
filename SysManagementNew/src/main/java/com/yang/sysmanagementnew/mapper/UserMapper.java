package com.yang.sysmanagementnew.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.sysmanagementnew.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
