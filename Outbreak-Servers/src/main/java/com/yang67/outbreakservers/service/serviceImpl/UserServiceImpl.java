package com.yang67.outbreakservers.service.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang67.outbreakservers.common.Constants;
import com.yang67.outbreakservers.controller.entityDto.UserDto;
import com.yang67.outbreakservers.entity.User;
import com.yang67.outbreakservers.exception.ServiceException;
import com.yang67.outbreakservers.mapper.UserMapper;
import com.yang67.outbreakservers.service.UserService;
import com.yang67.outbreakservers.utils.TokenUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
   //登录
    @Override
    public UserDto login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getUserId());
        queryWrapper.eq("user_pwd",user.getUserPwd());
        queryWrapper.eq("delete_flag",1);
        User one;
        try {
            one=getOne(queryWrapper);
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        if(one != null){
            BeanUtil.copyProperties(one,user,true);
            String token = TokenUtils.genToken(one.getUserId(),one.getUserPwd());
            UserDto userDto = new UserDto(one.getUserId(),one.getUserName(),one.getUserUrl(), one.getIdentity(),token);
            BeanUtil.copyProperties(one,userDto,true);
            return userDto;
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }
}
