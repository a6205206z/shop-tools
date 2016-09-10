package com.xmm.shoptools.backend.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

	public static void writeCookie(String key, String value, int maxAge,
			HttpServletResponse response) {
		Cookie cookie = new Cookie(key, value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	public static void writeCookie(String key, String value,
			HttpServletResponse response) {
		Cookie cookie = new Cookie(key, value);
		cookie.setPath("/");
		cookie.setMaxAge(7200);
		response.addCookie(cookie);
	}

	/**
	 * @param request
	 *            请求
	 * @param key
	 *            主键
	 * @return String 读取key 对应的值
	 */
	public static String readCookie(String key, HttpServletRequest request) {
		String value = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {

			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				if (c.getName().equalsIgnoreCase(key)) {
					value = c.getValue();
					return value;
				}
			}
		}
		return value;
	}
	
	//服务端过滤cookie值
	public static void delCookie(String key, HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {

			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				if (c.getName().equalsIgnoreCase(key)) {
					c.setMaxAge(0);
					c.setValue("");
				}
			}
		}
	}

}