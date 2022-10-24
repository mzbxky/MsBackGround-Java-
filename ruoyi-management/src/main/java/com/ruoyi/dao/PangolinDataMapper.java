package com.ruoyi.dao;

import com.ruoyi.query.ReportDataForMySqlQuery;
import com.ruoyi.vo.DataReportSecondVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PangolinDataMapper {
    Integer insertPangolinService(List<DataReportSecondVo> dataReportSecondVoList);
    List<DataReportSecondVo> selectPangolinData(@Param("reportDataForMySqlQuery") ReportDataForMySqlQuery reportDataForMySqlQuery);

    List<String> selectAppName();

    List<String> selectSlotName(@Param("appName") String appName,@Param("codeBit") Long codeBit);

    List<Long> selectCodeBit(String appName);

    List<DataReportSecondVo> sumList(@Param("reportDataForMySqlQuery") ReportDataForMySqlQuery reportDataForMySqlQuery);

}
