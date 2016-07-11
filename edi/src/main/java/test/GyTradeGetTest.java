package test;

import com.alibaba.fastjson.JSONObject;
import com.edi.third.gy.GetTradeRequest;
import com.edi.util.HttpKit;

public class GyTradeGetTest {
	public static void main(String[] args) throws Exception {
	/*	GetTradeRequest r=new GetTradeRequest();
		//r.setDate_type(0);
		//r.setPage_no(2);
		r.setPage_size(5);
		r.setStart_date("2015-06-08 00:00:01");
		r.setOrder_state(2);
		System.out.println(r.createUrl());
		System.out.println(r.createJson());
		String aaa=HttpKit.postJson(r.createUrl(), r.createJson());
		System.out.println(aaa);
		*/
		String a="{'user':'user','age':'22'}";
		JSONObject json=JSONObject.parseObject(a);
		System.out.println(json.getString("name"));
		//json.getJSONObject(user);
		String key="{order,order,order}";
		
	}
}
