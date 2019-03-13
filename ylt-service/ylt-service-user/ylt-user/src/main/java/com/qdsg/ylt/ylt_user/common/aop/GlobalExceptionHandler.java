package com.qdsg.ylt.ylt_user.common.aop;

import com.qdsg.ylt.base.exception.BaseControllerExceptionHandler;
import com.qdsg.ylt.base.returnEntity.ErrorEntity;
import com.qdsg.ylt.ylt_user.common.exception.BizExceptionEnum;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
*
* @author fengshuonan
* @date 2016年11月12日 下午3:19:56
* @Modified By：yangrb
*/
@ControllerAdvice
public class GlobalExceptionHandler extends BaseControllerExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截jwt相关异常
     */
    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorEntity jwtException(JwtException e) {
        log.error(e.getMessage());
        return new ErrorEntity(BizExceptionEnum.TOKEN_ERROR.getCode(), BizExceptionEnum.TOKEN_ERROR.getMessage());
    }

}
