package com.edi.third.gy;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.edi.third.BaseRequset;
import com.edi.util.GySign;

/**
 * 管易ERP API平台
 * @author jjweng@ibenben.com
 *
 */
@SuppressWarnings("serial")
abstract public class GyErpRequest implements BaseRequset, Serializable {
	/**	
	 * 接口地址
	 */
	private String url;
	private String appkey;
	private String secret;
	private String sessionkey;
	protected String method;
	private String sign;
	protected Integer page_no;
	private Integer page_size;
	
	public GyErpRequest() {
		url=System.getProperty("fulfiller.1");
		if(url==null){
			//API地址(V2环境)
			url = "http://v2.api.guanyierp.com/rest/erp_open";
		}
		String appkey_secret_sessionkey=System.getProperty("WsMerchant.鲜好货");
		if(appkey_secret_sessionkey!=null){
			appkey=appkey_secret_sessionkey.split(";")[0];
			secret=appkey_secret_sessionkey.split(";")[1];
			sessionkey=appkey_secret_sessionkey.split(";")[2];
		}
		else{
			appkey="161933";
			secret="be09267bef9441e19f00e8583a2b868f";
			sessionkey="e9b15a6143f74ccb8cde1ff77be2e6ee";
		}
	}

	public Integer getPage_no() {
		return page_no;
	}

	public void setPage_no(Integer page_no) {
		this.page_no = page_no;
	}

	public Integer getPage_size() {
		return page_size;
	}

	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}

	/**
	 * 生成sign 传输Json报文
	 * @return
	 */
	public String createJson(JSONObject json){
		json.put("page_no", page_no);
		json.put("page_size", page_size);
		json.put("appkey", appkey);
		json.put("sessionkey", sessionkey);
		json.put("method", method);
		sign=GySign.sign(json.toString(), secret);
		json.put("sign", sign);
		return json.toString();
	}
	/**
	 * 获取接口地址
	 * @return
	 */
	public String createUrl() {
		return url;
	}

}
