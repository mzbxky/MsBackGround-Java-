package com.ruoyi.vo.list;

import lombok.Data;

@Data
public class DayDataList {
    private String id;
    private Long member_id;
    private Integer app_id;
    private String medium_name;
    private Long placement_id;
    private String placement_name;
    private String date;
    private String ad_request_count;
    private String ad_return_count;
    private String pv;
    private String click;
    private String ad_fill_rate;
    private String ad_exposure_rate;
    private String click_rate;
    private Integer revenue;
    private Integer ecpm;
}
