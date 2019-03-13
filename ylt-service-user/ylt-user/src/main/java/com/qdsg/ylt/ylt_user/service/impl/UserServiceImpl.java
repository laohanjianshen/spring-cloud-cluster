package com.qdsg.ylt.ylt_user.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qdsg.ylt.kit.MD5Util;
import com.qdsg.ylt.ylt_user.common.model.User;
import com.qdsg.ylt.ylt_user.dao.UserMapper;
import com.qdsg.ylt.ylt_user.service.UserService;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

/**
 * @ Author     ：yangrb
 * @ Date       ：Created in 17:10 2018/6/14
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public Boolean addUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        List<User> users= selectList(new EntityWrapper<User>().eq("account", user.getAccount()).and().eq("flag",1));
        if(users.size()<1){
            String s = MD5Util.passwordBuilder(user.getPassword());
            String[] split = s.split(":");
            user.setPassword(split[0]);
            user.setSalt(split[1]);
            user.setCreateDate(new Date());
            user.setFlag(1);
            insert(user);
            return true;
        }
        return false;
    }
}
