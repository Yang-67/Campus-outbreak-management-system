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
@TableName(value = "health_info")
public class Health implements Serializable {
    @TableId(value = "health_id")
    private Integer healthId;
    private String userId;
    private int healthState;
    private int vaccineNum;
    private float temperature;
    private String createTime;
    private String location;
    private String route;
    private int deleteFlag;

}
