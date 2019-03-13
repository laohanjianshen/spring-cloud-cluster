package com.qdsg.ylt.ylt_user.common.exception;


import com.qdsg.ylt.exception.ServiceExceptionEnum;

/**
 * ylt-门诊 所有业务异常的枚举
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午5:04:51
 * @Modified By：yangrb
 */
public enum BizExceptionEnum implements ServiceExceptionEnum {
    /**
     * token异常
     */
    TOKEN_EXPIRED(700, "token过期"),
    TOKEN_ERROR(700, "token验证失败"),
    TOKEN_SAVE_ERROR(700, "token保存失败"),

    /**
     * 签名异常
     */
    SIGN_ERROR(700, "签名验证失败"),

    /**
     * 其他
     */
    AUTH_REQUEST_ERROR(400, "账号或密码错误"),
    UPDATE_PASSWORD_ERROR(400, "原密码错误"),
    /**
     * 业务异常
     */
    IP_ERROR(501, "ip地址不可识别"),
    DOCINFO_NOTFOUND_ERROR(501, "医生信息未找到"),
    DOCINFO_NOTBIND_ERROR(501, "账号未绑定医生信息"),
    DOCINFO_NOTMATCH_ERROR(501, "账号与医生信息不匹配"),
    HOSPITAL_PARAMETER_ERROR(501,"未查询到账号信息"),
    PARAMETER_ERROR(501,"传入参数有误"),
    USERINFO_NOFOUND_ERROR(501,"没有该用户信息"),
    PATIENTINFO_UPDATE_ERROR(501,"没有患者修改权限"),
    PATIENTINFO_NOFOUND_ERROR(501,"没有患者修改权限"),
    USERINFO_FORM_ERROR(501,"手机号或邮箱输入错误"),
    FILE_UPDATE_ERROR(501,"文件更新失败"),
    UPLOADFILE_ERROR(501,"文件上传失败"),
    PIC_ERROR(501,"文件为空"),
    PIC_DELE_ERROR(501,"文件为空"),
    PIC_FIND_ERROR(501,"文件查询失败"),
    OUTPINFO_UPDATE_ERROR(501,"门诊信息更新失败"),
    OUTPINFO_SAVE_ERROR(501,"门诊信息保存失败"),
    LOGIN_ERROR(501,"注册失败" );
    BizExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
