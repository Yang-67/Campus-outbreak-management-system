package com.yang67.outbreakservers.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "leave_info")
public class Leave {
    @TableId(value = "leave_id")
    private int leaveId;
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

    public Leave() {
    }

    public Leave(int leaveId, String userId, String leaveReason, String leaveType, String startTime, String endTime, int leaveState, String createTime, int classId, int deleteFlag, String leaveNo) {
        this.leaveId = leaveId;
        this.userId = userId;
        this.leaveReason = leaveReason;
        this.leaveType = leaveType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.leaveState = leaveState;
        this.createTime = createTime;
        this.classId = classId;
        this.deleteFlag = deleteFlag;
        this.leaveNo = leaveNo;
    }

    public int getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getLeaveState() {
        return leaveState;
    }

    public void setLeaveState(int leaveState) {
        this.leaveState = leaveState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getLeaveNo() {
        return leaveNo;
    }

    public void setLeaveNo(String leaveNo) {
        this.leaveNo = leaveNo;
    }

    @Override
    public String toString() {
        return "Leave{" +
                "leaveId=" + leaveId +
                ", userId='" + userId + '\'' +
                ", leaveReason='" + leaveReason + '\'' +
                ", leaveType='" + leaveType + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", leaveState=" + leaveState +
                ", createTime='" + createTime + '\'' +
                ", classId=" + classId +
                ", deleteFlag=" + deleteFlag +
                ", leaveNo='" + leaveNo + '\'' +
                '}';
    }
}
