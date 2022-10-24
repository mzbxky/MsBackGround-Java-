package com.ruoyi.dao;

import com.ruoyi.query.DayDataListQuery;
import com.ruoyi.vo.DayDataListVo;
import com.ruoyi.vo.list.DayDataList;
import com.ruoyi.vo.list.HourDataList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DailyDateMapper {
    int insertDayList(@Param("list") List<DayDataList> list);

    int insertHourList(@Param("list") List<HourDataList> list);

    List<DayDataListVo> selectDayData(@Param("dayDataListQuery") DayDataListQuery dayDataListQuery);

    List<String> selectMediaName();

    List<String> selectPlacementName(@Param("mediaName") String mediaName);

    List<DayDataListVo> selectOverview(@Param("dayDataListQuery") DayDataListQuery dayDataListQuery);
}
