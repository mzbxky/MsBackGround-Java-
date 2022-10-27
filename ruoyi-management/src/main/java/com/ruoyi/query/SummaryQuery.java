package com.ruoyi.query;

import lombok.Data;

@Data
public class SummaryQuery {
    private Integer pageNum;
    private Integer pageSize;
    private String pkg;
    private String appName;
    private String startDate;
    private String endDate;
    private String userId;
}
