package com.ruoyi.dao;

import com.ruoyi.domain.vo.ChannelVo;
import com.ruoyi.domain.vo.SelectOneByManyVo;
import com.ruoyi.form.InsertChannelForm;
import com.ruoyi.form.InsertIntoControlForm;
import com.ruoyi.form.UpdateAdvertisingForm;
import com.ruoyi.query.AdvertisingControlQuery;
import com.ruoyi.domain.vo.AdvertisingControlVo;
import com.ruoyi.vo.MediaByUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
@Mapper
@Repository
public interface AdvertisingControlMapper {
    List<AdvertisingControlVo> selectControlList(@Param("query") AdvertisingControlQuery query);

    Integer updateControlList(@Param("updateAdvertisingForm") UpdateAdvertisingForm updateAdvertisingForm);

    Integer deleteControlInfo(String[] id);

    void changeType(String[] changeId);

    void changeSecondConfirm(String[] id);

    Integer insert(@Param("insertInfo") List<InsertIntoControlForm> insertInfo);

    Integer insertChannel(@Param("insertChannelForm") InsertChannelForm insertChannelForm);

    List<MediaByUserVo> selectMediaByUser(@Param("userName") String userName);
    //查询渠道
    List<ChannelVo> selectChannel();
}
