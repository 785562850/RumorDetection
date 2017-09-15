package com.yyjc.utils;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
@Component
public class JsonObjectUtil {
	/**
	 * get请求
	 * @param url
	 * @return
	 */
	public static JSONObject doGetStr(String url,Map<String,String> params){
			StringBuffer paramStr=new StringBuffer();
			if(params!=null&&!params.isEmpty()){
				for(Entry<String, String> param:params.entrySet()){
					paramStr.append(param.getKey()+"="+param.getValue()+"&"); 
				}
			}
		 	CloseableHttpClient httpclient = HttpClients.createDefault();
		 	url="?"+paramStr.toString().substring(0,paramStr.length()-1);
		    HttpGet httpGet = new HttpGet(url);
		   
		    JSONObject jsonObject =null;
		    try {
		    	 CloseableHttpResponse response =httpclient.execute(httpGet);
				HttpEntity entity=response.getEntity();
				if(entity!=null){
					String result=EntityUtils.toString(entity,"UTF-8");
					jsonObject=JSONObject.parseObject(result);//将字符串转化为json对象
				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return jsonObject;
	}
	
	/**
	 * post请求
	 * @param url
	 * @param outStr
	 * @return
	 */
	public static JSONObject doPostStr(String url,String outStr){
	 	CloseableHttpClient httpclient = HttpClients.createDefault(); 
		HttpPost httpPost = new HttpPost(url);
		JSONObject jsonObject=null;
		httpPost.setEntity(new StringEntity(outStr,"UTF-8"));
		try {
			CloseableHttpResponse response =httpclient.execute(httpPost);
			String result=EntityUtils.toString(response.getEntity(),"UTF-8");
			jsonObject=JSONObject.parseObject(result);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}
	
}
