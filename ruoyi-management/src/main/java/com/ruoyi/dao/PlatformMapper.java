package com.ruoyi.dao;

import com.ruoyi.form.UpdatePlatformForm;
import com.ruoyi.query.PlatformQuery;
import com.ruoyi.vo.PlatformVo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PlatformMapper {
    List<String> selectPlatformName();

    List<PlatformVo> selectPlatformList(@Param("platformQuery") PlatformQuery platformQuery);

    int deleteMany(Long[] deleteId);

    int updateInfo(@Param("updatePlatformForm") UpdatePlatformForm updatePlatformForm);

    int insertInfo(@Param("updatePlatformForm") UpdatePlatformForm updatePlatformForm);
    //
    List<PlatformVo> selectPId(@Param("type") Integer type);
}
