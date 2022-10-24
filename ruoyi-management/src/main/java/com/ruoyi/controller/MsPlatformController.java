package com.ruoyi.controller;

import com.github.pagehelper.PageInfo;
import com.ruoyi.form.UpdatePlatformForm;
import com.ruoyi.query.PlatformQuery;
import com.ruoyi.service.MsPlatformService;
import com.ruoyi.vo.PlatformVo;
import com.ruoyi.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("platform")
public class MsPlatformController {
    @Autowired
    private MsPlatformService platformService;
//    查询平台名称
    @GetMapping("selectPlatformName")
    public List<String> selectPlatformName(){
        return platformService.selectPlatformName();
    }
    //查询平台列表
    @GetMapping("selectPlatformList")
    public PageInfo<PlatformVo> selectPlatformList(PlatformQuery platformQuery){
        return platformService.selectPlatformList(platformQuery);
    }
    //批量删除
    @PutMapping("deleteMany/{deleteId}")
    public ResultVo deleteMany(@PathVariable Long[] deleteId){
        return platformService.deleteMany(deleteId);
    }
    //修改
    @PostMapping("updateInfo")
    public ResultVo updateInfo(UpdatePlatformForm updatePlatformForm){
        return platformService.updateInfo(updatePlatformForm);
    }
    //添加
    @PostMapping("insertInfo")
    public ResultVo insertInfo(UpdatePlatformForm updatePlatformForm){
        return platformService.insertInfo(updatePlatformForm);
    }
}
