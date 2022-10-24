package com.ruoyi.service;

import com.github.pagehelper.PageInfo;
import com.ruoyi.form.UpdatePlatformForm;
import com.ruoyi.query.PlatformQuery;
import com.ruoyi.vo.PlatformVo;
import com.ruoyi.vo.ResultVo;

import java.util.List;

public interface MsPlatformService {
    List<String> selectPlatformName();

    PageInfo<PlatformVo> selectPlatformList(PlatformQuery platformQuery);

    ResultVo deleteMany(Long[] deleteId);

    ResultVo updateInfo(UpdatePlatformForm updatePlatformForm);

    ResultVo insertInfo(UpdatePlatformForm updatePlatformForm);
}
