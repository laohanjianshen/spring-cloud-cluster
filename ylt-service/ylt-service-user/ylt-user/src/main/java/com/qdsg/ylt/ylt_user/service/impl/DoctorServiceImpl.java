package com.qdsg.ylt.ylt_user.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qdsg.ylt.ylt_user.common.exception.BizExceptionEnum;
import com.qdsg.ylt.ylt_user.common.model.TbDoctor;
import com.qdsg.ylt.ylt_user.dao.TbDoctorMapper;
import com.qdsg.ylt.ylt_user.config.properties.JwtProperties;
import com.qdsg.ylt.exception.YltException;
import com.qdsg.ylt.ylt_user.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ Author     ：yangrb
 * @ Date       ：Created in 17:22 2018/6/12
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@Service
public class DoctorServiceImpl extends ServiceImpl<TbDoctorMapper,TbDoctor> implements DoctorService {
    @Resource
    private TbDoctorMapper doctorMapper;
    @Autowired
    private JwtProperties jwtProperties;
    @Override
    public List selectDocInfoByUserId(Long userId) {
        return doctorMapper.selectDoctorInfoByUserId(userId);
    }

    @Override
    public Map selectDocInfoByDocId(Long docId,Long userId) {
        Map<String ,Object> docInfo =  doctorMapper.selectDoctorInfoByDocId(docId);
        if (docInfo == null||docInfo.size()==0){
            throw new YltException(BizExceptionEnum.DOCINFO_NOTFOUND_ERROR);
        }else if (Long.parseLong(docInfo.get(jwtProperties.getUserId()).toString())!=userId){
            throw new YltException(BizExceptionEnum.DOCINFO_NOTMATCH_ERROR);
        }
        return docInfo;
    }

    @Override
    public Map selectDoctorInfoByHosId(Long hospitalId, String hosDocCode) {
        return doctorMapper.selectDoctorInfoByHosId(hospitalId,hosDocCode);
    }
}
