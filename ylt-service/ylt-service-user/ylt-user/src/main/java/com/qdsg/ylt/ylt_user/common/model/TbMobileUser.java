package com.qdsg.ylt.ylt_user.common.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng123
 * @since 2018-06-21
 */
@TableName("tb_mobile_user")
public class TbMobileUser extends Model<TbMobileUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 移动用户账号
     */
    @TableField("mobile_user_id")
    private String mobileUserId;
    /**
     * 用户id
     */
    @TableField("ylt_user_id")
    private Long yltUserId;
    /**
     * 移动用户虚拟号
     */
    @TableField("vitual_mobile")
    private String vitualMobile;
    /**
     * 逻辑删除：0正常   1-逻辑删除  默认为0
     */
    @TableField("isDel")
    private Integer isDel;
    /**
     * 移动手机号
     */
    @TableField("mobile_phone")
    private String mobilePhone;
    /**
     * 移动用户虚拟号
     */
    @TableField("passwd")
    private String passwd;

    /**
     * 记录产生时间
     */
    @TableField("create_dt")
    private Date createDate;
    /**
     * 记录更新时间
     */
    @TableField("update_dt")
    private Date updateDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobileUserId() {
        return mobileUserId;
    }

    public void setMobileUserId(String mobileUserId) {
        this.mobileUserId = mobileUserId;
    }

    public Long getYltUserId() {
        return yltUserId;
    }

    public void setYltUserId(Long yltUserId) {
        this.yltUserId = yltUserId;
    }

    public String getVitualMobile() {
        return vitualMobile;
    }

    public void setVitualMobile(String vitualMobile) {
        this.vitualMobile = vitualMobile;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TbMobileUser{" +
                "id=" + id +
                ", mobileUserId='" + mobileUserId + '\'' +
                ", yltUserId=" + yltUserId +
                ", vitualMobile='" + vitualMobile + '\'' +
                ", isDel=" + isDel +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", passwd='" + passwd + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }


}
