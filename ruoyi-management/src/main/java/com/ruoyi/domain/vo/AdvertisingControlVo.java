package com.ruoyi.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
//广告控制映射结果vo
public class AdvertisingControlVo {
    //广告Id
    @Excel(name = "广告序号")
    private String id;
    //广告名称
    @Excel(name = "广告名称")
    private String name;
    //包名
    @Excel(name = "包名")
    private String pck;
    //版本号
    @Excel(name = "版本号")
    private Integer version;
    //渠道中文名
    @Excel(name = "中文名")
    private String alt;
    //开关状态（0关1开）
    @Excel(name = "开关状态")
    private Integer state;
    //二次确认开关
    @Excel(name = "二次确认")
    private Integer confirm;
    //插入/更新时间
    @Excel(name = "更新时间")
    private String ctime;
    //渠道标识
    @Excel(name = "渠道标识")
    private String channel;
}
