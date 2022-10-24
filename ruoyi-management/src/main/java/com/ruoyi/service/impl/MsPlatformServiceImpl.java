package com.ruoyi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.dao.PlatformMapper;
import com.ruoyi.form.UpdatePlatformForm;
import com.ruoyi.query.PlatformQuery;
import com.ruoyi.service.MsPlatformService;
import com.ruoyi.vo.PlatformVo;
import com.ruoyi.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

@Service
public class MsPlatformServiceImpl implements MsPlatformService {
    @Autowired
    private PlatformMapper platformMapper;
    @Override
    public List<String> selectPlatformName() {
        return platformMapper.selectPlatformName();
    }

    @Override
    public PageInfo<PlatformVo> selectPlatformList(PlatformQuery platformQuery) {
        PageHelper.startPage(platformQuery.getPageNum(), platformQuery.getPageSize());
        List<PlatformVo> list = platformMapper.selectPlatformList(platformQuery);
        return  new PageInfo<>(list);
    }

    @Override
    public ResultVo deleteMany(Long[] deleteId) {
        int affectedRows = platformMapper.deleteMany(deleteId);
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(-1);
        resultVo.setMsg("删除失败");
        if (affectedRows>0){
            resultVo.setCode(0);
            resultVo.setMsg("删除成功");
        }
        return resultVo;
    }

    @Override
    public ResultVo updateInfo(UpdatePlatformForm updatePlatformForm) {
//        updatePlatformForm.setSecret(SecurityUtils.encryptPassword(updatePlatformForm.getSecret()));
        try {
            String a = Base64.getEncoder().encodeToString(updatePlatformForm.getSecret().getBytes("utf-8"));
            updatePlatformForm.setSecret(Base64.getEncoder().encodeToString(updatePlatformForm.getSecret().getBytes("utf-8")));
//            System.out.println("解密"+new String(Base64.getDecoder().decode(a)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int affectedRows = platformMapper.updateInfo(updatePlatformForm);
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(-1);
        resultVo.setMsg("修改失败");
        if (affectedRows>0){
            resultVo.setCode(0);
            resultVo.setMsg("修改成功");
        }
        return resultVo;
    }

    @Override
    public ResultVo insertInfo(UpdatePlatformForm updatePlatformForm) {
        updatePlatformForm.setSecret(SecurityUtils.encryptPassword(updatePlatformForm.getSecret()));
        int affectedRows = platformMapper.insertInfo(updatePlatformForm);
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(-1);
        resultVo.setMsg("添加失败");
        if (affectedRows>0){
            resultVo.setCode(0);
            resultVo.setMsg("添加成功");
        }
        return resultVo;
    }
}
