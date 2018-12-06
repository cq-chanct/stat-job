package com.chanct.core.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


public class JSONUtil {
	/**
	 * 数组转JSON
	 * @param object
	 * @return
	 */
	public static String arrayToJSON(Object object) {
		Gson gson = new Gson();
		return gson.toJson(object).toString();
	}
	/**
	 * 将对象转换为json字符串对象
	 * 
	 * @param object
	 * @Description:
	 */
	public static  String objectToJSON(Object object) {
		return new Gson().toJson(object);
	}
	
	public static String objectToJSONWithNull(Object object){
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.toJson(object);
	}
	/**
	 * json转对象
	 * @param json
	 * @param cla
	 * @return
	 */
	public static <T> Object jsonToObject(String json,Class<T> cla){
		return new Gson().fromJson(json, cla);
	}
	/**
	 * json转list
	 * @param json
	 * @return 
	 */
	public static <T> List<T> jsonToList(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, new TypeToken<T>() {
		}.getType());
	}
	
	/**
	 * json转list
	 * @param json
	 * @return 
	 */
	public static <T> List<T> jsonToList2(String json,Class<T> cla) {
		
		List<T> list = new ArrayList<T>();

		JsonArray array = new JsonParser().parse(json).getAsJsonArray();
		for(final JsonElement elem : array){
		list.add(new Gson().fromJson(elem, cla));
		}
		return list;
		
	}
	
	/**
	 * json数据转map
	 * @param json 数据
	 * @return map格式
	 */
	public static Map<String, Object> jsonToMap(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, new TypeToken<Map<	String, Object>>() {
		}.getType());
	}
	
	
	public static void main(String[] args) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		 map.put("1", 11);
		 map.put("2", "2");
		 Gson gson = new Gson();
		 map = jsonToMap(gson.toJson(map));
		 System.out.println(gson.toJson(map));
		
	}
}
