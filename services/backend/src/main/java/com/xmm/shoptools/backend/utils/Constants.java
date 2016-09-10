package com.xmm.shoptools.backend.utils;

public class Constants {

	public static final Integer PAGE_SIZE = 20;
	public static final String UTF8 = "UTF-8";
	public static final String USER_IN_SESSION = "user_in_session";

	// token服务端key
	public static final String MD5_TOKEN_KEY = "70Frh*FrE8bBq@z5m^VU*p5F#x2e7Yuu";

	public static final String ERROR_SUCCESS = "操作成功";
	public static final String ERROR_FAILD = "操作失败";
	public static final String ERROR_TIMEOUT = "登录超时，请重新登录！";
	public static final String ERROR_PERMISSION = "对不起，您没有权限！";

	public static final Integer LEVEL_F1 = -1; // 无权限
	public static final Integer LEVE_0 = 0; // 可见
	public static final Integer LEVEL_1 = 1;// 可操作
	public static final Integer LEVEL_2 = 2;// 可见不可操作
}
