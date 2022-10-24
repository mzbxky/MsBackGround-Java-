package com.ruoyi.vo;

import com.ruoyi.vo.list.DayDataList;
import lombok.Data;

import java.util.List;

@Data
public class DayVo {
    private String last_updated_date;
    private PageInfoVo page_info;
    private List<DayDataList> list;
}
