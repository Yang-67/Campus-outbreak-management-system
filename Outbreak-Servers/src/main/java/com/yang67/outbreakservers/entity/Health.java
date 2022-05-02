package com.yang67.outbreakservers.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "health_info")
public class Health {
    @TableId(value = "health_id")
    private int healthId;
    private String userId;
    private int healthState;
    private int vaccineNum;
    private float temperature;
    private String createTime;
    private String location;
    private String route;
    private int deleteFlag;

    public Health() {
    }

    public Health(int healthId, String userId, int healthState, int vaccineNum, float temperature, String createTime, String location, String route, int deleteFlag) {
        this.healthId = healthId;
        this.userId = userId;
        this.healthState = healthState;
        this.vaccineNum = vaccineNum;
        this.temperature = temperature;
        this.createTime = createTime;
        this.location = location;
        this.route = route;
        this.deleteFlag = deleteFlag;
    }

    public int getHealthId() {
        return healthId;
    }

    public void setHealthId(int healthId) {
        this.healthId = healthId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getHealthState() {
        return healthState;
    }

    public void setHealthState(int healthState) {
        this.healthState = healthState;
    }

    public int getVaccineNum() {
        return vaccineNum;
    }

    public void setVaccineNum(int vaccineNum) {
        this.vaccineNum = vaccineNum;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "Health{" +
                "healthId=" + healthId +
                ", userId=" + userId +
                ", healthState=" + healthState +
                ", vaccineNum=" + vaccineNum +
                ", temperature=" + temperature +
                ", createTime='" + createTime + '\'' +
                ", location='" + location + '\'' +
                ", route='" + route + '\'' +
                ", deleteFlag=" + deleteFlag +
                '}';
    }
}
