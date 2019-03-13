package com.qdsg.ylt.base.exception;


import com.qdsg.ylt.base.returnEntity.ErrorEntity;
import com.qdsg.ylt.exception.YltException;
import com.qdsg.ylt.exception.YltExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 * @author yangrb
 * @date 2018年6月14日 下午3:19:56
 */
public class BaseControllerExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截业务异常
     */
    @ExceptionHandler(YltException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorEntity notFount(YltException e) {
        log.error("业务异常:", e);
        return new ErrorEntity(e.getCode(), e.getMessage());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorEntity notFount(RuntimeException e) {
        log.error("运行时异常:", e);
        return new ErrorEntity(YltExceptionEnum.SERVER_ERROR.getCode(), YltExceptionEnum.SERVER_ERROR.getMessage());
    }

}
