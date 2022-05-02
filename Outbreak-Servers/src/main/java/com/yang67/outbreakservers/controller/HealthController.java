package com.yang67.outbreakservers.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yang67.outbreakservers.common.Result;
import com.yang67.outbreakservers.entity.Health;
import com.yang67.outbreakservers.entity.Inform;
import com.yang67.outbreakservers.mapper.HealthMapper;
import com.yang67.outbreakservers.service.HealthService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/health")
public class HealthController {
    @Resource
    private HealthService healthService;

    @Resource
    private HealthMapper healthMapper;

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
    public Result getDayHealthInfo(@RequestParam(value = "userId")String userId,@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,@RequestParam(defaultValue = "")String location,
                                   @RequestParam(defaultValue = "")String datetime1,@RequestParam(defaultValue = "")String datetime2){
        QueryWrapper<Health> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("delete_flag",1);
        queryWrapper.orderByDesc("create_time");
        if(!StrUtil.isEmpty(location)){
            queryWrapper.like("location",location);
        }
        if(!StrUtil.isEmpty(datetime1) && !StrUtil.isEmpty(datetime2)){
            queryWrapper.between("create_time",datetime1,datetime2);
        }
        return Result.success(healthMapper.selectPage(new Page<>(pageNum, pageSize),queryWrapper));
    }

    //根据学号获取学生健康信息条数
    @GetMapping("/getHealthNumByUserId")
    public int getHealthNumByUserId(@RequestParam(value = "userId")String userId){
        return (int) healthService.count();
    }

    //
    @GetMapping("/deleteInfoByHealthId")
    public Result deleteInfoByHealthId(@RequestParam(value = "healthId") int healthId){
        System.out.println("删除："+healthId);
        return Result.success();
    }

    //图表1，疫苗
    @GetMapping("/members1")
    public Result members1(){
        List<Health> list = healthService.list();
        int n0=0,n1=0,n2=0,n3=0;
        for(Health health:list){
            int vaccineNum = health.getVaccineNum();
            switch (vaccineNum) {
                case 0: n0 += 1; break;
                case 1: n1 += 1; break;
                case 2: n2 += 1; break;
                case 3: n3 += 1; break;
                default: break;
            }
        }
        return Result.success(CollUtil.newArrayList(n0,n1,n2,n3));
    }

    //图表2，健康
    @GetMapping("/members2")
    public Result members2(){
        List<Health> list = healthService.list();
        int n0=0,n1=0,n2=0;
        for(Health health:list){
            int healthState = health.getHealthState();
            switch (healthState) {
                case 0: n0 += 1; break;
                case 1: n1 += 1; break;
                case 2: n2 += 1; break;
                default: break;
            }
        }
        return Result.success(CollUtil.newArrayList(n0,n1,n2));
    }
}
