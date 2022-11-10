package com.ruoyi.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class UpdateAdvertisingForm {
    //广告Id
    private String id;
    //广告名称
    private String name;
    //包名
    private String pck;
    //版本号
    private Integer version;
    //渠道中文名
    private String alt;
    //开关状态（0关1开）
    private Integer state;
    //二次确认开关
    private Integer confirm;
    //插入/更新时间
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private String ctime;
    private String utime;
    //渠道标识符
    private String channel;
}
