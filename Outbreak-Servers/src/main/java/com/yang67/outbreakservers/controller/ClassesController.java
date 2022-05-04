package com.yang67.outbreakservers.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yang67.outbreakservers.common.Result;
import com.yang67.outbreakservers.entity.Classes;
import com.yang67.outbreakservers.entity.User;
import com.yang67.outbreakservers.service.ClassesService;
import com.yang67.outbreakservers.service.UserService;
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
