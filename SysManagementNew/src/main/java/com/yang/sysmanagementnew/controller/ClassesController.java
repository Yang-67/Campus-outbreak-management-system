package com.yang.sysmanagementnew.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yang.sysmanagementnew.domain.Classes;
import com.yang.sysmanagementnew.domain.Result;
import com.yang.sysmanagementnew.domain.User;
import com.yang.sysmanagementnew.service.ClassesService;
import com.yang.sysmanagementnew.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/classes")
public class ClassesController {
    @Resource
    private ClassesService classesService;

    @Resource
    private UserService userService;

    //获取全部班级
    @GetMapping("/selectAllClasses")
    public Result selectAllClasses(){
        List<Classes> list = classesService.list();
        if(!list.isEmpty()){
            return Result.success(list);
        }
        return Result.error();
    }

    //根据老师id查负责的班级
    @GetMapping("/getClassesByUserId")
    public Result getClassesByUserId(@RequestParam(value = "userId")String userId){
        User user = userService.getOne(new QueryWrapper<User>().eq("user_id", userId));
        String classIds = user.getClassId();
        List<String> typeList = new ArrayList<>();
        if (classIds != null) {
            String[] typeStr = classIds.split(",");
            typeList.addAll(Arrays.asList(typeStr));
        }
        return Result.success(classesService.list(new QueryWrapper<Classes>().in("class_id",typeList)));
    }
}
