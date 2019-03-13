package com.qdsg.ylt.ylt_user.common.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 接口表
 * </p>
 *
 * @author stylefeng123
 * @since 2018-06-21
 */
@TableName("ylt_interface")
public class YltInterface extends Model<YltInterface> {

    private static final long serialVersionUID = 1L;

    /**
     * 对接编号
     */
    private Long id;
    /**
     * 医院编号
     */
    @TableField("hospital_id")
    private Long hospitalId;
    /**
     * 院方APPID
     */
    @TableField("hos_app_id")
    private String hosAppId;
    /**
     * 院方APPSEC
     */
    @TableField("hos_app_sec")
    private String hosAppSec;
    /**
     * 分配给医院的APPID
     */
    @TableField("app_id")
    private String appId;
    /**
     * 分配给医院的APPSEC
     */
    @TableField("app_sec")
    private String appSec;
    /**
     * 医院获取TOKEN地址
     */
    @TableField("get_token_url")
    private String getTokenUrl;
    /**
     * 医院获取服务地址
     */
    @TableField("get_token_service")
    private String getTokenService;
    /**
     * 操作人编号
     */
    @TableField("op_user_id")
    private Long opUserId;
    /**
     * 状态标志
     */
    private Integer status;
    /**
     * 创建时间
     */
    @TableField("create_date")
    private String createDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHosAppId() {
        return hosAppId;
    }

    public void setHosAppId(String hosAppId) {
        this.hosAppId = hosAppId;
    }

    public String getHosAppSec() {
        return hosAppSec;
    }

    public void setHosAppSec(String hosAppSec) {
        this.hosAppSec = hosAppSec;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSec() {
        return appSec;
    }

    public void setAppSec(String appSec) {
        this.appSec = appSec;
    }

    public String getGetTokenUrl() {
        return getTokenUrl;
    }

    public void setGetTokenUrl(String getTokenUrl) {
        this.getTokenUrl = getTokenUrl;
    }

    public String getGetTokenService() {
        return getTokenService;
    }

    public void setGetTokenService(String getTokenService) {
        this.getTokenService = getTokenService;
    }

    public Long getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(Long opUserId) {
        this.opUserId = opUserId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "YltInterface{" +
        "id=" + id +
        ", hospitalId=" + hospitalId +
        ", hosAppId=" + hosAppId +
        ", hosAppSec=" + hosAppSec +
        ", appId=" + appId +
        ", appSec=" + appSec +
        ", getTokenUrl=" + getTokenUrl +
        ", getTokenService=" + getTokenService +
        ", opUserId=" + opUserId +
        ", status=" + status +
        ", createDate=" + createDate +
        "}";
    }
}
