package com.qdsg.ylt.base.returnEntity;

/**
 * @ Author     ：yangrb
 * @ Date       ：Created in 16:54 2018/6/14
 * @ Description： 返回给前台的成功提示
 * @ Modified By：
 * @Version: $
 */
public class SuccessEntity extends ReturnEntiry {
    protected Object object;

    public SuccessEntity(){
        super.code = 200;
        super.message = "操作成功";
    }
    public SuccessEntity(Object o){
        super.code = 200;
        super.message = "操作成功";
        super.data = o;
    }
}
