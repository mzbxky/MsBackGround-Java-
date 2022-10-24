package com.ruoyi.service.impl;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.dao.ManagementMapper;
import com.ruoyi.domain.vo.UserInfoVo;
import com.ruoyi.domain.vo.UserVo;
import com.ruoyi.form.InsertIntoManagementForm;
import com.ruoyi.form.UpdateMediaManagementForm;
import com.ruoyi.query.ManagementQuery;
import com.ruoyi.service.ManagementService;
import com.ruoyi.domain.vo.MediaManagementVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ManagementServiceImpl implements ManagementService {
    @Autowired
    private ManagementMapper managementMapper;
    @Override
    public PageInfo<MediaManagementVo> selectList(ManagementQuery managementQuery) {
        if (managementQuery.getPageNum() == null){
            managementQuery.setPageNum(1);
        }
        if (managementQuery.getPageSize() == null){
            managementQuery.setPageSize(10);
        }
        PageHelper.startPage(managementQuery.getPageNum(),managementQuery.getPageSize());
        List<MediaManagementVo> mediaManagementVos = managementMapper.selectList(managementQuery);
        return new PageInfo<>(mediaManagementVos);
    }

    @Override
    public Integer updateInfo(UpdateMediaManagementForm updateMediaManagementForm) {
        if (updateMediaManagementForm.getVersion() == null){
            updateMediaManagementForm.setVersion("0");
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateToStr = dateFormat.format(date);
        updateMediaManagementForm.setUtime(dateToStr);
        return managementMapper.updateInfo(updateMediaManagementForm);
    }

    @Override
    public Integer deleteInfo(String[] deleteId) {
        return managementMapper.deleteInfo(deleteId);
    }

    @Override
    public Integer insertInfo(InsertIntoManagementForm insertIntoManagementForm) {
        //查询整个数据库
        List<MediaManagementVo> mediaManagementVos = managementMapper.selectList(new ManagementQuery());
        String s = UUID.randomUUID().toString();
        String uId = s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
        //设置uuid
        insertIntoManagementForm.setId(uId);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateToStr = dateFormat.format(date);
        //设置插入时间
        insertIntoManagementForm.setCtime(dateToStr);
        //设置修改时间
        insertIntoManagementForm.setUtime(dateToStr);
        //判断数据库是否为空，为空直接添加
        if (mediaManagementVos.isEmpty()){
             managementMapper.insertInfo(insertIntoManagementForm);
        }else {
            for (MediaManagementVo mediaManagementVo : mediaManagementVos) {
                //说明包名有重复的
                if (mediaManagementVo.getPkg().equals(insertIntoManagementForm.getPkg())){
                    throw new ServiceException("包名重复，请您仔细检查以后再操作检查");
                }
            }
        }
        return managementMapper.insertInfo(insertIntoManagementForm);
    }

    @Override
    public List<MediaManagementVo> selectDownLoadList(ManagementQuery query) {
        return managementMapper.selectList(query);
    }

    @Override
    public List<UserVo> selectUser() {
        return managementMapper.selectUser();
    }

    @Override
    public List<UserInfoVo> userInfo(String varuserName) {
        return managementMapper.userInfo(varuserName);
    }
}
