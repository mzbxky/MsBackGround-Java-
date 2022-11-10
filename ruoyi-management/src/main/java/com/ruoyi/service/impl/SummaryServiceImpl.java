package com.ruoyi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.dao.SummaryMapper;
import com.ruoyi.query.SummaryQuery;
import com.ruoyi.service.SummaryService;
import com.ruoyi.vo.AnyAppOneDateDataVo;
import com.ruoyi.vo.AnyUserAnyDateVo;
import com.ruoyi.vo.ResultVo;
import com.ruoyi.vo.SummaryShowVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SummaryServiceImpl implements SummaryService {
    @Autowired
    private SummaryMapper summaryMapper;

    @Override
    public void insertIntoBus(){
        LocalDate localDate = LocalDate.now().minusDays(1);
        List<AnyAppOneDateDataVo> operating = summaryMapper.AnyAppOneDateDataCSJ(localDate.toString());
        summaryMapper.insertIntoBus(operating);
        List<AnyAppOneDateDataVo> operating1 = summaryMapper.AnyAppOneDateDataYLH(localDate.toString());
        summaryMapper.insertIntoBus(operating1);
        List<AnyUserAnyDateVo> anyUserAnyDateVos = summaryMapper.selectAnyUser(localDate.toString());
        summaryMapper.insertIntoSum(anyUserAnyDateVos);
    }

    @Override
    public ResultVo pageShow(SummaryQuery summaryQuery) {
        PageHelper.startPage(summaryQuery.getPageNum(), summaryQuery.getPageSize());
        List<SummaryShowVo> summaryShowVos = summaryMapper.pageShow(summaryQuery);
        PageInfo<SummaryShowVo> pageInfo = new PageInfo<>(summaryShowVos);
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(-1);
        resultVo.setMsg("暂无数据");
        if (summaryShowVos.size()!= 0){
            resultVo.setCode(0);
            resultVo.setMsg("查询成功");
            resultVo.setData(pageInfo);
        }
        return resultVo;
    }

    @Override
    public ResultVo pageShowUser(SummaryQuery summaryQuery) {
        PageHelper.startPage(summaryQuery.getPageNum(),summaryQuery.getPageSize());
        List<SummaryShowVo> summaryShowVos = summaryMapper.pageShowUser(summaryQuery);
        PageInfo<SummaryShowVo> pageInfo = new PageInfo<>(summaryShowVos);
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(-1);
        resultVo.setMsg("暂无数据");
        if (summaryShowVos .size() >0){
            resultVo.setCode(0);
            resultVo.setMsg("查询成功");
            resultVo.setData(pageInfo);
        }
        return resultVo;
    }

    @Override
    public ResultVo indexChart(SummaryQuery summaryQuery) {
        List<SummaryShowVo> summaryShowVos = summaryMapper.indexChart(summaryQuery);
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(-1);
        resultVo.setMsg("暂无数据");
        if (summaryShowVos .size() >0){
            resultVo.setCode(0);
            resultVo.setMsg("查询成功");
            resultVo.setData(summaryShowVos);
        }
        return resultVo;
    }
}
