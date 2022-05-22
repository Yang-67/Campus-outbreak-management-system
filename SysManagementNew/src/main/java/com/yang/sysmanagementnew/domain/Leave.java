package com.yang.sysmanagementnew.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "leave_info")
public class Leave implements Serializable {
    @TableId(value = "leave_id")
    private Integer leaveId;
    private String userId;
    private String leaveReason;
    private String leaveType;
    private String startTime;
    private String endTime;
    private int leaveState;
    private String createTime;
    private int classId;
    private int deleteFlag;
    private String leaveNo;
}
