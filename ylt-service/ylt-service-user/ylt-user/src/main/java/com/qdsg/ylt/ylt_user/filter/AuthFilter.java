package com.qdsg.ylt.ylt_user.filter;

import com.qdsg.ylt.base.returnEntity.ErrorEntity;
import com.qdsg.ylt.kit.RenderUtil;
import com.qdsg.ylt.ylt_user.common.BaseResult;
import com.qdsg.ylt.ylt_user.common.exception.BizExceptionEnum;
import com.qdsg.ylt.ylt_user.config.properties.JwtProperties;
import com.qdsg.ylt.ylt_user.service.AuthService;
import com.qdsg.ylt.ylt_user.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 对客户端请求的jwt token验证过滤器
 *
 * @author fengshuonan
 * @Date 2017/8/24 14:04
 */

@Component
@WebFilter(urlPatterns = "/*", filterName = "authFilter")
public class AuthFilter extends OncePerRequestFilter {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String servletPath = request.getServletPath();
        System.out.println(servletPath
        );
        if (request.getServletPath().equals("/" + jwtProperties.getAuthPath())||
            request.getServletPath().equals("/token")||
            request.getServletPath().equals("/videoAuth")||
            request.getServletPath().equals("/user/add")) {
            chain.doFilter(request, response);
            return;
        }
        String header = request.getHeader("Authorization");
        String parameter = request.getParameter("token");

        final String token = header ==null ?parameter:header;
        if (token != null) {
            BaseResult<Boolean> result = authService.token(token);
            if(!result.getData()){
                RenderUtil.renderJson(response,new ErrorEntity(Integer.valueOf(result.getCode()),result.getMessage()));
                return;
            }
//            Claims claims = jwtTokenUtil.getClaimFromToken(token);
//            //验证token是否过期,包含了验证jwt是否正确
//            try {
//                boolean flag = jwtTokenUtil.isTokenExpired(token);
//                if (flag) {
//                    RenderUtil.renderJson(response, new ErrorEntity(BizExceptionEnum.TOKEN_EXPIRED.getCode(), BizExceptionEnum.TOKEN_EXPIRED.getMessage()));
//                    return;
//                }
//            } catch (JwtException e) {
//                //有异常就是token解析失败
//                RenderUtil.renderJson(response, new ErrorEntity(BizExceptionEnum.TOKEN_ERROR.getCode(), BizExceptionEnum.TOKEN_ERROR.getMessage()));
//                return;
//            }
        } else {
            //header没有带Bearer字段
//            chain.doFilter(request, response);
            RenderUtil.renderJson(response, new ErrorEntity(BizExceptionEnum.TOKEN_ERROR.getCode(), BizExceptionEnum.TOKEN_ERROR.getMessage()));
            return;
        }
        chain.doFilter(request, response);
    }
}