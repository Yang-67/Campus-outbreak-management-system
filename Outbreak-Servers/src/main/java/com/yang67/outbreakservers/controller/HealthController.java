package com.yang67.outbreakservers.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yang67.outbreakservers.common.Result;
import com.yang67.outbreakservers.entity.Health;
import com.yang67.outbreakservers.service.HealthService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/health")
public class HealthController {
    @Resource
    private HealthService healthService;

    //学生添加每日健康信息
    @PostMapping("/insertHealthInfo")
    public Result insertHealthInfo(@RequestBody Health health){
        System.out.println(health.toString());
        if(healthService.save(health)){
            return Result.success();
        }else{
            return Result.error();
        }
    }

    //学生查询每日健康记录
    @GetMapping("/getDayHealthInfo")
    public Result getDayHealthInfo(@RequestParam(value = "userId")String userId){
        QueryWrapper<Health> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("delete_flag",1);
        return Result.success(healthService.list(queryWrapper));
    }

    //根据学号获取学生健康信息条数
    @GetMapping("/getHealthNumByUserId")
    public int getHealthNumByUserId(@RequestParam(value = "userId")String userId){
        return (int) healthService.count();
    }
}
