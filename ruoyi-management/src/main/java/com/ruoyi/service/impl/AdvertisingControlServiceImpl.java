package com.ruoyi.service.impl;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.dao.AdvertisingControlMapper;
import com.ruoyi.domain.vo.ChannelVo;
import com.ruoyi.form.InsertChannelForm;
import com.ruoyi.form.InsertIntoControlForm;
import com.ruoyi.form.UpdateAdvertisingForm;
import com.ruoyi.query.AdvertisingControlQuery;
import com.ruoyi.service.AdvertisingControlService;
import com.ruoyi.domain.vo.AdvertisingControlVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.vo.MediaByUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class AdvertisingControlServiceImpl implements AdvertisingControlService {
    @Autowired
    private AdvertisingControlMapper advertisingControlMapper;

    @Override
    public PageInfo<AdvertisingControlVo> selectControlList(AdvertisingControlQuery query) {
        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        List<AdvertisingControlVo> advertisingControlVos = advertisingControlMapper.selectControlList(query);
        return new PageInfo<>(advertisingControlVos);
    }

    @Override
    public Integer updateControlList(UpdateAdvertisingForm updateAdvertisingForm) {
        if (updateAdvertisingForm.getChannel() == null){
            updateAdvertisingForm.setChannel("1");
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateToStr = dateFormat.format(date);
        updateAdvertisingForm.setUtime(dateToStr);
        return advertisingControlMapper.updateControlList(updateAdvertisingForm);
    }

    @Override
    public Integer deleteControlInfo(String[] id) {
        return advertisingControlMapper.deleteControlInfo(id);
    }

    @Override
    public void changeType(String[] changeId) {
        advertisingControlMapper.changeType(changeId);
    }

    @Override
    public Integer insert(InsertIntoControlForm insert) {
        if (insert.getChannel() == null){
            insert.setChannel("1");
        }
        String[] chaSplit;
            chaSplit = insert.getChannel().split(",");
            HashSet<InsertIntoControlForm> list = new HashSet<>();
            AdvertisingControlQuery advertisingControlQuery = new AdvertisingControlQuery();
            advertisingControlQuery.setMediaName(insert.getMediaName());
            List<AdvertisingControlVo> all = advertisingControlMapper.selectControlList(advertisingControlQuery);
            for (String s1 : chaSplit) {
                InsertIntoControlForm insertChannelForm = new InsertIntoControlForm();
                BeanUtils.copyProperties(insert, insertChannelForm);
                insertChannelForm.setId(UUID.randomUUID().toString().replace("-", "").replace(" ", ""));
                insertChannelForm.setChannel(s1);
                //??????????????????
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                String dateToStr = dateFormat.format(date);
                insertChannelForm.setCtime(dateToStr);
                insertChannelForm.setUtime(dateToStr);
                if (insertChannelForm.getState() == null) {
                    insertChannelForm.setState(0);
                }
                if (insertChannelForm.getConfirm() == null) {
                    insertChannelForm.setConfirm(0);
                }
                //???????????????
                if (all.isEmpty()) {
                    list.add(insertChannelForm);
                } else {
                    for (AdvertisingControlVo advertisingControlVo : all) {
                        if (advertisingControlVo.getChannel().contains(insertChannelForm.getChannel()) && advertisingControlVo.getPck().equals(insertChannelForm.getPck())) {
                            //????????????
                            list.remove(insertChannelForm);
                            throw new ServiceException("??????????????????????????????????????????");
                        } else {
                            list.add(insertChannelForm);
                        }
                    }
                }
            }
            if (list.size() > 0) {
                List<InsertIntoControlForm> list1 = new ArrayList<>(list);
                return advertisingControlMapper.insert(list1);
            }
            return 0;
    }

    @Override
    public Integer insertChannel(InsertChannelForm insertChannelForm) {
        return advertisingControlMapper.insertChannel(insertChannelForm);
    }

    @Override
    public void secondConfirm(String[] changeSecondConfirm) {
        advertisingControlMapper.changeSecondConfirm(changeSecondConfirm);
    }

    @Override
    public List<AdvertisingControlVo> selectDownLoadList(AdvertisingControlQuery query) {
        return advertisingControlMapper.selectControlList(query);
    }

    @Override
    public List<MediaByUserVo> selectMediaByUser(String userName) {
        return advertisingControlMapper.selectMediaByUser(userName);
    }

    @Override
    public List<ChannelVo> selectChannel() {
        return advertisingControlMapper.selectChannel();
    }
}
