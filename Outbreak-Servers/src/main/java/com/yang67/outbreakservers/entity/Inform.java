package com.yang67.outbreakservers.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "inform_info")
public class Inform {
    @TableId(value = "inform_id")
    private int informId;
    private String classId;
    private String userId;
    private String informTitle;
    private String informContent;
    private String releaseTime;
    private int deleteFlag;

    public Inform() {
    }

    public Inform(int informId, String classId, String userId, String informTitle, String informContent, String releaseTime, int deleteFlag) {
        this.informId = informId;
        this.classId = classId;
        this.userId = userId;
        this.informTitle = informTitle;
        this.informContent = informContent;
        this.releaseTime = releaseTime;
        this.deleteFlag = deleteFlag;
    }

    public int getInformId() {
        return informId;
    }

    public void setInformId(int informId) {
        this.informId = informId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInformTitle() {
        return informTitle;
    }

    public void setInformTitle(String informTitle) {
        this.informTitle = informTitle;
    }

    public String getInformContent() {
        return informContent;
    }

    public void setInformContent(String informContent) {
        this.informContent = informContent;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "Inform{" +
                "informId=" + informId +
                ", classId=" + classId +
                ", userId=" + userId +
                ", informTitle='" + informTitle + '\'' +
                ", informContent='" + informContent + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", deleteFlag=" + deleteFlag +
                '}';
    }
}
