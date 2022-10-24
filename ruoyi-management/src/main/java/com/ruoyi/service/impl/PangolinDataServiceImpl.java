package com.ruoyi.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.dao.PangolinDataMapper;
import com.ruoyi.query.ReportDataForMySqlQuery;
import com.ruoyi.query.pangolinDataReport.DataReportSecondQuery;
import com.ruoyi.service.PangolinDataService;
import com.ruoyi.util.HttpClientUtil;
import com.ruoyi.vo.DataReportSecondVo;
import com.ruoyi.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map;
import java.util.Set;

@Service
public class PangolinDataServiceImpl implements PangolinDataService {
    @Autowired
    private PangolinDataMapper pangolinDataMapper;
    public final static String URL = "https://open-api.csjplatform.com/union_media/open_api/rt/income";
    //    获取sign
    public static String getSign(Map<String, String> request, String token){
        Set<Map.Entry<String, String>> entries=request.entrySet();
        StringBuilder str= new StringBuilder();
        int i=0;
        for (Map.Entry<String,String> e:entries){
            i++;
            str.append(e.getKey());
            str.append("=");
            str.append(e.getValue());
            if (i != request.size()){
                str.append("&");
            }
        }
        str.append(token);
        return getMD5Str(str.toString());
    }
    public static String getMD5Str(String str){
        byte[] digest = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest = md5.digest(str.getBytes(StandardCharsets.UTF_8));
        }catch (Exception e){
            e.printStackTrace();
        }
        StringBuilder md5StrBuilder = new StringBuilder();
        for (byte tmp: Objects.requireNonNull(digest)){
            md5StrBuilder.append(String.format("%02x", tmp&0xff));
        }
        return md5StrBuilder.toString();
    }
    @Override
    public ResultVo getDataReport(DataReportSecondQuery dataReportSecondQuery) {
        String date;
        if (dataReportSecondQuery.getDate() == null || dataReportSecondQuery.getDate().equals("")){
            date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        }else {
            date = dataReportSecondQuery.getDate();
        }
        TreeMap<String,String> param=new TreeMap();
        param.put("user_id","69197");
        param.put("role_id","69197");
        param.put("sign_type","MD5");
        param.put("version","2.0");
        param.put("timestamp",String.valueOf(System.currentTimeMillis()));//"1664510309902L"
        param.put("date",date);
        param.put("region","cn");
        param.put("currency","cny");
        param.put("time_zone","8");
        String sign = getSign(param,"880ab0f2df2e0ba2cc82c9b0c419fc0d");
        param.put("sign",sign);
        JSONObject jsonObject = HttpClientUtil.doGet(URL, param);
            List o = (List)jsonObject.get(date);
        List<DataReportSecondVo> dataReportSecondVoList = new ArrayList<>();
        for (Object o1 : o) {
            DataReportSecondVo dataReportSecondVo;
            dataReportSecondVo = JSONObject.toJavaObject((JSON) o1, DataReportSecondVo.class);
            dataReportSecondVo.setId(UUID.randomUUID().toString().replace("-", "").replace(" ", "").substring(0,16));
            switch (dataReportSecondVo.getAd_slot_type()){
                case 1:
                    dataReportSecondVo.setSlot_name("信息流");break;
                case 2:
                    dataReportSecondVo.setSlot_name("Banner");break;
                case 3:
                    dataReportSecondVo.setSlot_name("开屏");break;
                case 4:
                    dataReportSecondVo.setSlot_name("插屏");break;
                case 5:
                    dataReportSecondVo.setSlot_name("激励视频");break;
                case 6:
                    dataReportSecondVo.setSlot_name("全屏视频");break;
                case 7:
                    dataReportSecondVo.setSlot_name("Draw信息流");break;
                case 8:
                    dataReportSecondVo.setSlot_name("贴片");break;
                case 9:
                    dataReportSecondVo.setSlot_name("新插屏广告");break;
            }
            dataReportSecondVoList.add(dataReportSecondVo);
        }
        pangolinDataMapper.insertPangolinService(dataReportSecondVoList);
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(-1);
        resultVo.setMsg("暂无数据");
        if (dataReportSecondVoList.size() > 0){
            resultVo.setCode(0);
            resultVo.setMsg("拉取成功");
            resultVo.setData(jsonObject);
        }
        return resultVo;
    }

    @Override
    public ResultVo selectReportDataForMySQL(ReportDataForMySqlQuery reportDataForMySqlQuery) {
        PageHelper.startPage(reportDataForMySqlQuery.getPageNum(), reportDataForMySqlQuery.getPageSize());
        List<DataReportSecondVo> dataReportSecondVoList = pangolinDataMapper.selectPangolinData(reportDataForMySqlQuery);
        PageInfo<DataReportSecondVo> pageInfo = new PageInfo<>(dataReportSecondVoList);
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(-1);
        resultVo.setMsg("暂无数据");
        if (dataReportSecondVoList.size() > 0){
            resultVo.setData(pageInfo);
            resultVo.setCode(0);
            resultVo.setMsg("查询成功");
        }
        return resultVo;
    }

    @Override
    public List<String> selectAppName() {
        return pangolinDataMapper.selectAppName();
    }

    @Override
    public List<String> selectSlotName(String appName,Long codeBit) {
        return pangolinDataMapper.selectSlotName(appName,codeBit);
    }

    @Override
    public List<Long> selectCodeBit(String appName) {
        return pangolinDataMapper.selectCodeBit(appName);
    }

    @Override
    public ResultVo selectSumList(ReportDataForMySqlQuery reportDataForMySqlQuery) {
        PageHelper.startPage(reportDataForMySqlQuery.getPageNum(), reportDataForMySqlQuery.getPageSize());
        List<DataReportSecondVo> dataReportSecondVoList = pangolinDataMapper.sumList(reportDataForMySqlQuery);
        PageInfo<DataReportSecondVo> pageInfo = new PageInfo<>(dataReportSecondVoList);
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(-1);
        resultVo.setMsg("暂无数据");
        if (dataReportSecondVoList.size() > 0){
            resultVo.setData(pageInfo);
            resultVo.setCode(0);
            resultVo.setMsg("查询成功");
        }
        return resultVo;
    }
}
