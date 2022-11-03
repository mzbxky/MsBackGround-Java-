package com.ruoyi.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.domain.vo.ChannelVo;
import com.ruoyi.form.InsertChannelForm;
import com.ruoyi.form.InsertIntoControlForm;
import com.ruoyi.form.UpdateAdvertisingForm;
import com.ruoyi.query.AdvertisingControlQuery;
import com.ruoyi.service.AdvertisingControlService;
import com.ruoyi.domain.vo.AdvertisingControlVo;
import com.github.pagehelper.PageInfo;
import com.ruoyi.vo.MediaByUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/advertisingControl")
@Component("advertisingControl")
@Api(tags = "广告管理接口")
public class AdvertisingControlController {
    @Autowired
    private AdvertisingControlService advertisingControlService;

    @GetMapping("/selectControlList")
    @ResponseBody
    @ApiOperation(value = "获取广告管理列表" ,notes = "获取广告管理列表")
    @ApiImplicitParam(paramType="body",name="query",value="广告列表查询参数",required=true,dataType="AdvertisingControlQuery",dataTypeClass=AdvertisingControlQuery.class)
    public PageInfo<AdvertisingControlVo> selectControlList(AdvertisingControlQuery query){
        return advertisingControlService.selectControlList(query);
    }

    @Transactional
    @GetMapping("updateControlList")
    @ApiOperation(value = "修改广告广告信息" ,notes = "修改广告广告信息")
    @ApiImplicitParam(paramType="body",name="updateAdvertisingForm",value="广告信息修改",required=true,dataType="UpdateAdvertisingForm",dataTypeClass=UpdateAdvertisingForm.class)
    public Integer updateControlList(UpdateAdvertisingForm updateAdvertisingForm){
        return advertisingControlService.updateControlList(updateAdvertisingForm);
    }

    @PutMapping("deleteControlInfo/{deleteId}")
    @ApiOperation(value = "广告信息删除" ,notes = "广告信息删除")
    @ApiImplicitParam(paramType="body",name="deleteId",value="广告信息删除",required=true,dataType="String[]",dataTypeClass=String.class)
    public Integer deleteControlInfo(@PathVariable String[] deleteId){
        return advertisingControlService.deleteControlInfo(deleteId);
    }

    @Transactional
    @PutMapping("changeType/{changeId}")
    @ApiOperation(value = "广告开关" ,notes = "广告开关")
    @ApiImplicitParam(paramType="path",name="changeId",value="广告开关",required=true,dataType="String[]",dataTypeClass=String.class)
    public void changeType(@PathVariable String[] changeId){
        advertisingControlService.changeType(changeId);
    }

    //二次确认
    @Transactional
    @PutMapping("changeSecondConfirm/{changeSecondConfirm}")
    @ApiOperation(value = "二次确认" ,notes = "二次确认")
    @ApiImplicitParam(paramType="path",name="changeSecondConfirm",value="二次确认",required=true,dataType="String[]",dataTypeClass=String.class)
    public void secondConfirm(@PathVariable String[] changeSecondConfirm){
        advertisingControlService.secondConfirm(changeSecondConfirm);
    }

    @GetMapping ("insert")
    @ApiOperation(value = "广告信息添加" ,notes = "广告信息添加")
    @ApiImplicitParam(paramType="body",name="insert",value="广告信息添加",required=true,dataType="InsertIntoControlForm",dataTypeClass=InsertIntoControlForm.class)
    public Integer insert(InsertIntoControlForm insert) {
        return advertisingControlService.insert(insert);
    }

    @GetMapping("insertChannel")
    public Integer insertChannel(InsertChannelForm insertChannelForm){
        return advertisingControlService.insertChannel(insertChannelForm);
    }

    //根据用户查询所属媒体
    @GetMapping("selectMediaByUser")
    @ApiOperation(value = "根据用户查询所属媒体" ,notes = "根据用户查询所属媒体")
    @ApiImplicitParam(paramType="query",name="userName",value="根据用户查询所属媒体",required=true,dataType="String",dataTypeClass=String.class)
    public List<MediaByUserVo> selectMediaByUser(String userName){
        return advertisingControlService.selectMediaByUser(userName);
    }

    //查询渠道
    @GetMapping("selectChannel")
    public List<ChannelVo> selectChannel(){
        return advertisingControlService.selectChannel();
    }

    @Log(title = "广告控制管理", businessType = BusinessType.EXPORT)
    //@PreAuthorize("@ss.hasPermi('management:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdvertisingControlQuery query) {
        List<AdvertisingControlVo> list =  advertisingControlService.selectDownLoadList(query);
        ExcelUtil<AdvertisingControlVo> util = new ExcelUtil<AdvertisingControlVo>(AdvertisingControlVo.class);
        util.exportExcel(response, list, "广告数据");
    }
}
