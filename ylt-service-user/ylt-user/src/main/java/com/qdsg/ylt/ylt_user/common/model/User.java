package com.qdsg.ylt.ylt_user.common.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 前台表用户

 * </p>
 *
 * @author stylefeng123
 * @since 2018-06-12
 */
@TableName("tb_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 头像id
     */
    private Long avatar;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * md5密码盐
     */
    private String salt;
    private Date birth;
    private String sex;
    private String mobile;
    private String email;
//    private String idcard;
    /**
     * 创建时间
     */
    @TableField("create_date")
    private Date createDate;
    /**
     * 状态 0:锁定 1:有效 
     */
    private Integer flag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAvatar() {
        return avatar;
    }

    public void setAvatar(Long avatar) {
        this.avatar = avatar;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

//    public String getIdcard() {
//        return idcard;
//    }
//
//    public void setIdcard(String idcard) {
//        this.idcard = idcard;
//    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", avatar=" + avatar +
        ", account=" + account +
        ", password=" + password +
        ", salt=" + salt +
        ", birth=" + birth +
        ", mobile=" + mobile +
        ", email=" + email +
        ", sex=" + sex +
        ", createDate=" + createDate +
        ", flag=" + flag +
        "}";
    }
}
