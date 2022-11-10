package com.ruoyi.controller;

import com.ruoyi.dao.PlatformMapper;
import com.ruoyi.query.DayDataListQuery;
import com.ruoyi.query.pangolinDataReport.DataReportSecondQuery;
import com.ruoyi.query.reportAPI.DailyDataQuery;
import com.ruoyi.query.reportAPI.HourDataQuery;
import com.ruoyi.service.DailyDateService;
import com.ruoyi.service.PangolinDataService;
import com.ruoyi.service.SummaryService;
import com.ruoyi.service.impl.PangolinDataServiceImpl;
import com.ruoyi.vo.PlatformVo;
import com.ruoyi.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequestMapping("dataReport")
@RestController
@Api(tags = "优量汇数据获取平台")
@Component("pull")
public class DailyDataController {
    @Autowired
    private DailyDateService dailyDateService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private SummaryService summaryService;
    @Autowired
    private PlatformMapper platformMapper;
    @Autowired
    private PangolinDataService pangolinDataService;
    @GetMapping("dayData")
    @ApiOperation(value = "获取天级数据(三方接口)" ,notes = "获取天级数据")
    @ApiImplicitParam(paramType="body",name="dailyDataQuery",value="获取天级数据",required=true,dataType="DailyDataQuery",dataTypeClass= DailyDataQuery.class)
    public ResultVo dayData(DailyDataQuery dailyDataQuery){
        return dailyDateService.dayData(dailyDataQuery);
    }

    @PostMapping("hourData")
    public ResultVo hourData(@RequestBody HourDataQuery hourDataQuery){
        return dailyDateService.hourData(hourDataQuery);
    }

    @GetMapping("selectDayDataList")
    public ResultVo selectDayDataList(DayDataListQuery dayDataListQuery){
        return dailyDateService.selectDayDataList(dayDataListQuery);
    }

    //查询媒体
    @GetMapping("selectMediaName")
    public List<String> selectMediaName(){
        return dailyDateService.selectMediaName();
    }
    @GetMapping("selectPlacementName")
    public List<String> selectPlacementName(String mediaName){
        return dailyDateService.selectPlacementName(mediaName);
    }

    //总览
    @GetMapping("overview")
    @ApiOperation(value = "查询天级数据(码上数据库)" ,notes = "查询天级数据(码上数据库)")
    @ApiImplicitParam(paramType="body",name="dayDataListQuery",value="查询天级数据(码上数据库)",required=true,dataType="DayDataListQuery",dataTypeClass= DayDataListQuery.class)
    public ResultVo selectOverview(DayDataListQuery dayDataListQuery){
        return dailyDateService.selectOverview(dayDataListQuery);
    }

//    //定时任务获取第三方数据
//    public void pullData(){
//        DailyDataQuery dailyDataQuery = new DailyDataQuery();
//        dailyDataQuery.setMember_id("800050613231");
//        dailyDataQuery.setStart_date(Integer.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).replace("-","").replace("-","")));
//        dailyDataQuery.setEnd_date(Integer.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).replace("-","").replace("-","")));
//        dailyDataQuery.setPage(1);
//        dailyDataQuery.setPage_size(20);
//        dailyDateService.dayData(dailyDataQuery);
//    }
//每天早上执行获取前一天数据
    public void anyData(){
        LocalDate localDate = LocalDate.now().minusDays(1);
        Integer date = Integer.valueOf(localDate.toString().replace("-","").replace("-",""));
        List<PlatformVo> list = platformMapper.selectPId(null);
        for (PlatformVo platformVo : list) {
            //说明是穿山甲
            if (platformVo.getType() == 0){
                DataReportSecondQuery dataReportSecondQuery = new DataReportSecondQuery();
                dataReportSecondQuery.setDate(localDate.toString());
                dataReportSecondQuery.setRole_id(platformVo.getPlatform_id());
                dataReportSecondQuery.setUser_id(platformVo.getPlatform_id());
                String s = new String(Base64.getDecoder().decode(platformVo.getSecret()));
                dataReportSecondQuery.setSign(s);
                pangolinDataService.scheduledTask(dataReportSecondQuery);
            }else if (platformVo.getType() == 1){
                DailyDataQuery dailyDataQuery = new DailyDataQuery();
                dailyDataQuery.setMember_id(platformVo.getPlatform_id().toString());
                dailyDataQuery.setStart_date(date);
                dailyDataQuery.setEnd_date(date);
                dailyDataQuery.setPage(1);
                dailyDataQuery.setPage_size(20);
                dailyDateService.scheduld(dailyDataQuery);
            }
        }
//        //判断键是否存在
//        Boolean redisKey = stringRedisTemplate.hasKey("SumAndBus");
//        if (redisKey){
//            stringRedisTemplate.opsForValue().increment("SumAndBus",1);
//            int s = Integer.parseInt(stringRedisTemplate.opsForValue().get("SumAndBus"));
//            if (s>3){
//                stringRedisTemplate.delete("SumAndBus");
//                stringRedisTemplate.opsForValue().set("SumAndBus","1");
//            }
//            if(stringRedisTemplate.opsForValue().get("SumAndBus").equals("3")){
//                summaryService.insertIntoBus();
//                stringRedisTemplate.delete("SumAndBus");
//            }
//        }else {
//            stringRedisTemplate.opsForValue().set("SumAndBus","1");
//        }
    }
    //每天早上八点半执行   0 30 8 ? * 1-7       0 0 8,10,12,14,16,18,20,22 ? * * *
    public void anyMorning(){
        LocalDate localDate = LocalDate.now().minusDays(1);
        Integer date = Integer.valueOf(localDate.toString().replace("-","").replace("-",""));
        DailyDataQuery dailyDataQuery = new DailyDataQuery();
        dailyDataQuery.setMember_id("800050613231");
        dailyDataQuery.setStart_date(date);
        dailyDataQuery.setEnd_date(date);
        dailyDataQuery.setPage(1);
        dailyDataQuery.setPage_size(20);
        dailyDateService.dayData(dailyDataQuery);
    }
}
