package com.ruoyi.dao;

import com.ruoyi.query.SummaryQuery;
import com.ruoyi.vo.AnyAppOneDateDataVo;
import com.ruoyi.vo.SummaryShowVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SummaryMapper {
    List<AnyAppOneDateDataVo> AnyAppOneDateDataCSJ(@Param("userName") String userName,@Param("date") String date);

    List<AnyAppOneDateDataVo> AnyAppOneDateDataYLH(@Param("userName") String userName,@Param("date") String date);

    int insertIntoBus(@Param("list") List<AnyAppOneDateDataVo> list);

    List<SummaryShowVo> pageShow(@Param("summaryQuery") SummaryQuery summaryQuery);
}
