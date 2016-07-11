package com.edi.domain;

import java.io.Serializable;

/**
 * GPS信息
 * 
 * @author zxl0047
 *
 */
@SuppressWarnings("serial")
public class Gps implements Serializable{
	
	public Gps() {

	}

	public Gps(Double lat, Double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}

	/**
	 * 纬度
	 */
//	@NotNull(message="纬度不能为空")
	private Double lat;
	/**
	 * 经度
	 */
//	@NotNull(message="经度不能为空")
	//@NotNull
	private Double lng;
	
	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}



}