package com.qdsg.ylt.base.returnEntity;

/**
 * @ Author     ：yangrb
 * @ Date       ：Created in 16:55 2018/6/14
 * @ Description： 返回给前台的失败提示
 * @ Modified By：
 * @Version: $
 */
public class ErrorEntity extends ReturnEntiry {
    public ErrorEntity(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
}
