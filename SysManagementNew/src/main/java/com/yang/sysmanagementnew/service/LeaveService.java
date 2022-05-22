package com.yang.sysmanagementnew.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.sysmanagementnew.domain.Leave;
import com.yang.sysmanagementnew.domain.Result;

public interface LeaveService extends IService<Leave> {
    //老师审核该假条
    Result UpdLeaveInfo(String leaveId, String leaveState, String leaveNo);
}
