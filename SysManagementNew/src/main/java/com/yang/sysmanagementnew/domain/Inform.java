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
@TableName(value = "inform_info")
public class Inform implements Serializable {
    @TableId(value = "inform_id")
    private Integer informId;
    private String classId;
    private String userId;
    private String informTitle;
    private String informContent;
    private String releaseTime;
    private int deleteFlag;
}
