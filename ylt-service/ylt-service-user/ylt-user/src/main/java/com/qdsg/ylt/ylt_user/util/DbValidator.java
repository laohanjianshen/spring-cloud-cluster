package com.qdsg.ylt.ylt_user.util;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qdsg.ylt.ylt_user.common.model.User;
import com.qdsg.ylt.kit.MD5Util;
import com.qdsg.ylt.ylt_user.dao.UserMapper;
import com.qdsg.ylt.ylt_user.validator.IReqValidator;
import com.qdsg.ylt.ylt_user.validator.dto.Credence;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 账号密码验证工具类
 *
 * @author fengshuonan
 * @date 2017-08-23 12:34
 */
@Service
public class DbValidator implements IReqValidator {

    @Resource
    UserMapper userMapper;

    @Override
    public Map validate(Credence credence) {
        String account = credence.getCredenceName();
        String password = credence.getCredenceCode();

        //结果集封装
        Map result = new HashMap();
        result.put("ret","0");

        List<User> users = userMapper.selectList(new EntityWrapper<User>().eq("account",account).and().eq("flag",1));
        if (users != null && users.size() > 0) {
            //根据用户account查询到信息的状况下
            User user = users.get(0);
            //status不为1 说明账号目前不可用
            if (user.getFlag()!=1){
                return result;
            }
            try {
                //用MD5工具去验证用户输入密码和数据库中的密码是否一致
                boolean flag = MD5Util.validPasswd(password,user.getPassword(),user.getSalt());
                if (flag){
                    //密码匹配的情况下
                    result.put("userId",user.getId());
                    result.put("user",user);
                    result.put("ret","1");
                    return result;
                }else {
                    return result;
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return result;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return result;
            }
        } else {
            //根据用户account查询不到信息的状况下
            return result;
        }
    }
}
