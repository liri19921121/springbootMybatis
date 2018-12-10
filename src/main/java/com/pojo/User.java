
package com.pojo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;


/**
 * devEntity
 * @author rstyro
 * @version v1.0
 */


/**
 * if the table has 'creater_code','creater_name','create_time' columns ,  you can write
 *  the class-head as this ' public class User extends CommonInfoModel implements java.io.Serializable  '
 */
public class User implements java.io.Serializable {
	private static final long serialVersionUID = 1L;




	/**
	 * id       db_column: id
	 */

	private Long id;
	/**
	 * 注册email       db_column: mail
	 */

	private String mail;
	/**
	 * 用户姓名       db_column: name
	 */

	private String name;
	/**
	 * 用户名       db_column: user_name
	 */

	private String userName;
	/**
	 * authMail       db_column: auth_mail
	 */

	private Boolean authMail;
	/**
	 * 是否开启google验证       db_column: auth_google
	 */

	private Boolean authGoogle;
	/**
	 * authMobile       db_column: auth_mobile
	 */

	private Boolean authMobile;
	/**
	 * 手机号码       db_column: mobile
	 */

	private String mobile;
	/**
	 * 注册来源       db_column: source
	 */

	private String source;
	/**
	 * 用户秘钥       db_column: password
	 */

	private String password;
	/**
	 * 交易密码       db_column: deal_password
	 */

	private String dealPassword;
	/**
	 * 用户状态 0 表示删除 1 表示可用 2 表示 未认证 3 其它       db_column: status
	 */

	private Byte status;
	/**
	 * 最后登录时间       db_column: gmt_lastlogin
	 */

	private Date gmtLastlogin;
	/**
	 * 最后登录ip       db_column: last_login_ip
	 */

	private String lastLoginIp;
	/**
	 * 开启google 二重验证的密钥       db_column: google_secret
	 */

	private String googleSecret;
	/**
	 * 头像       db_column: picture
	 */

	private String picture;
	/**
	 * createTime       db_column: create_time
	 */

	private Date createTime;
	/**
	 * createBy       db_column: create_by
	 */

	private Long createBy;
	/**
	 * modifyTime       db_column: modify_time
	 */

	private Date modifyTime;
	/**
	 * modifyBy       db_column: modify_by
	 */

	private Long modifyBy;
	/**
	 * 社交渠道       db_column: social_channel
	 */

	private String socialChannel;
	/**
	 * 社交账号       db_column: social_account
	 */

	private String socialAccount;
	//columns END


	public User(){

	}

	public User(
			Long id
	){
		this.id = id;
	}




	public void setId(Long value) {
		this.id = value;
	}

	public Long getId() {
		return this.id;
	}

	//
	public String getMail() {
		return this.mail;
	}

	public void setMail(String value) {
		this.mail = value;
	}

	//
	public String getName() {
		return this.name;
	}

	public void setName(String value) {
		this.name = value;
	}

	//
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String value) {
		this.userName = value;
	}

	//
	public Boolean getAuthMail() {
		return this.authMail;
	}

	public void setAuthMail(Boolean value) {
		this.authMail = value;
	}

	//
	public Boolean getAuthGoogle() {
		return this.authGoogle;
	}

	public void setAuthGoogle(Boolean value) {
		this.authGoogle = value;
	}

	//
	public Boolean getAuthMobile() {
		return this.authMobile;
	}

	public void setAuthMobile(Boolean value) {
		this.authMobile = value;
	}

	//
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String value) {
		this.mobile = value;
	}

	//
	public String getSource() {
		return this.source;
	}

	public void setSource(String value) {
		this.source = value;
	}

	//
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String value) {
		this.password = value;
	}

	//
	public String getDealPassword() {
		return this.dealPassword;
	}

	public void setDealPassword(String value) {
		this.dealPassword = value;
	}

	//
	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte value) {
		this.status = value;
	}

	//
//
//	public String getGmtLastloginString() {
//		if(getGmtLastlogin()==null){
//			return "";
//		}
//		return DateUtils.getDateString(getGmtLastlogin(), FORMAT_GMT_LASTLOGIN);
//	}
//	public void setGmtLastloginString(String value) {
//		if(!StringUtils.isBlank(value)){
//			setGmtLastlogin(DateUtils.stringToDate(value, FORMAT_GMT_LASTLOGIN));
//		}
//	}
//
//
	public Date getGmtLastlogin() {
		return this.gmtLastlogin;
	}

	public void setGmtLastlogin(Date value) {
		this.gmtLastlogin = value;
	}

	//
	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	public void setLastLoginIp(String value) {
		this.lastLoginIp = value;
	}

	//
	public String getGoogleSecret() {
		return this.googleSecret;
	}

	public void setGoogleSecret(String value) {
		this.googleSecret = value;
	}

	//
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String value) {
		this.picture = value;
	}

	//
//
//	public String getCreateTimeString() {
//		if(getCreateTime()==null){
//			return "";
//		}
//		return DateUtils.getDateString(getCreateTime(), FORMAT_CREATE_TIME);
//	}
//	public void setCreateTimeString(String value) {
//		if(!StringUtils.isBlank(value)){
//			setCreateTime(DateUtils.stringToDate(value, FORMAT_CREATE_TIME));
//		}
//	}
//
//
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date value) {
		this.createTime = value;
	}

	//
	public Long getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(Long value) {
		this.createBy = value;
	}

	//
//
//	public String getModifyTimeString() {
//		if(getModifyTime()==null){
//			return "";
//		}
//		return DateUtils.getDateString(getModifyTime(), FORMAT_MODIFY_TIME);
//	}
//	public void setModifyTimeString(String value) {
//		if(!StringUtils.isBlank(value)){
//			setModifyTime(DateUtils.stringToDate(value, FORMAT_MODIFY_TIME));
//		}
//	}
//
//
	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date value) {
		this.modifyTime = value;
	}

	//
	public Long getModifyBy() {
		return this.modifyBy;
	}

	public void setModifyBy(Long value) {
		this.modifyBy = value;
	}

	//
	public String getSocialChannel() {
		return this.socialChannel;
	}

	public void setSocialChannel(String value) {
		this.socialChannel = value;
	}

	//
	public String getSocialAccount() {
		return this.socialAccount;
	}

	public void setSocialAccount(String value) {
		this.socialAccount = value;
	}


	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("Id",getId())
				.append("Mail",getMail())
				.append("Name",getName())
				.append("UserName",getUserName())
				.append("AuthMail",getAuthMail())
				.append("AuthGoogle",getAuthGoogle())
				.append("AuthMobile",getAuthMobile())
				.append("Mobile",getMobile())
				.append("Source",getSource())
				.append("Password",getPassword())
				.append("DealPassword",getDealPassword())
				.append("Status",getStatus())
				.append("GmtLastlogin",getGmtLastlogin())
				.append("LastLoginIp",getLastLoginIp())
				.append("GoogleSecret",getGoogleSecret())
				.append("Picture",getPicture())
				.append("CreateTime",getCreateTime())
				.append("CreateBy",getCreateBy())
				.append("ModifyTime",getModifyTime())
				.append("ModifyBy",getModifyBy())
				.append("SocialChannel",getSocialChannel())
				.append("SocialAccount",getSocialAccount())
				.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder()
				.append(getId())
				.toHashCode();
	}

	public boolean equals(Object obj) {
		if(obj instanceof User == false) return false;
		if(this == obj) return true;
		User other = (User)obj;
		return new EqualsBuilder()
				.append(getId(),other.getId())
				.isEquals();
	}
}

