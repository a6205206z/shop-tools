package com.xmm.shoptools.backend.dao.base;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 序列化json串
 * 
 * @date 2014年12月11日上午10:10:06
 * @version 1.0
 */
public class JsonRedisSeriaziler {
	public static final String empty_json = "{}";

	private JsonRedisSeriaziler() {
	}

	/**
	 * @param object
	 * @return
	 */
	public static String seriazileAsString(Object object) {
		if (null == object) {
			return empty_json;
		}
		return JSON.toJSONString(object);
	}

	/**
	 * @param str
	 * @param clazz
	 * @return
	 */
	public static <T> T deserializeAsObject(String str, Class<T> clazz) {
		if (str == null || clazz == null) {
			return null;
		}
		return JSON.parseObject(str, clazz);
	}

	/**
	 * @param str
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> deserializeAsList(String str, Class<T> clazz) {
		if (str == null || clazz == null) {
			return null;
		}
		return JSON.parseArray(str, clazz);
	}

}