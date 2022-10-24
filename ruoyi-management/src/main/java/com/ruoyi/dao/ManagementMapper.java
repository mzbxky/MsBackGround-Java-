package com.ruoyi.dao;

import com.ruoyi.domain.vo.UserInfoVo;
import com.ruoyi.domain.vo.UserVo;
import com.ruoyi.form.InsertIntoManagementForm;
import com.ruoyi.form.UpdateMediaManagementForm;
import com.ruoyi.query.ManagementQuery;
import com.ruoyi.domain.vo.MediaManagementVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface ManagementMapper {
    List<MediaManagementVo> selectList(@Param("managementQuery") ManagementQuery managementQuery);

    Integer updateInfo(@Param("updateMediaManagementForm") UpdateMediaManagementForm updateMediaManagementForm);

    Integer deleteInfo(String[] deleteId);

    Integer insertInfo(@Param("insertIntoManagementForm") InsertIntoManagementForm insertIntoManagementForm);

    List<UserVo> selectUser();

    List<UserInfoVo> userInfo(@Param("varuserName") String varuserName);
}
