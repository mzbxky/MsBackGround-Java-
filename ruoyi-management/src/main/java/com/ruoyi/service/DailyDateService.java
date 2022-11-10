package com.ruoyi.service;

import com.ruoyi.query.DayDataListQuery;
import com.ruoyi.query.reportAPI.DailyDataQuery;
import com.ruoyi.query.reportAPI.HourDataQuery;
import com.ruoyi.vo.ResultVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DailyDateService {

    ResultVo dayData(DailyDataQuery dailyDataQuery);

    ResultVo hourData(HourDataQuery hourDataQuery);

    ResultVo selectDayDataList(DayDataListQuery dayDataListQuery);

    List<String> selectMediaName();

    List<String> selectPlacementName(@Param("mediaName") String mediaName);

    ResultVo selectOverview(DayDataListQuery dayDataListQuery);
    void scheduld(DailyDataQuery dailyDataQuery);
}
