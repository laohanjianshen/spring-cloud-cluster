package com.qdsg.gateway.util;


import com.qdsg.gateway.dto.BaseResult;
import com.qdsg.gateway.redis.RedisService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 验证token是否合法
 */
@Component
public class TokenUtil {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private RedisService redisService;

    public BaseResult isLegitimate(String token) {
        if (token != null) {
            try {
                Claims claims = jwtTokenUtil.getClaimFromToken(token);
                //验证token是否过期,包含了验证jwt是否正确
                //验证token是否过期
                boolean flag = jwtTokenUtil.isTokenExpired(token);
                String pcRedisKey = jwtTokenUtil.getUsernameFromToken(token) + "_" + jwtTokenUtil.getIssuedAtDateFromToken(token);
                String mobileRedisKey = jwtTokenUtil.getUsernameFromToken(token) + "_mobile";
                Boolean redisFlag = (null == redisService.get(pcRedisKey) || !token.equals(redisService.get(pcRedisKey).toString())) && (null == redisService.get(mobileRedisKey) || !token.equals(redisService.get(mobileRedisKey).toString()));
                if (flag || redisFlag) {
                    return new BaseResult(1, "700", "token过期", false);
                }
            } catch (JwtException e) {
                //有异常就是token解析失败
                return new BaseResult(1, "700", "token解析失败", false);
            }
        } else {
            return new BaseResult(1, "700", "未识别到token", false);
        }
        return new BaseResult(1, "200", "成功", true);
    }
}