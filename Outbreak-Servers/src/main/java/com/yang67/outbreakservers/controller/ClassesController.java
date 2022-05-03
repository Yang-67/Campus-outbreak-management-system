package com.yang67.outbreakservers.controller;

import com.yang67.outbreakservers.common.Result;
import com.yang67.outbreakservers.entity.Classes;
import com.yang67.outbreakservers.service.ClassesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/classes")
public class ClassesController {
    @Resource
    private ClassesService classesService;

    //获取全部班级
    @GetMapping("/selectAllClasses")
    public Result selectAllClasses(){
        List<Classes> list = classesService.list();
        if(!list.isEmpty()){
            return Result.success(list);
        }
        return Result.error();
    }
}
