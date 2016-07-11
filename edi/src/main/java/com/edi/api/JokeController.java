package com.edi.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.edi.domain.TempJoke;
import com.edi.service.JokeService;
import com.edi.service.UserService;
import com.edi.util.HttpKit;

@Controller
@RequestMapping("/joke")
public class JokeController {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private JokeService jokeService;
	@RequestMapping(value ="/addjoke")
	@ResponseBody
	public void addJoke()
	{
		String url = "http://apis.baidu.com/showapi_open_bus/showapi_joke/joke_text";
		Map<String, String> params=new HashMap<String, String>();
		Map<String, String> headers=new HashMap<String, String>();
		params.put("page", "1");
		headers.put("apikey", "25688d87de6d6d799327469477bf314c");

		JSONObject json = new JSONObject();
		String joke = HttpKit.get(url,params,headers);
		json = JSONObject.parseObject(joke);
		String joke_body = json.getString("showapi_res_body");
		json = JSONObject.parseObject(joke_body);
		String joke_list = json.getString("contentlist");
		List<TempJoke> jokes = JSONObject.parseArray(joke_list, TempJoke.class);
		for(TempJoke joke_content:jokes)
		{
			jokeService.addJoke(joke_content);
		}
	}
}
