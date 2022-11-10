package com.ruoyi.vo;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class DataReportSecondVo {
    //id
    private String id;
    //应用id
    private Integer app_id;
    //应用名称
    private String app_name;
    //包名
    private String package_name;
    //代码位id
    private Integer ad_slot_id;

    private Integer ad_slot_type;
    private String slot_name;
    private Integer request;
    private Integer response;
    //曝光量
    private Integer show;
    private Integer click;
    private Float click_rate;
    private Float revenue;
    private Float ecpm;
    private Integer use_mediation;
    private String date;
    //平台账号id
    private Long memberid;
}
