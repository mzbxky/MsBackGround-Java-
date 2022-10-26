package com.ruoyi.service;

import com.ruoyi.query.SummaryQuery;
import com.ruoyi.vo.ResultVo;

public interface SummaryService {

    void insertIntoBus();

    ResultVo pageShow(SummaryQuery summaryQuery);

    ResultVo pageShowUser(SummaryQuery summaryQuery);
}
