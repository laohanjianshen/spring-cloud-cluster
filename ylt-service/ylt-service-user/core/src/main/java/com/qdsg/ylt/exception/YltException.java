package com.qdsg.ylt.exception;

/**
 * @ Author     ：yangrb
 * @ Date       ：Created in 17:00 2018/6/14
 * @ Description：封装医联体异常
 * @ Modified By：
 * @Version: $
 */
public class YltException extends RuntimeException {

    private Integer code;

    private String message;

    public YltException(ServiceExceptionEnum serviceExceptionEnum) {
        this.code = serviceExceptionEnum.getCode();
        this.message = serviceExceptionEnum.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}