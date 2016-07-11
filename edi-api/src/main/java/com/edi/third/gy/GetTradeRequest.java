package com.edi.third.gy;
import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;


/**
 * wms:取消订单接口
 * @author jjweng@ibenben.com
 *
 */
public class GetTradeRequest extends GyErpRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1113767122298970852L;
	/**
	 * 开始时间
	 */
	private String  start_date;
	/**
	 * 结束时间
	 */
	private String  end_date;
	/**
	 * 时间类型
	 */
	private Integer  date_type;
	/**
	 * 订单类型
	 */
	private Integer  order_state;
	/**
	 * 仓库代码
	 */
	private String  warehouse_code;
	/**
	 * 店铺代码
	 */
	private String  shop_code;
	/**
	 * 会员名称
	 */
	private String  vip_name;
	/**
	 * 平台单号
	 */
	private String  platform_code;
	/**
	 * 收件手机
	 */
	private String  receiver_mobile;
	

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public Integer getDate_type() {
		return date_type;
	}

	public void setDate_type(Integer date_type) {
		this.date_type = date_type;
	}

	public Integer getOrder_state() {
		return order_state;
	}

	public void setOrder_state(Integer order_state) {
		this.order_state = order_state;
	}

	public String getWarehouse_code() {
		return warehouse_code;
	}

	public void setWarehouse_code(String warehouse_code) {
		this.warehouse_code = warehouse_code;
	}

	public String getShop_code() {
		return shop_code;
	}

	public void setShop_code(String shop_code) {
		this.shop_code = shop_code;
	}

	public String getVip_name() {
		return vip_name;
	}

	public void setVip_name(String vip_name) {
		this.vip_name = vip_name;
	}

	public String getPlatform_code() {
		return platform_code;
	}

	public void setPlatform_code(String platform_code) {
		this.platform_code = platform_code;
	}

	public String getReceiver_mobile() {
		return receiver_mobile;
	}

	public void setReceiver_mobile(String receiver_mobile) {
		this.receiver_mobile = receiver_mobile;
	}

	@Override
	public String createUrl() {
		return super.createUrl();
	}

	@Override
	public String createJson() {
		JSONObject json=new JSONObject();
		method="gy.erp.trade.get";
		json.put("start_date", start_date);
		json.put("end_date", end_date);
		json.put("date_type", date_type);
		json.put("order_state", order_state);
		json.put("warehouse_code", warehouse_code);
		json.put("shop_code", shop_code);
		json.put("vip_name", vip_name);
		json.put("platform_code", platform_code);
		json.put("receiver_mobile", receiver_mobile);
		return super.createJson(json);
	}
}
