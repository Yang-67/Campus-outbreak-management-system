package com.yang.sysmanagementnew.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.sysmanagementnew.controller.entityDto.UserDto;
import com.yang.sysmanagementnew.domain.User;

public interface UserService extends IService<User> {
    // 登录
    UserDto login(User user);

}
