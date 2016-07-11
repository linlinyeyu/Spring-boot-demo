package com.edi.domain;

import java.io.Serializable;
import javax.persistence.*;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.Date;

/**
 * 商家表
 * @author jjweng@ibenben.com
 *
 */
@Entity
@Table(name="ws_merchant")
public class WsMerchant implements Serializable {
	private static final long serialVersionUID = 7735445320730911744L;
	/**
	 * 商家id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column  (name = "merchant_id")
	private Integer merchantId;
	/**
	 * 名称
	 */
	@Column  (name = "merchant_name")
	private String merchantName;
	/**
	 * 
	 */
	@Column  (name = "platform_id")
	private Integer platformId;
	/**
	 * 
	 */
	@Column  (name = "area_id")
	private Integer areaId;
	/**
	 * 
	 */
	@Column  (name = "merchant_type")
	private String merchantType;
	/**
	 * 
	 */
	@Column  (name = "merchant_tel")
	private String merchantTel;
	/**
	 * 
	 */
	@Column  (name = "enabled")
	private Integer enabled;
	/**
	 * 创建时间
	 */
	@Column  (name = "created_time")
	private Date createdTime;
	/**
	 * 创建人
	 */
	@Column  (name = "created_user")
	private String createdUser;
	/**
	 * 
	 */
	@Column  (name = "last_updated_user")
	private String lastUpdatedUser;
	/**
	 * 
	 */
	@Column  (name = "last_updated_time")
	private Date lastUpdatedTime;
	/**
	 * 
	 */
	@Column  (name = "app_key")
	private String appKey;
	/**
	 * 
	 */
	@Column  (name = "secret")
	private String secret;
	/**
	 * 
	 */
	@Column  (name = "session_key")
	private String sessionKey;

	/**
	 * 商家id
	 */
	public void setMerchantId(Integer merchantId){
		this.merchantId=merchantId;
	}
	/**
	 * 商家id
	 */
	public Integer getMerchantId(){
		return merchantId;
	}
	/**
	 * 名称
	 */
	public void setMerchantName(String merchantName){
		this.merchantName=merchantName;
	}
	/**
	 * 名称
	 */
	public String getMerchantName(){
		return merchantName;
	}
	/**
	 * 
	 */
	public void setPlatformId(Integer platformId){
		this.platformId=platformId;
	}
	/**
	 * 
	 */
	public Integer getPlatformId(){
		return platformId;
	}
	/**
	 * 
	 */
	public void setAreaId(Integer areaId){
		this.areaId=areaId;
	}
	/**
	 * 
	 */
	public Integer getAreaId(){
		return areaId;
	}
	/**
	 * 
	 */
	public void setMerchantType(String merchantType){
		this.merchantType=merchantType;
	}
	/**
	 * 
	 */
	public String getMerchantType(){
		return merchantType;
	}
	/**
	 * 
	 */
	public void setMerchantTel(String merchantTel){
		this.merchantTel=merchantTel;
	}
	/**
	 * 
	 */
	public String getMerchantTel(){
		return merchantTel;
	}
	/**
	 * 
	 */
	public void setEnabled(Integer enabled){
		this.enabled=enabled;
	}
	/**
	 * 
	 */
	public Integer getEnabled(){
		return enabled;
	}
	/**
	 * 创建时间
	 */
	public void setCreatedTime(Date createdTime){
		this.createdTime=createdTime;
	}
	/**
	 * 创建时间
	 */
	public Date getCreatedTime(){
		return createdTime;
	}
	/**
	 * 创建人
	 */
	public void setCreatedUser(String createdUser){
		this.createdUser=createdUser;
	}
	/**
	 * 创建人
	 */
	public String getCreatedUser(){
		return createdUser;
	}
	/**
	 * 
	 */
	public void setLastUpdatedUser(String lastUpdatedUser){
		this.lastUpdatedUser=lastUpdatedUser;
	}
	/**
	 * 
	 */
	public String getLastUpdatedUser(){
		return lastUpdatedUser;
	}
	/**
	 * 
	 */
	public void setLastUpdatedTime(Date lastUpdatedTime){
		this.lastUpdatedTime=lastUpdatedTime;
	}
	/**
	 * 
	 */
	public Date getLastUpdatedTime(){
		return lastUpdatedTime;
	}
	/**
	 * 
	 */
	public void setAppKey(String appKey){
		this.appKey=appKey;
	}
	/**
	 * 
	 */
	public String getAppKey(){
		return appKey;
	}
	/**
	 * 
	 */
	public void setSecret(String secret){
		this.secret=secret;
	}
	/**
	 * 
	 */
	public String getSecret(){
		return secret;
	}
	/**
	 * 
	 */
	public void setSessionKey(String sessionKey){
		this.sessionKey=sessionKey;
	}
	/**
	 * 
	 */
	public String getSessionKey(){
		return sessionKey;
	}
}

