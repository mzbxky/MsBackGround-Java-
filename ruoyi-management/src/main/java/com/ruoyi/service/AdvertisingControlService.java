package com.ruoyi.service;

import com.ruoyi.domain.vo.ChannelVo;
import com.ruoyi.domain.vo.MediaManagementVo;
import com.ruoyi.domain.vo.SelectOneByManyVo;
import com.ruoyi.form.InsertChannelForm;
import com.ruoyi.form.InsertIntoControlForm;
import com.ruoyi.form.UpdateAdvertisingForm;
import com.ruoyi.query.AdvertisingControlQuery;
import com.ruoyi.domain.vo.AdvertisingControlVo;
import com.github.pagehelper.PageInfo;
import com.ruoyi.query.ManagementQuery;
import com.ruoyi.vo.MediaByUserVo;

import java.util.List;

public interface AdvertisingControlService {
    PageInfo<AdvertisingControlVo> selectControlList(AdvertisingControlQuery query);

    Integer updateControlList(UpdateAdvertisingForm updateAdvertisingForm);

    Integer deleteControlInfo(String[] id);

    void changeType(String[] changeId);

    Integer insert(InsertIntoControlForm insert);

    Integer insertChannel(InsertChannelForm insertChannelForm);

    void secondConfirm(String[] changeSecondConfirm);

    List<AdvertisingControlVo> selectDownLoadList(AdvertisingControlQuery query);

    List<MediaByUserVo> selectMediaByUser(String userName);

    List<ChannelVo> selectChannel();
}
