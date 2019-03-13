package com.qdsg.ylt.ylt_user.service;

import com.baomidou.mybatisplus.service.IService;
import com.qdsg.ylt.ylt_user.common.model.TbDoctor;

import java.util.List;
import java.util.Map;

/**
 * @ Author     ：yangrb
 * @ Date       ：Created in 17:21 2018/6/12
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface DoctorService extends IService<TbDoctor> {
    List selectDocInfoByUserId(Long userId);
    Map selectDocInfoByDocId(Long docId, Long userId);

    Map selectDoctorInfoByHosId(Long hospitalId, String hosDocCode);
}
