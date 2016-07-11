package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.edi.domain.TempJoke;
import com.edi.service.JokeService;
import com.edi.util.HttpKit;

public class JokeApitest {
	private static JokeService jokeService;
	public static void main(String[] args) throws Exception{
		JokeApiConnection();
	}

	private static void JokeApiConnection() throws Exception
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
