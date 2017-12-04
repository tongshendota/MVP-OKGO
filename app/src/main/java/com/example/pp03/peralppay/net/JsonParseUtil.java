/**  
 * 工程: Coyote<p>
 * 标题: JsonParseUtil.java<p>
 * 包:   com.esun.ui.net.basic<p>
 * 描述: TODO<p>
 * 作者: 梅雄新 meixx@500wan.com<p>
 * 时间: 2014-7-25 下午8:19:42<p>
 * 版权: Copyright 2014 Shenzhen E-sun Skey Net Tech Co.,Ltd.<p>
 * All rights reserved.<p>
 *
 */

package com.example.pp03.peralppay.net;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 类: JsonParseUtil<p>
 * 描述: 防止混淆JSON  JSONARRAY<p>
 * 作者: 梅雄新 meixx@500wan.com<p>
 * 时间: 2014-7-25 下午8:19:42<p><p>
 */
public class JsonParseUtil {
   
	// 把JSON文本parse为JSONObject或者JSONArray 
	public static final Object parse(String text){
		return JSON.parse(text);
	}
	// 把JSON文本parse成JSONObject    
	public static final JSONObject parseObject(String text){
		return JSON.parseObject(text);
	}
	
	// 把JSON文本parse为JavaBean 
	public static final <T extends ReflectBean> T parseObject(String text, Class<T > clazz){
		return JSON.parseObject(text, clazz);
	}
	// 把JSON文本parse成JSONArray 
	public static final JSONArray parseArray(String text){
		return JSON.parseArray(text);
	}
	//把JSON文本parse成JavaBean集合
	public static final  <T extends ReflectBean> List<T> parseArray(String text, Class<T> clazz){
		return JSON.parseArray(text, clazz);
	}
	// 将JavaBean序列化为JSON文本
	public static final String toJSONString(Object object){
		return JSON.toJSONString(object);
	}
	// 将JavaBean序列化为带格式的JSON文本 
	public static final String toJSONString(Object object, boolean prettyFormat){
		return JSON.toJSONString(object,prettyFormat);
	}
	//将JavaBean转换为JSONObject或者JSONArray。
	public static final Object toJSON(Object javaObject){
		return JSON.toJSON(javaObject);
	}
}
