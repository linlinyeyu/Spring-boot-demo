package com.edi.third;


/**
 * 管易ERP:api接口
 * http://gop.guanyierp.com/
 * @author jjweng@ibenben.com
 *
 */
public interface BaseRequset {
	/**
	 * 生成传输报文
	 * @param params
	 * @return
	 */
	public String createJson();

	/**
	 * 产生签名数字并且返回接口请求地址<br>
	 * 对接口地址的字段进行签名，然后返回接口地址
	 * 
	 * @return
	 */
	public String createUrl();
}
