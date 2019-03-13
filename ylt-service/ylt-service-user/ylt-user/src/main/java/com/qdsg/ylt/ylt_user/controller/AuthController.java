package com.qdsg.ylt.ylt_user.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qdsg.ylt.base.controller.BaseController;
import com.qdsg.ylt.base.returnEntity.SuccessEntity;
import com.qdsg.ylt.exception.YltException;
import com.qdsg.ylt.ylt_user.common.BaseResult;
import com.qdsg.ylt.ylt_user.common.exception.BizExceptionEnum;
import com.qdsg.ylt.ylt_user.common.model.JwtUserDetail;
import com.qdsg.ylt.ylt_user.common.model.TbDoctor;
import com.qdsg.ylt.ylt_user.common.model.User;
import com.qdsg.ylt.ylt_user.common.model.YltInterface;
import com.qdsg.ylt.ylt_user.config.properties.JwtProperties;
import com.qdsg.ylt.ylt_user.controller.dto.AuthResponse;
import com.qdsg.ylt.ylt_user.service.RedisService;
import com.qdsg.ylt.ylt_user.service.UserService;
import com.qdsg.ylt.ylt_user.util.CryptoUtil;
import com.qdsg.ylt.ylt_user.util.JwtTokenUtil;
import com.qdsg.ylt.ylt_user.util.NetworkUtil;
import com.qdsg.ylt.ylt_user.service.AuthService;
import com.qdsg.ylt.ylt_user.controller.dto.AuthRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("/")
public class AuthController extends BaseController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisService redisService;


    @RequestMapping(value = "${jwt.auth-path}")//,method = RequestMethod.POST
    public BaseResult<String> createAuthenticationToken(AuthRequest authRequest, HttpServletRequest request) {
        return authService.login(authRequest, request);
    }

    @RequestMapping("videoAuth")
    public BaseResult creteVideoAuthenticationToken(AuthRequest authRequest,HttpServletRequest request){
        BaseResult login = authService.login(authRequest, request);
        Map video;
        try{
            video =  authService.videoAuth(authRequest,request);
        }catch (YltException e){
            e.printStackTrace();
            return new BaseResult(1,e.getCode().toString(),e.getMessage(),null);
        }

        return new BaseResult(1,"200","登陆成功",video);
    }
//todo
   /* *//**
     * 医院医生登陆
     *//*
    @RequestMapping(value = "docAuth")
    public BaseResult<String> createDocAuthenticationToken(Long hospitalId, String appId, String requestTime, String sign, String hos_doc_code,HttpServletRequest request, HttpServletResponse response){
        Date date = new Date(Long.valueOf(requestTime));
        long time = date.getTime();
        Date now = new Date();
        long nowTime = now.getTime();
        //判断时间是否超过30分钟
        boolean timeOut = ((nowTime - time) / 60000) > 30;
        String s = null;
        if (!timeOut) {
            if (null != sign && !"".equals(sign)) {
                try {
                    s = authService.docLogin(hospitalId, appId, requestTime, sign, hos_doc_code, request);
                } catch (Exception e) {

                }
            } else {
                //sign为空
            }
        }else {
            //超时
        }
        if(s == null){
            return new BaseResult<>(1,"700","失败","");
        }else {
            return new BaseResult<String>(1,"200", "成功", s);
        }
    }*/

    //登出系统
    @RequestMapping(value = "logOut")
    public BaseResult<Boolean> logOut(HttpServletRequest request, HttpServletResponse response) {
        final String token = request.getParameter("token");
        String redisKey = jwtTokenUtil.getUsernameFromToken(token) + "_" + jwtTokenUtil.getIssuedAtDateFromToken(token);
        if (null == redisService.get(redisKey)) {
            return new BaseResult(1, "700", "查无此token", false);
        }
        redisService.delCacheByKey(redisKey);
        return new BaseResult(1, "200", "成功", true);
    }

    //校验token是否过期，与是否正确。
    @RequestMapping(value = "token")//,method = RequestMethod.POST
    public BaseResult<Boolean> validateToken(HttpServletRequest request, HttpServletResponse response) {
        String header = request.getHeader("Authorization");
        String parameter = request.getParameter("token");
        final String token = header == null ? parameter : header;
        return authService.mobileToken(token);
//            return authService.token(token);
    }

    @RequestMapping(value = "getUser")//,method = RequestMethod.Get
    public BaseResult<JwtUserDetail> getUserInfo(Long userId) {
        BaseResult baseResult;
        if (null != userId && !"".equals(userId)) {
            List<User> users = userService.selectList(new EntityWrapper<User>().eq("id", userId));
            if (users.size() < 1) {
                return new BaseResult(1, "702", "您传入的Id有误", null);
            } else {
                User user = users.get(0);
                JwtUserDetail jwtUserDetail = jwtTokenUtil.setUser(user);
                jwtUserDetail.setIsCredentialsNonExpired(true);
                jwtUserDetail.setIsEnabled(true);
                return new BaseResult(1, "200", "获取用户成功", jwtUserDetail);
            }
        }
        return new BaseResult(1, "702", "您必须传入一个userId", null);
    }


    @RequestMapping(value = "/passwd")
    @ApiOperation(value="修改密码",notes = "修改密码 更新数据库中的MD5密码和salt")
    public BaseResult<Boolean> setPasswd(HttpServletRequest request, String password, String newPassword){
        Map updateResult = null;
        try {
            updateResult = authService.setPasswd(request.getHeader(jwtProperties.getHeader()).toString(),password,newPassword);
        } catch (YltException e) {
            return new BaseResult<Boolean>(1,e.getCode().toString(), e.getMessage(),false);
        }catch (Exception e){
            e.printStackTrace();
            return new BaseResult<Boolean>(1,"500","服务器异常",false);
        }
        if ("1".equals(updateResult.get("ret"))){
            return new BaseResult<Boolean>(1,"200", "修改成功",true);
        }else {
            return new BaseResult<Boolean>(1,"400", "原密码错误",false);
        }
    }
}
