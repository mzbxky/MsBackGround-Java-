package com.ruoyi.query.pangolinDataReport;

import lombok.Data;

@Data
public class DataReportSecondQuery {
    private Long user_id;
    private Long role_id;
    private Integer timestamp;
    private String version;
    private String date;
    private Integer time_zone;
    private String currency;
    private String region;
    private String sign;
    private String sign_type;
}
