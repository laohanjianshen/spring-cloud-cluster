package com.qdsg.ylt.ylt_user.service;

import com.qdsg.ylt.ylt_user.common.BaseResult;
import com.qdsg.ylt.ylt_user.controller.dto.AuthRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @ Author     ：yangrb
 * @ Date       ：Created in 17:11 2018/6/12
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public interface AuthService {

    Map login(AuthRequest authRequest, String ip) throws IOException;

    BaseResult login(AuthRequest authRequest, HttpServletRequest request);

    BaseResult<Boolean> token(String token);

    String docLogin(Long hospitalId, String appId, String requestTime, String sign, String hos_doc_code, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    Map setPasswd(String s, String password, String newPassword) throws NoSuchAlgorithmException, UnsupportedEncodingException;

    Map videoAuth(AuthRequest authRequest, HttpServletRequest request);

    BaseResult<Boolean> mobileToken(String token);
}
