package com.ruoyi.service;

import com.ruoyi.query.ReportDataForMySqlQuery;
import com.ruoyi.query.pangolinDataReport.DataReportSecondQuery;
import com.ruoyi.vo.ResultVo;

import java.util.List;

public interface PangolinDataService {
    ResultVo getDataReport(DataReportSecondQuery dataReportSecondQuery);

    ResultVo selectReportDataForMySQL(ReportDataForMySqlQuery reportDataForMySqlQuery);

    List<String> selectAppName();

    List<String> selectSlotName(String appName,Long codeBit);

    List<Long> selectCodeBit(String appName);

    ResultVo selectSumList(ReportDataForMySqlQuery reportDataForMySqlQuery);
}
