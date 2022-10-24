package com.ruoyi.query;

import com.ruoyi.common.annotation.Log;
import lombok.Data;

@Data
public class ReportDataForMySqlQuery {
    private Integer pageNum;
    private Integer pageSize;
    private String appName;
    private String slotName;
    private String startDate;
    private String endDate;
    //查询代码位
    private Long codeBit;

    private Boolean byDate;
    private Boolean byAppName;
    private Boolean bySlot;
    private Boolean bySlotName;
}
