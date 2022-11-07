package com.ruoyi.controller;

import com.github.pagehelper.PageInfo;
import com.ruoyi.form.UpdatePlatformForm;
import com.ruoyi.query.PlatformQuery;
import com.ruoyi.query.SummaryQuery;
import com.ruoyi.service.MsPlatformService;
import com.ruoyi.vo.PlatformVo;
import com.ruoyi.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("platform")
@Api(tags = "平台信息管理接口")
public class MsPlatformController {
    @Autowired
    private MsPlatformService platformService;
    //查询平台名称
    @GetMapping("selectPlatformName")
    public List<String> selectPlatformName(){
        return platformService.selectPlatformName();
    }

    //查询平台列表
    @GetMapping("selectPlatformList")
    @ApiOperation(value = "平台信息列表" ,notes = "平台信息列表")
    @ApiImplicitParam(paramType="query",name="platformQuery",value="平台信息列表",required=true,dataType="PlatformQuery",dataTypeClass= PlatformQuery.class)
    public PageInfo<PlatformVo> selectPlatformList(PlatformQuery platformQuery){
        return platformService.selectPlatformList(platformQuery);
    }

    //批量删除
    @PutMapping("deleteMany/{deleteId}")
    @ApiOperation(value = "平台信息删除" ,notes = "平台信息删除")
    @ApiImplicitParam(paramType="body",name="deleteId",value="平台信息删除",required=true,dataType="Long",dataTypeClass= Long.class)
    public ResultVo deleteMany(@PathVariable Long[] deleteId){
        return platformService.deleteMany(deleteId);
    }

    //修改
    @Transactional
    @PostMapping("updateInfo")
    @ApiOperation(value = "平台信息修改" ,notes = "平台信息修改")
    @ApiImplicitParam(paramType="query",name="updatePlatformForm",value="平台信息修改",required=true,dataType="UpdatePlatformForm",dataTypeClass= UpdatePlatformForm.class)
    public ResultVo updateInfo(UpdatePlatformForm updatePlatformForm){
        return platformService.updateInfo(updatePlatformForm);
    }

    //添加
    @PostMapping("insertInfo")
    @ApiOperation(value = "平台信息添加" ,notes = "平台信息添加")
    @ApiImplicitParam(paramType="query",name="updatePlatformForm",value="平台信息添加",required=true,dataType="UpdatePlatformForm",dataTypeClass= UpdatePlatformForm.class)
    public ResultVo insertInfo(UpdatePlatformForm updatePlatformForm){
        return platformService.insertInfo(updatePlatformForm);
    }
}
