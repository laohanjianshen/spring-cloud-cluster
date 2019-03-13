package com.qdsg.ylt.ylt_user.dao;

import com.qdsg.ylt.ylt_user.common.model.TbDoctor;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stylefeng123
 * @since 2018-06-21
 */
public interface TbDoctorMapper extends BaseMapper<TbDoctor> {
    List<Map> selectDoctorInfoByUserId(Long userId);
    Map selectDoctorInfoByDocId(Long DocId);
    Map selectHosTypeByDocId(Long docId);
    List<Map> selectPerInfoByDocId(Long docId);
    List<Map> selectHosInfoByUserId(Long userId);
    List<Map> selectDocNameByDeptId(@Param("deptId") Long deptId, @Param("hosId") Long hosId);
    Map selectDoctorInfoByHosId(@Param("hosId") Long deptId, @Param("hosDocCode") String hosId);
}
