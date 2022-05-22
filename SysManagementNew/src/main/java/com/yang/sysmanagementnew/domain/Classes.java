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
@TableName(value = "class_info")
public class Classes implements Serializable {
    @TableId(value = "class_id")
    private Integer classId;
    private String className;
    private int deleteFlag;
}
