package com.ruoyi.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.dao.DailyDateMapper;
import com.ruoyi.query.DayDataListQuery;
import com.ruoyi.query.reportAPI.DailyDataQuery;
import com.ruoyi.query.reportAPI.HourDataQuery;
import com.ruoyi.service.DailyDateService;
import com.ruoyi.util.GetTokenUtil;
import com.ruoyi.util.HttpClientUtil;
import com.ruoyi.vo.DayDataListVo;
import com.ruoyi.vo.DayVo;
import com.ruoyi.vo.HourVo;
import com.ruoyi.vo.ResultVo;
import com.ruoyi.vo.list.DayDataList;
import com.ruoyi.vo.list.HourDataList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class DailyDateServiceImpl implements DailyDateService {
    //测试路径
    //private static String URL = "https://test-api.adnet.qq.com/open/v1.1/report/get";
    //正式路径
    private final static String URL = "https://api.adnet.qq.com/open/v1.1/report/";
    //token
    private static String TOKEN = null;
    //生成token的时间
    private static Long CREATE_TIME = 0L;
    /*获取token*/
    public static String getToken(){
        if (TOKEN == null || CREATE_TIME == 0L || new Date().getTime() - CREATE_TIME > 1000 * 20 * 60){
            TOKEN = GetTokenUtil.getToken();
            CREATE_TIME = new Date().getTime();
            return TOKEN;
        }
        return TOKEN;
    }
    @Autowired
    private DailyDateMapper dailyDateMapper;

    public ResultVo getResultVo(Object data,int insert,Class clazz){
        ResultVo resultVo = new ResultVo();
        resultVo.setData(data);
        resultVo.setCode(0);
        resultVo.setMsg("ok!");
        if (clazz == DayVo.class){
            if (((DayVo) data).getList() == null){
                resultVo.setCode(-1);
                resultVo.setMsg("请求失败或数据不存在");
            }else if (insert <= 0){
                resultVo.setCode(-1);
                resultVo.setMsg("存入数据库失败或目前不存在数据");
            }
        }if (clazz == HourVo.class){
            if (((HourVo) data).getList() == null){
                resultVo.setCode(-1);
                resultVo.setMsg("请求失败或数据不存在");
            }else if (insert <= 0){
                resultVo.setCode(-1);
                resultVo.setMsg("存入数据库失败");
            }
        }
        return resultVo;
    }
    @Override
    public ResultVo dayData(DailyDataQuery dailyDataQuery) {
        dailyDataQuery.setToken(getToken());
        dailyDataQuery.setMember_id("800050613231");
        if (dailyDataQuery.getStart_date() == null || dailyDataQuery.getEnd_date() == null){
            LocalDate localDate = LocalDate.now().minusDays(0);
            Integer date = Integer.valueOf(localDate.toString().replace("-","").replace("-",""));
            dailyDataQuery.setStart_date(date);
            dailyDataQuery.setEnd_date(date);
        }
        JSONObject jsonObject = HttpClientUtil.doGet(URL+"get", HttpClientUtil.getObject(dailyDataQuery));
        DayVo data = JSONObject.toJavaObject(jsonObject, DayVo.class);
        int insert = 0;
        if (data.getList() !=null){
            List<DayDataList> list = data.getList();
            for (DayDataList dayDataList : list) {
                dayDataList.setId(UUID.randomUUID().toString().replace("-", "").replace(" ", "").substring(0,16));
            }
            list.remove(0);
            data.setLast_updated_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"");
            if (!list.isEmpty()){
                insert = dailyDateMapper.insertDayList(list);
            }
        }
        return getResultVo(data,insert,DayVo.class);
    }

    @Override
    public ResultVo hourData(HourDataQuery hourDataQuery) {
        JSONObject jsonObject = HttpClientUtil.doPost(URL+"hourly",HttpClientUtil.getObject(hourDataQuery));
        HourVo data = JSONObject.toJavaObject(jsonObject, HourVo.class);
        int insert = 0;
        if (data.getList() != null){
            List<HourDataList> list = data.getList();
            for (HourDataList hourDataList : list) {
                hourDataList.setId(UUID.randomUUID().toString().replace("-", "").replace(" ", "").substring(0,16));
            }
            insert = dailyDateMapper.insertHourList(list);
        }
        return getResultVo(data,insert,HourVo.class);
    }

    @Override
    public ResultVo selectDayDataList(DayDataListQuery dayDataListQuery) {
        PageHelper.startPage(dayDataListQuery.getPageNum(),dayDataListQuery.getPageSize());
        List<DayDataListVo> list = dailyDateMapper.selectDayData(dayDataListQuery);
        PageInfo<DayDataListVo> pageInfo = new PageInfo<>(list);
        ResultVo resultVo = new ResultVo();
        resultVo.setMsg("数据不存在");
        resultVo.setCode(-1);
        if (list.size()>0){
            resultVo.setMsg("请求成功");
            resultVo.setCode(0);
            resultVo.setData(pageInfo);
        }
        return resultVo;
    }

    @Override
    public List<String> selectMediaName() {
        return dailyDateMapper.selectMediaName();
    }

    @Override
    public List<String> selectPlacementName(String mediaName) {
        return dailyDateMapper.selectPlacementName(mediaName);
    }

    @Override
    public ResultVo selectOverview(DayDataListQuery dayDataListQuery) {
        PageHelper.startPage(dayDataListQuery.getPageNum(),dayDataListQuery.getPageSize());
        List<DayDataListVo> list = dailyDateMapper.selectOverview(dayDataListQuery);
        PageInfo<DayDataListVo> pageInfo = new PageInfo<>(list);
        ResultVo resultVo = new ResultVo();
        resultVo.setMsg("数据不存在");
        resultVo.setCode(-1);
        if (list.size()>0){
            resultVo.setMsg("请求成功");
            resultVo.setCode(0);
            resultVo.setData(pageInfo);
        }
        return resultVo;
    }

}
