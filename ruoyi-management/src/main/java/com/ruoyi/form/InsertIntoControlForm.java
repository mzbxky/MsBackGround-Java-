package com.ruoyi.form;

import lombok.Data;

//新增form表单
@Data
public class InsertIntoControlForm {
    private String id;
    //应用名称
    private String name;
    //包名
    private String pck;
    //版本号
    private Integer version;
    //渠道号
    private String channel;
    //开关状态
    private Integer state;
    //二次确认
    private Integer confirm;
    //插入时间
    private String ctime;
    //媒体名称
    private String mediaName;
}
