package com.yang67.outbreakservers.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yang67.outbreakservers.common.Result;
import com.yang67.outbreakservers.entity.Inform;
import com.yang67.outbreakservers.mapper.InformMapper;
import com.yang67.outbreakservers.service.InformService;
import com.yang67.outbreakservers.service.UserService;
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
                                 @RequestParam Integer pageSize, @RequestParam(defaultValue = "") String informTitle){
        int classId = userService.getById(userId).getClassId();
        QueryWrapper<Inform> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("class_id",classId);
        queryWrapper.eq("delete_flag",1);
        queryWrapper.orderByDesc("release_time");
        if(!StrUtil.isEmpty(informTitle)){
            queryWrapper.like("inform_title",informTitle);
        }
//        informService.list(new QueryWrapper<Inform>().eq("class_id",classId));
        return Result.success(informMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper));
    }
}
