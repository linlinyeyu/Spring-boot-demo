/**
 * 微信公众平台开发模式(JAVA) SDK
 */
package com.edi.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

/**
 * 功能描述：https 请求 微信为https的请求
 * 
 */
public class HttpKit {

	private static Log logger = LogFactory.getLog(HttpKit.class);
	private static final String CHARSET = "UTF-8";

	/**
	 * 功能描述: get 请求
	 * 
	 * @param url
	 * @param params
	 * @param headers
	 * @return
	 */
	public static String get(String url, Map<String, String> params,Map<String, String> headers) {
		HttpRespons httpres;
		try {
			httpres = sendPost(url,"GET",params,headers);
			if(httpres!=null){
				return httpres.getContent();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 功能描述：get 请求
	 * 
	 * @param url
	 * @return
	 * @throws IOException 
	 */
	public static String get(String url) throws IOException {
		HttpRespons httpres=sendPost(url,"GET",null,null);
		if(httpres!=null){
			return httpres.getContent();
		}
		return null;
	}

	/**
	 * 功能描述: POST 请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException 
	 */
	public static String post(String url, Map<String, String> params){
		HttpRespons httpres;
		try {
			httpres = sendPost(url,"POST",params,null);
			if(httpres!=null){
				return httpres.getContent();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * json数据格式
	 * @param url
	 * @param params
	 * @return
	 */
	public static String post(String url, String params) {
		try {
			return postJson(url, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 功能描述：post请求
	 * 
	 * @param url
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public static String postJson(String url, String json) throws Exception {
		byte[] data = json.getBytes(CHARSET);
		URL urL = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) urL.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		conn.setRequestProperty("Content-Length", String.valueOf(data.length));
		conn.setConnectTimeout(5 * 1000);
		OutputStream outStream = conn.getOutputStream();
		outStream.write(data);
		outStream.flush();
		outStream.close();
		if (conn.getResponseCode() == 200) {
			byte[] read2Byte = read2Byte(conn.getInputStream());
			return new String(read2Byte, CHARSET);
		}else{
			HttpStatus hs=HttpStatus.valueOf(conn.getResponseCode());
			throw new HttpClientErrorException(hs, "返回数据出错："+hs);
		}
		//return null;
	}

	/**
	 * 二进制数据转换
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] read2Byte(InputStream inStream) throws Exception {
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		return outSteam.toByteArray();
	}

	
	
	/**
     * 发送HTTP请求
     * 
     * @param urlString 地址
     * @param method  get/post
     * @param parameters  添加由键值对指定的请求参数
     * @param propertys  添加由键值对指定的一般请求属性
     * @return 响映对象
     * @throws IOException
     */ 
    private static HttpRespons sendPost(String urlString, String method, Map<String, String> parameters, 
            Map<String, String> propertys) throws IOException { 
        HttpURLConnection urlConnection = null; 
   
        if (method.equalsIgnoreCase("GET") && parameters != null) { 
            StringBuffer param = new StringBuffer(); 
            int i = 0; 
            for (String key : parameters.keySet()) { 
                if (i == 0) 
                    param.append("?"); 
                else 
                    param.append("&"); 
                param.append(key).append("=").append(parameters.get(key)); 
                i++; 
            } 
            urlString += param; 
        } 
   
        URL url = new URL(urlString); 
        urlConnection = (HttpURLConnection) url.openConnection(); 
        urlConnection.setRequestMethod(method); 
        urlConnection.setDoOutput(true); 
        urlConnection.setDoInput(true); 
        urlConnection.setUseCaches(false); 
   
        if (propertys != null) 
            for (String key : propertys.keySet()) { 
                urlConnection.addRequestProperty(key, propertys.get(key)); 
            } 
   
        if (method.equalsIgnoreCase("POST") && parameters != null) { 
            StringBuffer param = new StringBuffer(); 
            for (String key : parameters.keySet()) { 
                param.append("&"); 
                param.append(key).append("=").append(parameters.get(key)); 
            } 
            urlConnection.getOutputStream().write(param.toString().getBytes()); 
            urlConnection.getOutputStream().flush(); 
            urlConnection.getOutputStream().close(); 
        } 
        return makeContent(urlString, urlConnection); 
    } 
   
    /**
     * 得到响应对象
     * 
     * @param urlConnection
     * @return 响应对象
     * @throws IOException
     */ 
    private static HttpRespons makeContent(String urlString, HttpURLConnection urlConnection) throws IOException { 
        HttpRespons httpResponser = new HttpRespons(); 
        try { 
            InputStream in = urlConnection.getInputStream(); 
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in)); 
            httpResponser.contentCollection = new Vector<String>(); 
            StringBuffer temp = new StringBuffer(); 
            String line = bufferedReader.readLine(); 
            while (line != null) { 
                httpResponser.contentCollection.add(line); 
                temp.append(line).append("\r\n"); 
                line = bufferedReader.readLine(); 
            } 
            bufferedReader.close(); 
            String ecod = urlConnection.getContentEncoding(); 
            if (ecod == null) 
                ecod = CHARSET; 
            httpResponser.urlString = urlString; 
            httpResponser.defaultPort = urlConnection.getURL().getDefaultPort(); 
            httpResponser.file = urlConnection.getURL().getFile(); 
            httpResponser.host = urlConnection.getURL().getHost(); 
            httpResponser.path = urlConnection.getURL().getPath(); 
            httpResponser.port = urlConnection.getURL().getPort(); 
            httpResponser.protocol = urlConnection.getURL().getProtocol(); 
            httpResponser.query = urlConnection.getURL().getQuery(); 
            httpResponser.ref = urlConnection.getURL().getRef(); 
            httpResponser.userInfo = urlConnection.getURL().getUserInfo(); 
            httpResponser.content = new String(temp.toString().getBytes(), ecod); 
            httpResponser.contentEncoding = ecod; 
            httpResponser.code = urlConnection.getResponseCode(); 
            httpResponser.message = urlConnection.getResponseMessage(); 
            httpResponser.contentType = urlConnection.getContentType(); 
            httpResponser.method = urlConnection.getRequestMethod(); 
            httpResponser.connectTimeout = urlConnection.getConnectTimeout(); 
            httpResponser.readTimeout = urlConnection.getReadTimeout(); 
            return httpResponser; 
        } catch (IOException e) { 
            throw e; 
        } finally { 
            if (urlConnection != null) 
                urlConnection.disconnect(); 
        } 
    } 
   
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		// String accessToken =
		// "ulhEL9F2CciJezmGj47C-d3hAJZwXiAANctVIwSHwBRK7Z1enIRWeZKZekk8jS5abIkCo2YmMSDlqUFKOKvSaw";
		// String openId = "oeZTVt6XlCphRnCI-DlpdTyk27p4";
		// UserInfo u = WeChat.user.getUserInfo(accessToken, openId);
		// logger.info(JSON.toJSONString(u));
		// logger.info(WeChat.message.sendText(accessToken , openId ,
		// "测试"));
		// Map<String, Object> mgs = WeChat.uploadMedia(accessToken, "image",
		// new File("C:\\Users\\郭华\\Pictures\\13.jpg"));
		// logger.info(JSON.toJSONString(mgs));
	}
}