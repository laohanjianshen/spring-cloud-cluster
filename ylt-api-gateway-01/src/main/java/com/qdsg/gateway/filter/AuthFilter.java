package com.qdsg.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.qdsg.gateway.config.JwtProperties;
import com.qdsg.gateway.dto.BaseResult;
import com.qdsg.gateway.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Component
@Slf4j
public class AuthFilter extends ZuulFilter {
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        String uri = requestContext.getRequest().getRequestURI();
        System.out.println("请求地址:" + uri);
        //登录放行,其它接口拦截
        for (String whiteUri:jwtProperties.getWhiteUriList()) {
            System.out.println("放行的URI:"+whiteUri);
            if (uri != null && uri.equals(whiteUri)) {
                log.info("放行");
                return false;
            }
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getHeader("Authorization");
        BaseResult baseResult = tokenUtil.isLegitimate(token);
        log.info("鉴权结果:"+baseResult);
        if (token == null || !(baseResult.getCode().equals("200"))) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
}
