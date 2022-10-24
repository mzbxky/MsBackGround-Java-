package com.ruoyi.vo;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class DayDataListVo {
    private String id;
    private Long member_id;
    private Integer app_id;
    private String medium_name;
    private Long placement_id;
    private String placement_name;
    private String date;
    private Integer ad_request_count;
    private Integer ad_return_count;
    private Integer pv;
    private Integer click;
    private float ad_fill_rate;
    private float ad_exposure_rate;
    private float click_rate;
    private Integer revenue;
    private Integer ecpm;
}
