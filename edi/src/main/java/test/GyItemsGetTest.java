package test;

import com.alibaba.fastjson.JSONObject;
import com.edi.util.GySign;
import com.edi.util.HttpKit;

public class GyItemsGetTest {
	public static void main(String[] args) throws Exception {
		GyItemsGetTest1();
	}

	private static void GyItemsGetTest1() throws Exception {
		//String url="https://demo.guanyierp.com/erpapi/rest/erp_open";
		String url="http://v2.api.guanyierp.com/rest/erp_open";
		//String url="http://open.guanyierp.com/rest/core";
		JSONObject json=new JSONObject();
		json.put("appkey", "161933");
		String sessionkey="e9b15a6143f74ccb8cde1ff77be2e6ee";
		String secret="be09267bef9441e19f00e8583a2b868f";
		json.put("sessionkey", sessionkey);
		json.put("method", "gy.erp.trade.get");
		//json.put("page_no", "1");
		//json.put("page_size", "10");
		json.put("start_date", "2015-06-08 00:00:01");
		json.put("platform_code", "160611-77252107656");
		//json.put("end_date", "2015-06-08 23:59:59");
		json.put("date_type", 2);
		String sign=GySign.sign(json.toString(), secret);
		System.out.println(sign);
		json.put("sign", sign);
		
		String aaa=HttpKit.postJson(url, json.toJSONString());
		System.out.println(aaa);		
	}
}
