package com.qdsg.ylt.ylt_jwt.model;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @ClassName: JwtUserDetail
 * @Description:实现spring security 定义的用户信息接口，自定自己的用户信息
 * @author: ZJ
 * @date: 2018年8月1日 下午5:21:58
 * @version 1.0
 * @Copyright: 2018 www.qdsgvision.com Inc. All rights reserved.
 */
public class TbUser {

    private Long userId;
    private Long avatar;
    private String account;
    private Date birth;
    private String sex;
    private String mobile;
    private String email;
    private Integer flag;// 0锁定 1有效 2待审核
    private Boolean isCredentialsNonExpired;// 凭据是否过期

    public TbUser() {
    }

    public TbUser(Long userId, Long avatar, String account, Date birth, String sex, String mobile, String email, Integer flag, Boolean isCredentialsNonExpired) {
        this.userId = userId;
        this.avatar = avatar;
        this.account = account;
        this.birth = birth;
        this.sex = sex;
        this.mobile = mobile;
        this.email = email;
        this.flag = flag;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Boolean getIsCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setIsCredentialsNonExpired(Boolean isCredentialsNonExpired) {
        this.isCredentialsNonExpired = isCredentialsNonExpired;
    }

    /**
     * 用户密码
     */
    public String getPassword() {
        return "******";
    }

    /**
     * 用户id
     */
    public String getUsername() {
        return String.valueOf(userId);
    }

    /**
     * 账户是否过期 0代表锁定
     */
    public boolean isAccountNonExpired() {
        return flag == 0 || flag == 2;
    }

    /**
     * 账户是否被冻结
     */
    public boolean isAccountNonLocked() {
        return flag == 0 || flag == 2;
    }

    /**
     * token是否过期
     */
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }


}