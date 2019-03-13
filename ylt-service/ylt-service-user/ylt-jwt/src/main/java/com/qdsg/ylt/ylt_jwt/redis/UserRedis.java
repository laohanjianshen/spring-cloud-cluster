package com.qdsg.ylt.ylt_jwt.redis;

import com.qdsg.ylt.ylt_jwt.model.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/*
前端 app 提供 token,  userId, bussinessId(docId, erpClientId, eduUserId), bussinessType(ylt, erp, edu)
在用户token 检查通过后， 用userId , bussinessId, bussinessType 获取用户业务相关信息
 */
@Component
public class UserRedis<T> {

    @Autowired
    private RedisService redisService;

    public void setUserInfo(long userID, TbUser tbUser)
    {

    }

    /*设置用户业务数据*/
    public TbUser getUserInfo(long userID)
    {
        return null;
    }

    /*设置用户业务数据*/
    public void setBusinessInfo(long userID, String businessType , String businessID ,T t)
    {

    }

    /*获取用户业务数据*/
    public T getBusinessInfo(long userID, String businessType, String businessID)
    {
        return null;
    }
}
