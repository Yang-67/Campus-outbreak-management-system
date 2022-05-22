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
@TableName(value = "user_info")
public class User implements Serializable {
    @TableId(value = "user_id")
    private String userId;
    private String userName;
    private String userPwd;
    private int userSex;
    private String userEmail;
    private String userAddress;
    private String createTime;
    private String userUrl;
    private String classId;
    private int identity;
    private int deleteFlag;
}
