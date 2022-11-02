package com.ruoyi.controller;

import com.ruoyi.query.DayDataListQuery;
import com.ruoyi.query.reportAPI.DailyDataQuery;
import com.ruoyi.query.reportAPI.HourDataQuery;
import com.ruoyi.service.DailyDateService;
import com.ruoyi.service.SummaryService;
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
import java.util.Date;
import java.util.List;

@RequestMapping("dataReport")
@RestController
@Api("MediaApi")
@Component("pull")
public class DailyDataController {
    @Autowired
    private DailyDateService dailyDateService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private SummaryService summaryService;
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
    @ApiOperation(value = "查询天级数据(码上数据库)" ,notes = "查询天级数据(码上数据库)")
    @ApiImplicitParam(paramType="body",name="dayDataListQuery",value="查询天级数据(码上数据库)",required=true,dataType="DayDataListQuery",dataTypeClass= DayDataListQuery.class)
    public ResultVo selectDayDataList(DayDataListQuery dayDataListQuery){
        return dailyDateService.selectDayDataList(dayDataListQuery);
    }

//    查询媒体
    @GetMapping("selectMediaName")
    public List<String> selectMediaName(){
        return dailyDateService.selectMediaName();
    }
    @GetMapping("selectPlacementName")
    public List<String> selectPlacementName(String mediaName){
        return dailyDateService.selectPlacementName(mediaName);
    }
//    总览
    @GetMapping("overview")
    public ResultVo selectOverview(DayDataListQuery dayDataListQuery){
        return dailyDateService.selectOverview(dayDataListQuery);
    }
    //定时任务获取第三方数据
    public void pullData(){
        DailyDataQuery dailyDataQuery = new DailyDataQuery();
        dailyDataQuery.setMember_id("800050613231");
        dailyDataQuery.setStart_date(Integer.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).replace("-","").replace("-","")));
        dailyDataQuery.setEnd_date(Integer.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).replace("-","").replace("-","")));
        dailyDataQuery.setPage(1);
        dailyDataQuery.setPage_size(20);
        dailyDateService.dayData(dailyDataQuery);
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
            //判断键是否存在
        Boolean redisKey = stringRedisTemplate.hasKey("YLH");
        if (redisKey){
            stringRedisTemplate.opsForValue().increment("YLH",1);
            if(stringRedisTemplate.opsForValue().get("YLH").equals("3")&&stringRedisTemplate.opsForValue().get("CSJ").equals("3")){
                summaryService.insertIntoBus();
                stringRedisTemplate.delete("YLH");
                stringRedisTemplate.delete("CSJ");
            }
        }else {
            stringRedisTemplate.opsForValue().set("YLH","1");
        }
    }
}
