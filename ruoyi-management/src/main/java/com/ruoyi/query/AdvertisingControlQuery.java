package com.ruoyi.query;

import lombok.Data;

//表单提交数据
@Data
public class AdvertisingControlQuery {
    //分页参数
    private Integer pageNum;
    private Integer pageSize;
    //查询id
    private String id;
    //包名
    private String pck;
    private String channel;
    //时间
    private String NowTime;
    //渠道标识
    private String name;
    //媒体
    private String mediaName;
    //所属用户
    private String userName;
    //应用名
    private String appName;
}
