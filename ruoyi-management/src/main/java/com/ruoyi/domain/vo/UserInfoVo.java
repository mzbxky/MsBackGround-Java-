package com.ruoyi.domain.vo;

import lombok.Data;

//登录用户信息
@Data
public class UserInfoVo {
    //登录用户的id
    private Integer userId;
    //角色权限字符
    private String roleKey;
}
