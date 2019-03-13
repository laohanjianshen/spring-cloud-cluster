package com.qdsg.ylt.ylt_user.service;

import com.baomidou.mybatisplus.service.IService;
import com.qdsg.ylt.ylt_user.common.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @ Author     ：yangrb
 * @ Date       ：Created in 17:10 2018/6/14
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface UserService extends IService<User> {

    @Transactional
    public Boolean addUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
