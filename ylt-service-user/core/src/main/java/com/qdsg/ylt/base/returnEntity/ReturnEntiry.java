package com.qdsg.ylt.base.returnEntity;

/**
 * @ Author     ：yangrb
 * @ Date       ：Created in 16:53 2018/6/14
 * @ Description：接口返回类
 * @ Modified By：
 * @Version: $
 */
public abstract class ReturnEntiry {
    protected Integer code;
    protected String message;
    protected Object data;
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
}
