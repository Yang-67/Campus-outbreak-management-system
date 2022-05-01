package com.yang67.outbreakservers.controller;

import cn.hutool.core.util.StrUtil;
import com.yang67.outbreakservers.common.Constants;
import com.yang67.outbreakservers.common.Result;
import com.yang67.outbreakservers.controller.entityDto.UserDto;
import com.yang67.outbreakservers.entity.User;
import com.yang67.outbreakservers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    //登录
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        if(StrUtil.isBlank(user.getUserPwd())){
            return Result.error(Constants.CODE_400,"参数错误");
        }
        UserDto userDto = userService.login(user);
        return Result.success(userDto);
    }
}
