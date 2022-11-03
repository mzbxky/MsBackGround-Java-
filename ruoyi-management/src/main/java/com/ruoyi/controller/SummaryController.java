package com.ruoyi.controller;

import com.ruoyi.query.SummaryQuery;
import com.ruoyi.service.SummaryService;
import com.ruoyi.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component("summary")
@RequestMapping("Summary")
@Api(tags = "数据收益管理接口")
public class SummaryController {
    @Autowired
    private SummaryService summaryService;

    //定时任务，每天汇总前一天的所有数据
    public void beforeDay(){
        summaryService.insertIntoBus();
    }

    //页面展示数据(每个应用每天)
    @ApiOperation(value = "应用收益" ,notes = "应用收益")
    @ApiImplicitParam(paramType="query",name="summaryQuery",value="应用收益",required=true,dataType="SummaryQuery",dataTypeClass= SummaryQuery.class)
    @GetMapping("pageShow")
    public ResultVo pageShow(SummaryQuery summaryQuery){
        return summaryService.pageShow(summaryQuery);
    }

    //页面展示数据（每个用户每天）
    @ApiOperation(value = "每日收益" ,notes = "每日收益")
    @ApiImplicitParam(paramType="query",name="summaryQuery",value="每日收益",required=true,dataType="SummaryQuery",dataTypeClass= SummaryQuery.class)
    @GetMapping("pageShowUser")
    public ResultVo pageShowUser(SummaryQuery summaryQuery){
        return summaryService.pageShowUser(summaryQuery);
    }
}
