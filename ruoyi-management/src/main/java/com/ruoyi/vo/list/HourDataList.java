package com.ruoyi.vo.list;

import lombok.Data;

@Data
public class HourDataList {
    private String id;
    private String date;
    private Long hour;
    private Long app_id;
    private String medium_name;
    private Long placement_id;
    private String placement_name;
    private Double revenue;
    private Double ecpm;
    private String pv;
    private String click;
    private String click_rate;
}
