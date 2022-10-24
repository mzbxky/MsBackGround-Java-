package com.ruoyi.controller;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.domain.vo.UserInfoVo;
import com.ruoyi.domain.vo.UserVo;
import com.ruoyi.form.InsertIntoManagementForm;
import com.ruoyi.form.UpdateMediaManagementForm;
import com.ruoyi.query.ManagementQuery;
import com.ruoyi.service.ManagementService;
import com.ruoyi.domain.vo.MediaManagementVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("management")
@Api(tags = "媒体管理接口")
public class ManagementListController {
    @Autowired
    private ManagementService managementService;
    @GetMapping("selectList")
    @ApiOperation(value = "获取媒体列表" ,notes = "获取媒体列表")
    @ApiImplicitParam(paramType="body",name="managementQuery",value="媒体列表查询参数",required=true,dataType="ManagementQuery",dataTypeClass= ManagementQuery.class)
    public PageInfo<MediaManagementVo> selectList(ManagementQuery managementQuery){
        PageInfo<MediaManagementVo> pageInfo = managementService.selectList(managementQuery);
        return pageInfo;
    }
    @Transactional
    @GetMapping("updateInfo")
    @ApiOperation(value = "修改媒体信息" ,notes = "修改媒体信息")
    @ApiImplicitParam(paramType="body",name="updateMediaManagementForm",value="媒体修改参数",required=true,dataType="UpdateMediaManagementForm",dataTypeClass= UpdateMediaManagementForm.class)
    public Integer updateInfo(UpdateMediaManagementForm updateMediaManagementForm){
        Integer affectedRows = managementService.updateInfo(updateMediaManagementForm);
        return affectedRows;
    }
    @PutMapping("deleteInfo/{deleteId}")
    @ApiOperation(value = "修改媒体信息" ,notes = "修改媒体信息")
    @ApiImplicitParam(paramType="body",name="deleteId",value="删除id数组",required=true,dataType="String[]",dataTypeClass= String.class)
    public Integer deleteInfo(@PathVariable String[] deleteId){
        Integer affectedRows = managementService.deleteInfo(deleteId);
        return affectedRows;
    }
    @GetMapping("insertInfo")
    @ApiOperation(value = "添加媒体信息" ,notes = "添加媒体信息")
    @ApiImplicitParam(paramType="body",name="insertIntoManagementForm",value="添加媒体信息",required=true,dataType="InsertIntoManagementForm",dataTypeClass= InsertIntoManagementForm.class)
    public Integer insertInfo(InsertIntoManagementForm insertIntoManagementForm){
        Integer affectedRows = managementService.insertInfo(insertIntoManagementForm);
        return affectedRows;
    }
    //查询用户
    //
    @GetMapping("selectUser")
    @ApiOperation(value = "查询用户" ,notes = "查询用户")
    public List<UserVo> selectUser(){
        List<UserVo> list = managementService.selectUser();
        return list;
    }
    //根据登录的名称查询用户信息
    @GetMapping("userInfo")
    @ApiOperation(value = "查询用户信息" ,notes = "查询用户信息")
    @ApiImplicitParam(paramType="query",name="varuserName",value="查询用户信息",required=true,dataType="String",dataTypeClass= String.class)
    public List<UserInfoVo> userInfo(String varuserName){
        List<UserInfoVo> userInfoVo = managementService.userInfo(varuserName);
        return userInfoVo;
    }
    @Log(title = "岗位管理", businessType = BusinessType.EXPORT)
//    @PreAuthorize("@ss.hasPermi('management:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, ManagementQuery query)
    {
        List<MediaManagementVo> list =  managementService.selectDownLoadList(query);
        ExcelUtil<MediaManagementVo> util = new ExcelUtil<MediaManagementVo>(MediaManagementVo.class);
        util.exportExcel(response, list, "岗位数据");
    }
}
