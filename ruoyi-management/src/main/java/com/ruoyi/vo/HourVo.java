package com.ruoyi.vo;

import com.ruoyi.vo.list.HourDataList;
import lombok.Data;

import java.util.List;

@Data
public class HourVo {
    private List<HourDataList> list;
    private PageInfoVo pageInfoVo;
}
