package com.yang67.outbreakservers.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yang67.outbreakservers.common.Result;

import com.yang67.outbreakservers.entity.Health;
import com.yang67.outbreakservers.entity.Leave;
import com.yang67.outbreakservers.entity.User;
import com.yang67.outbreakservers.mapper.LeaveMapper;
import com.yang67.outbreakservers.service.LeaveService;
import com.yang67.outbreakservers.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/leave")
public class LeaveController {
    @Resource
    private LeaveService leaveService;

    @Resource
    private LeaveMapper leaveMapper;

    @Resource
    private UserService userService;

    //学生提交请假信息
    @PostMapping("/insertLeaveInfo")
    public Result insertLeaveInfo(@RequestBody Leave leave){
        leave.setDeleteFlag(1);
        if(leaveService.save(leave)){
            return Result.success();
        }
        return Result.error();
    }

    //学生获取自己的请假信息
    @GetMapping("/getLeaveMeInfo")
    public Result getLeaveMeInfo(@RequestParam(value = "userId") String userId, @RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize, @RequestParam(defaultValue = "") String leaveType,
                                 @RequestParam(defaultValue = "") String datetime1, @RequestParam(defaultValue = "") String datetime2){
        QueryWrapper<Leave> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("delete_flag", 1);
        queryWrapper.orderByAsc("leave_state");
        queryWrapper.orderByDesc("create_time");
        if (!StrUtil.isEmpty(leaveType)) {
            queryWrapper.like("leave_type", leaveType);
        }
        if (!StrUtil.isEmpty(datetime1) && !StrUtil.isEmpty(datetime2)) {
            queryWrapper.between("start_time", datetime1, datetime2);
        }
        return Result.success(leaveMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper));
    }

    //删除请假记录
    @PostMapping("/deleteInfoByLeaveId")
    public Result deleteInfoByLeaveId(@RequestBody Leave leave){
        leave.setDeleteFlag(0);
        if(leaveService.saveOrUpdate(leave)){
            return Result.success();
        }
        return Result.error();
    }

    //老师获取本班学生的请假记录并审核
    @GetMapping("/getAllLeaveInfoByS")
    public Result getAllLeaveInfoByS(@RequestParam(value = "userId")String userId,
                                     @RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize,
                                     @RequestParam(defaultValue = "") String inputClass,
                                     @RequestParam(defaultValue = "") String inputName){
        User user = userService.getOne(new QueryWrapper<User>().eq("user_id", userId));
        String classIds = user.getClassId();
        List<String> typeList = new ArrayList<>();
        if (classIds != null) {
            String[] typeStr = classIds.split(",");
            typeList.addAll(Arrays.asList(typeStr));
        }
        QueryWrapper<Leave> queryWrapper = new QueryWrapper<>();
        if(!StrUtil.isEmpty(inputClass)){
            queryWrapper.eq("class_id",inputClass);
        }
        if(!StrUtil.isEmpty(inputName)){
            queryWrapper.like("user_id",inputName);
        }
        queryWrapper.in("class_id",typeList);
        queryWrapper.eq("delete_flag",1);
        queryWrapper.eq("leave_state",0);
        return Result.success(leaveMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper));
    }
}
