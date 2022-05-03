package com.yang67.outbreakservers.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "class_info")
public class Classes {
    @TableId(value = "class_id")
    private int classId;
    private String className;
    private int deleteFlag;

    public Classes() {
    }

    public Classes(int classId, String className, int deleteFlag) {
        this.classId = classId;
        this.className = className;
        this.deleteFlag = deleteFlag;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", deleteFlag=" + deleteFlag +
                '}';
    }
}
