package com.qdsg.ylt.ylt_jwt.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.qdsg.ylt.ylt_jwt.utils.HttpUtils;
import com.qdsg.ylt.ylt_jwt.utils.RenderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import com.qdsg.ylt.ylt_jwt.utils.BaseResult;

/**
 * @ClassName: JwtAuthencationTokenFilter
 * @Description:对请求进行拦截，检查token
 * @author: ZJ
 * @date: 2018年8月7日 下午3:29:44
 * @version 1.0
 * @Copyright: 2018 www.qdsgvision.com Inc. All rights reserved.
 */


public class JwtAuthencationTokenFilter extends org.springframework.web.filter.OncePerRequestFilter {

  @Autowired
  private UserClient userClient;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

    final String token = HttpUtils.getTokenFromRequest(request);
    System.out.println("token filter worked:"+userClient.BuildReqUrl(userClient.validateTokenUrl)+", "+token);
    if (token != null) {
      String res = userClient.validateToken(token);
      System.out.println("token result:"+res);
      BaseResult  jsonObj = null;
      try
      {
          jsonObj=JSONObject.parseObject(res, BaseResult.class);
      }
      catch (Exception ex)
      {
          RenderUtil.renderJson(response,  new BaseResult(1, "700", "用户服务中心返回json解析失败:"+res, false));
          return;
      }
      if(jsonObj == null || jsonObj.getCode() != "200")
      {
          RenderUtil.renderJson(response, jsonObj);
          return;
      }
    }
    else
    {
      RenderUtil.renderJson(response,  new BaseResult(1, "700", "token不能为空", false));
      return;
    }
    chain.doFilter(request, response);
  }


}
