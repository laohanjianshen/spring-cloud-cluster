package com.qdsg.ylt.ylt_jwt.security;

import com.qdsg.ylt.ylt_jwt.config.JwtClientProperties;
import com.qdsg.ylt.ylt_jwt.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserClient {

    public final String authUrl = "/auth";

    public final String validateTokenUrl = "/token";

    public final String logOutUrl = "/logout";

    public final  String getUserURL = "/getUser";

    public  String BuildReqUrl(String tails)
    {
        return jwtClientProperties.getUserServer() + tails;
    }

    @Autowired
    private JwtClientProperties jwtClientProperties;

    public String validateToken(String token)
    {
        Map<String, String> params = new HashMap<>();
        params.put("token",token);
        String response = HttpUtils.httpPostRequest(BuildReqUrl(validateTokenUrl), params);
        return response;
    }

    public String logOut(String token)
    {
        Map<String, String> params = new HashMap<>();
        params.put("token",token);
        String response = HttpUtils.httpPostRequest(BuildReqUrl(logOutUrl), params);
        return response;
    }

}
