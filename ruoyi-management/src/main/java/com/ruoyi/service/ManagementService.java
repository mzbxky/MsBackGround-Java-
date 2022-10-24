package com.ruoyi.service;

import com.ruoyi.domain.vo.UserInfoVo;
import com.ruoyi.domain.vo.UserVo;
import com.ruoyi.form.InsertIntoManagementForm;
import com.ruoyi.form.UpdateMediaManagementForm;
import com.ruoyi.query.ManagementQuery;
import com.ruoyi.domain.vo.MediaManagementVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ManagementService {
    PageInfo<MediaManagementVo> selectList(ManagementQuery managementQuery);

    Integer updateInfo(UpdateMediaManagementForm updateMediaManagementForm);

    Integer deleteInfo(String[] deleteId);

    Integer insertInfo(InsertIntoManagementForm insertIntoManagementForm);

    List<MediaManagementVo> selectDownLoadList(ManagementQuery query);

    List<UserVo> selectUser();

    List<UserInfoVo> userInfo(String varuserName);
}
