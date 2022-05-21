package com.yang.sysmanagementnew.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.sysmanagementnew.config.Constants;
import com.yang.sysmanagementnew.controller.entityDto.UserDto;
import com.yang.sysmanagementnew.domain.LoginUser;
import com.yang.sysmanagementnew.domain.User;
import com.yang.sysmanagementnew.exception.ServiceException;
import com.yang.sysmanagementnew.mapper.UserMapper;
import com.yang.sysmanagementnew.service.UserService;
import com.yang.sysmanagementnew.utils.JwtUtil;
import com.yang.sysmanagementnew.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired(required = false)
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;
    //登录
    @Override
    public UserDto login(User user) {
        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserId(),user.getUserPwd());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，给出对应的提示
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }
        //如果认证通过了，使用userid生成一个jwt jwt存入ResponseResult返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userid = String.valueOf(loginUser.getUser().getUserId());
        String jwt = JwtUtil.createJWT(userid);
        //把完整的用户信息存入redis  userid作为key
        redisCache.setCacheObject("login:"+userid,loginUser);
        User user1 = getOne(new QueryWrapper<User>().eq("user_id",userid));
        return new UserDto(userid,user1.getUserName(),user1.getUserUrl(),user1.getIdentity(),jwt);
    }
}
