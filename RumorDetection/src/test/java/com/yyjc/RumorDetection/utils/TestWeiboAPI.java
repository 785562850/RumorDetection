package com.yyjc.RumorDetection.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.yyjc.domain.po.AccessToken;
import com.yyjc.utils.WeiboAPI;

public class TestWeiboAPI {
	@Test
	public void testGetCookie() {
		Map<String,String> params=new HashMap<String, String>();
		params.put("a", "1");
		params.put("b", "2");
		StringBuffer paramStr=new StringBuffer();
		if(params!=null&&!params.isEmpty()){
			for(Entry<String, String> param:params.entrySet()){
				paramStr.append(param.getKey()+":"+param.getValue()+','); 
			}
		}
		
		String url="/"+paramStr.toString().substring(0,paramStr.length()-1);
		System.out.println(JSONObject.parseObject('{'+paramStr.toString().substring(0,paramStr.length()-1)+'}'));
		System.out.println('{'+paramStr.toString().substring(0,paramStr.length()-1)+'}');
	}
}
