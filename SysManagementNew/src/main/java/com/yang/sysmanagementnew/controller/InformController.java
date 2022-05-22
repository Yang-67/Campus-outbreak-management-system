package com.yang.sysmanagementnew.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yang.sysmanagementnew.domain.Inform;
import com.yang.sysmanagementnew.domain.Result;
import com.yang.sysmanagementnew.mapper.InformMapper;
import com.yang.sysmanagementnew.service.InformService;
import com.yang.sysmanagementnew.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/inform")
public class InformController {
    @Resource
    private InformService informService;

    @Resource
    private InformMapper informMapper;

    @Resource
    private UserService userService;

    //获取本班通知信息
    @GetMapping("/getAllInformByUserId")
    public Result getClassInform(@RequestParam(value = "userId") String userId, @RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize, @RequestParam(defaultValue = "") String informTitle) {
        String classId = userService.getById(userId).getClassId();
        QueryWrapper<Inform> queryWrapper = new QueryWrapper<>();
        String classIds = classId + ",";
        queryWrapper.like("class_id", classIds);
        queryWrapper.eq("delete_flag", 1);
        queryWrapper.orderByDesc("release_time");
        if (!StrUtil.isEmpty(informTitle)) {
            queryWrapper.like("inform_title", informTitle);
        }
        return Result.success(informMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper));
    }

    //查询面向全体学生的通知
    @GetMapping("/getStudentInfo")
    public Result getStudentInfo() {
        return Result.success(informService.list(new QueryWrapper<Inform>().eq("class_id", "0,")));
    }

    //老师发布通知
    @PostMapping("/insertInformInfoS")
    public Result insertInformInfoS(@RequestBody Inform inform) {
        inform.setDeleteFlag(1);
        if (informService.save(inform)) {
            return Result.success();
        }
        return Result.error();
    }


}
