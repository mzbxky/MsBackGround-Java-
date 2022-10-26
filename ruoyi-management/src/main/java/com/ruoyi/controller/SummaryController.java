package com.ruoyi.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.query.SummaryQuery;
import com.ruoyi.service.SummaryService;
import com.ruoyi.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component("summary")
@RequestMapping("Summary")
public class SummaryController {
    @Autowired
    private SummaryService summaryService;

//    定时任务，每天汇总前一天的数据
    public void test(){
        summaryService.insertIntoBus();
    }
//    页面展示数据
    @GetMapping("pageShow")
    public ResultVo pageShow(SummaryQuery summaryQuery){
        return summaryService.pageShow(summaryQuery);
    }
}
