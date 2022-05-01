package com.yang67.outbreakservers.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang67.outbreakservers.controller.entityDto.UserDto;
import com.yang67.outbreakservers.entity.User;

public interface UserService extends IService<User> {
    //    登录
    UserDto login(User user);
}
