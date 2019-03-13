package com.qdsg.ylt.ylt_user.common.model;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @ClassName: JwtUserDetail
 * @Description:实现spring security 定义的用户信息接口，自定自己的用户信息
 * @author: ZJ
 * @date: 2018年8月1日 下午5:21:58
 * @version 1.0
 * @Copyright: 2018 www.qdsgvision.com Inc. All rights reserved.
 */
public class JwtUserDetail implements UserDetails {

	private Long userId;
	private Long avatar;
	private String account;
	private Date birth;
	private String sex;
	private String mobile;
	private String email;
	private Collection<GrantedAuthority> authorities;// 用户加色集合
	private Integer flag;// 0锁定 1有效 2待审核
	private Boolean isCredentialsNonExpired;// 凭据是否过期
	private Boolean isEnabled;// 使能

	public JwtUserDetail() {
	}

	public JwtUserDetail(Long userId, Long avatar, String account, Date birth, String sex, String mobile, String email, Collection<GrantedAuthority> authorities, Integer flag, Boolean isCredentialsNonExpired, Boolean isEnabled) {
		this.userId = userId;
		this.avatar = avatar;
		this.account = account;
		this.birth = birth;
		this.sex = sex;
		this.mobile = mobile;
		this.email = email;
		this.authorities = authorities;
		this.flag = flag;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
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

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	/**
	 * 用户权限集合
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/**
	 * 用户密码
	 */
	@Override
	public String getPassword() {
		return "xxx";
	}

	/**
	 * 用户id
	 */
	@Override
	public String getUsername() {
		return String.valueOf(userId);
	}

	/**
	 * 账户是否过期 0代表锁定
	 */
	@Override
	public boolean isAccountNonExpired() {
		return flag == 0 || flag == 2;
	}

	/**
	 * 账户是否被冻结
	 */
	@Override
	public boolean isAccountNonLocked() {
		return flag == 0 || flag == 2;
	}

	/**
	 * token是否过期
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	/**
	 * 使能
	 */
	@Override
	public boolean isEnabled() {
		return isEnabled;
	}
}
