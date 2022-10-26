package com.ruoyi.controller;

import com.ruoyi.query.ReportDataForMySqlQuery;
import com.ruoyi.service.PangolinDataService;
import com.ruoyi.query.pangolinDataReport.DataReportSecondQuery;
import com.ruoyi.vo.ResultVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("pangolin")
@Component("pangolinDataPlatform")
public class PangolinDataReportController {
    @Autowired
    private PangolinDataService pangolinDataService;
    @GetMapping("getDataReport")
    @ApiOperation(value = "穿山甲数据报告2.0" ,notes = "穿山甲数据报告2.0")
    @ApiImplicitParam(paramType="body",name="dataReportSecondQuery",value="穿山甲数据报告2.0",required=true,dataType="DataReportSecondQuery",dataTypeClass= DataReportSecondQuery.class)
    public ResultVo getDataReport(DataReportSecondQuery dataReportSecondQuery){
        return pangolinDataService.getDataReport(dataReportSecondQuery);
    }
    @GetMapping("selectReportDataForMySQL")
    public ResultVo selectReportDataForMySQL(ReportDataForMySqlQuery reportDataForMySqlQuery){
        return pangolinDataService.selectReportDataForMySQL(reportDataForMySqlQuery);
    }
    //查询应用名称
    @GetMapping("selectAppName")
    public List<String> selectAppName(){
        return pangolinDataService.selectAppName();
    }
    //查询广告位名称
    @GetMapping("selectSlotName")
    public List<String> selectSlotName(String appName,Long codeBit){
        return pangolinDataService.selectSlotName(appName,codeBit);
    }
    @GetMapping("selectCodeBit")
    //查询代码位
    public List<Long> selectCodeBit(String appName){
        return pangolinDataService.selectCodeBit(appName);
    }
//查询合计
    @GetMapping("selectSumList")
    public ResultVo selectSumList(ReportDataForMySqlQuery reportDataForMySqlQuery){
        return pangolinDataService.selectSumList(reportDataForMySqlQuery);
    }
    //每两个小时更新一次数据
    public void timingTask(){
        DataReportSecondQuery dataReportSecondQuery = new DataReportSecondQuery();
        pangolinDataService.getDataReport(dataReportSecondQuery);
    }

    //每天早上八点半更新前一天数据
    public void anyMorningUpdate(){
            DataReportSecondQuery dataReportSecondQuery = new DataReportSecondQuery();
            dataReportSecondQuery.setDate(LocalDate.now().minusDays(1).toString());
            pangolinDataService.getDataReport(dataReportSecondQuery);

    }
}
