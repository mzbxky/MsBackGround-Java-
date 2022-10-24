package com.ruoyi.query;

import lombok.Data;

@Data
public class DayDataListQuery {
    private Integer pageNum;
    private Integer pageSize;
    private String mediaName;
    private String placementName;
    private String startDate;
    private String endDate;
    private Boolean byDate;
    private Boolean byMediumName;
    private Boolean byPlacementName;
}
