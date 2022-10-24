package com.ruoyi.form;

import lombok.Data;

@Data
public class InsertIntoManagementForm {
    //媒体id
    private String id;
    //媒体名称
    private String name;
    //包名
    private String pkg;
    //别名
    private String nick;
    //版本
    private String version;
    //修改时间
    private String utime;
    //状态
    private String state;
    //平台C
    private String cid;
    //平台T
    private String tid;
    //所属用户
    private String userName;
    //软件名
    private String exp;
    //创建时间
    private String ctime;
}
