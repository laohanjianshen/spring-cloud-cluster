package com.qdsg.ylt.ylt_user.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qdsg.ylt.exception.YltException;
import com.qdsg.ylt.kit.MD5Util;
import com.qdsg.ylt.ylt_user.common.BaseResult;
import com.qdsg.ylt.ylt_user.common.exception.BizExceptionEnum;
import com.qdsg.ylt.ylt_user.common.model.*;
import com.qdsg.ylt.ylt_user.dao.LoginLogMapper;
import com.qdsg.ylt.ylt_user.config.properties.JwtProperties;
import com.qdsg.ylt.ylt_user.controller.dto.AuthRequest;
import com.qdsg.ylt.ylt_user.dao.TbMobileUserMapper;
import com.qdsg.ylt.ylt_user.dao.UserMapper;
import com.qdsg.ylt.ylt_user.dao.YltInterfaceMapper;
import com.qdsg.ylt.ylt_user.service.AuthService;
import com.qdsg.ylt.ylt_user.service.DoctorService;
import com.qdsg.ylt.ylt_user.service.RedisService;
import com.qdsg.ylt.ylt_user.util.JwtTokenUtil;
import com.qdsg.ylt.ylt_user.util.NetworkUtil;
import com.qdsg.ylt.ylt_user.validator.IReqValidator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Author     ：yangrb
 * @ Date       ：Created in 17:11 2018/6/12
 * @ Description：登录Service 主要生成jwt
 * @ Modified By：
 * @Version: $
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Resource(name = "dbValidator")
    private IReqValidator reqValidator;

    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private DoctorService doctorService;
//    @Autowired
//    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;
    @Resource
    private LoginLogMapper loginLogMapper;
    @Resource
    private YltInterfaceMapper yltInterfaceMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private TbMobileUserMapper tbMobileUserMapper;
    /**
     * <pre>
     *  登录信息验证
     *  result.ret=0:失败   result.ret=1:成功
     * </pre>
     */
    @Override
    public Map login(AuthRequest authRequest, String ip) throws IOException {
        Map result = new HashMap();
        result.put("ret", "0");
        LoginLog loginLog = new LoginLog();
        loginLog.setCreatetime(new Date());
        loginLog.setLogname(authRequest.getCredenceName());

        Map validate = reqValidator.validate(authRequest);
        loginLog.setIp(ip);
        loginLog.setSucceed("1".equals(validate.get("ret")) ? "success" : "fail");
        loginLog.setUserid(validate.get("userId") == null ? 0L : (Long) validate.get("userId"));

        if ("1".equals(validate.get("ret"))) {
            //登录验证成功
            result.put("ret", "1");

            Map claims = new HashMap();
            final String randomKey = jwtTokenUtil.getRandomKey();

            List docInfos = doctorService.selectDocInfoByUserId((Long) validate.get("userId"));
            //查不到医生信息DOCINFOS 说明该账号未绑定医生或者没有审核通过
            if (docInfos.size() < 1) {
                throw new YltException(BizExceptionEnum.DOCINFO_NOTBIND_ERROR);
            }

            claims.put(jwtProperties.getUserId(), validate.get("userId"));
            claims.put(jwtProperties.getDoctor(), docInfos.get(0));
            claims.put("randomKey", randomKey);
            String token = jwtTokenUtil.generateToken(authRequest.getUserName(),claims);
            result.put("hosType", ((Map)docInfos.get(0)).get("hosType"));

            result.put("randomKey", randomKey);
            result.put("token", token);
            loginLog.setSucceed("成功");
            loginLog.setMessage(token);
            loginLogMapper.insert(loginLog);
        } else {
            //登录验证失败
            loginLog.setSucceed("失败");
            loginLogMapper.insert(loginLog);
        }
        return result;
    }

    @Override
    public BaseResult<String> login(AuthRequest authRequest, HttpServletRequest request) {
        Map loginResult;
        try {
            //用户登录
            loginResult = login(authRequest, NetworkUtil.getIpAddress(request));
        } catch (IOException e) {
            return new BaseResult(-1, "501", "账号未绑定医生信息", null);
        }
        if ("1".equals(loginResult.get("ret"))) {
            String token = loginResult.get("token").toString();
            String redisKey = authRequest.getUserName() + "_" + jwtTokenUtil.getIssuedAtDateFromToken(token);
            Boolean set = redisService.set(redisKey, token);
            if (!set) {
                return new BaseResult(1, "700", "token添加redis失败", null);
            }
            return new BaseResult<String>(1, "200", "登陆成功", loginResult.get("token").toString());
        } else {
            return new BaseResult(1, "400", "账号或密码错误", null);
        }
    }

    @Override
    public BaseResult<Boolean> mobileToken(String token) {
        if (token != null) {
            try {
                Claims claims = jwtTokenUtil.getClaimFromToken(token);
                //验证token是否过期,包含了验证jwt是否正确
                //验证token是否过期
                boolean flag = jwtTokenUtil.isTokenExpired(token);
                String pcRedisKey = jwtTokenUtil.getUsernameFromToken(token) + "_" + jwtTokenUtil.getIssuedAtDateFromToken(token);
                String mobileRedisKey = jwtTokenUtil.getUsernameFromToken(token) + "_mobile";
                Boolean redisFlag= (null == redisService.get(pcRedisKey) || !token.equals(redisService.get(pcRedisKey).toString())) && (null == redisService.get(mobileRedisKey) || !token.equals(redisService.get(mobileRedisKey).toString()));
                if (flag || redisFlag) {
                    return new BaseResult(1, "700", "token过期", false);
                }
            } catch (JwtException e) {
                //有异常就是token解析失败
                return new BaseResult(1, "700", "token解析失败", false);
            }
        } else {
            //header没有带Authorization字段
            return new BaseResult(1, "700", "未识别到token", false);
        }
        return new BaseResult(1, "200", "成功", true);
    }

    @Override
    public BaseResult<Boolean> token(String token) {
        if (token != null) {
            try {
                Claims claims = jwtTokenUtil.getClaimFromToken(token);
                //验证token是否过期,包含了验证jwt是否正确
                //验证token是否过期
                boolean flag = jwtTokenUtil.isTokenExpired(token);
                String redisKey = jwtTokenUtil.getUsernameFromToken(token) + "_" + jwtTokenUtil.getIssuedAtDateFromToken(token);
                if (flag || null == redisService.get(redisKey) || !token.equals(redisService.get(redisKey).toString())) {
                    return new BaseResult(1, "700", "token过期", false);
                }
            } catch (JwtException e) {
                //有异常就是token解析失败
                return new BaseResult(1, "700", "token解析失败", false);
            }
//                JwtUserDetail userFromToken = jwtTokenUtil.getUserFromToken(token);
//                String redisKey = userFromToken.getAccount() + "_" + jwtTokenUtil.getIat(token);
//                boolean flag = jwtTokenUtil.isTokenExpired(token);
//                if (flag || null == redisService.get(redisKey) || !token.equals(redisService.get(redisKey).toString())) {
//                    return new BaseResult(1, "700", "token过期", false);
//                } else {
//                    //验证token是否正确
//                    if (!jwtTokenUtil.validateToken(token, userFromToken)) {
//                        return new BaseResult(1, "700", "token验证失败", false);
//                    }
//                }
//            } catch (JwtException e) {
//                return new BaseResult(-1, "700", "token验证失败", false);
//            }
        } else {
            //header没有带Authorization字段
            return new BaseResult(1, "700", "未识别到token", false);
        }
        return new BaseResult(1, "200", "成功", true);
    }

    @Override
    public String docLogin(Long hospitalId, String appId, String requestTime, String sign, String hosDocCode, HttpServletRequest request){
        List<YltInterface> yltInterfaceList = yltInterfaceMapper.selectList(new EntityWrapper<YltInterface>()
                .eq("hospital_id", hospitalId)
                .and()
                .eq("hos_app_id", appId)
        );
        String s = null;
        String ipAddress;
        try {
            ipAddress =NetworkUtil.getIpAddress(request);
        } catch (IOException e) {
            e.printStackTrace();
            throw new YltException(BizExceptionEnum.IP_ERROR);
        }
        if(yltInterfaceList.size()==1){
            String hosAppSec = yltInterfaceList.get(0).getHosAppSec();
            String message = hospitalId+appId+hosAppSec;
            try {
                s = MD5Util.signBuilder(message, requestTime);
            } catch (Exception e) {
                e.printStackTrace();
                throw new YltException(BizExceptionEnum.PARAMETER_ERROR);
            }
        }else {
            throw new YltException(BizExceptionEnum.HOSPITAL_PARAMETER_ERROR);
        }
        if(sign.equals(s)){
            Map claims = new HashMap();
            final String randomKey = jwtTokenUtil.getRandomKey();
            Map map = doctorService.selectDoctorInfoByHosId(hospitalId, hosDocCode);
            //查不到医生信息DOCINFOS 说明该账号未绑定医生或者没有审核通过
            if (null == map || map.isEmpty()){
                throw new YltException(BizExceptionEnum.DOCINFO_NOTBIND_ERROR);
            }
            Long userId = Long.valueOf(map.get("userId").toString());
            claims.put(jwtProperties.getUserId(), userId);
            claims.put(jwtProperties.getDoctor(), map);
            claims.put("randomKey", randomKey);
            User user = userMapper.selectById(userId);
            String token = jwtTokenUtil.generateToken(user.getAccount(),claims);

            LoginLog loginLog = new LoginLog();
            loginLog.setCreatetime(new Date());
            loginLog.setLogname(user.getAccount());
            loginLog.setIp(ipAddress);
            loginLog.setUserid(userId);
            loginLog.setSucceed("成功");
            loginLog.setMessage(token);
            loginLogMapper.insert(loginLog);
            String redisKey = user.getAccount() + "_" + jwtTokenUtil.getIssuedAtDateFromToken(token);
            Boolean set = redisService.set(redisKey, token);
            if (!set) {
                throw new YltException(BizExceptionEnum.TOKEN_SAVE_ERROR);
            }
            return token;
        }else {
            throw new YltException(BizExceptionEnum.SIGN_ERROR);
        }
    }

    @Override
    public Map setPasswd(String token,String password, String newPassword) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Long userId = jwtTokenUtil.getUserId(token);
        User user = userMapper.selectById(userId);
        String userName = jwtTokenUtil.getUsernameFromToken(token);
        Map result = new HashMap();
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUserName(userName);
        authRequest.setPassword(password);
        Map validate = reqValidator.validate(authRequest);
        if ("1".equals(validate.get("ret"))) {
            //登录验证成功
            String[] newMd5 = MD5Util.passwordBuilder(newPassword).split(":");
            user.setPassword(newMd5[0]);
            user.setSalt(newMd5[1]);
            userMapper.updateById(user);
            result.put("ret","1");
        } else {
            //登录验证失败
            throw new YltException(BizExceptionEnum.UPDATE_PASSWORD_ERROR);
        }
        return result;
    }

    @Override
    public Map videoAuth(AuthRequest authRequest, HttpServletRequest request) {
        Map loginResult;
        Map mobile = new HashMap<>(2);
        Map loginMessage = new HashMap(5);
        try {
            //用户登录
            loginResult = login(authRequest, NetworkUtil.getIpAddress(request));
        } catch (IOException e) {
            throw new YltException(BizExceptionEnum.DOCINFO_NOTBIND_ERROR);
        }
        if ("1".equals(loginResult.get("ret"))) {
            Long userId = Long.valueOf(reqValidator.validate(authRequest).get("userId").toString());
            List<TbMobileUser> videoList = tbMobileUserMapper.selectList(new EntityWrapper<TbMobileUser>().eq("ylt_user_id", userId));
            String mobilePhone;
            String passwd;
            if(videoList.size()< 1){
                throw new YltException(BizExceptionEnum.LOGIN_ERROR);
            }else {
                mobile.put("mobilePhone",videoList.get(0).getMobilePhone());
                mobile.put("mobilePassword",videoList.get(0).getPasswd());
            }

            String token = loginResult.get("token").toString();
            String redisKey = authRequest.getUserName() + "_" + jwtTokenUtil.getIssuedAtDateFromToken(token);
            Boolean set = redisService.set(redisKey, token);
            if (!set) {
                throw new YltException(BizExceptionEnum.TOKEN_SAVE_ERROR);
            }
            loginMessage.put("token",token);
            loginMessage.put("randomKey",loginResult.get("randomKey"));
            loginMessage.put("hosType",loginResult.get("hosType"));
            loginMessage.put("mobile",mobile);
            return loginMessage;
        } else {
            throw new YltException(BizExceptionEnum.AUTH_REQUEST_ERROR);
        }
    }
}
