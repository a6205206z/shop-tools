package com.xmm.shoptools.backend.utils;

public class Validate {

	public static final String Email = "[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+";

	public static final String MOBILE = "^((\\(\\d{3}\\))|(\\d{3}\\-))?1(3|5|8|4|7)\\d{9}$";

	public static final String PHONE = "^((\\(\\d{3}\\))|(\\d{3}\\-))?(\\(0\\d{2,3}\\)|0\\d{2,3}-)?[1-9]\\d{6,7}$";

	public static final String ZIP_CODE = "^[0-9]\\d{5}$";

	public static final String USER_NAME = "^\\w+$";

	public static final String YD_MOBILE = "^((13[4-9])|(147)|(178)|(1705)|(15[0-2,7-9])|(18[2-4,7-8]))(\\d{7}|\\d{8})$";
	public static final String LT_MOBILE = "^((13[0-2])|(145)|(176)|(1709)|(15[5-6])|(18[5-6]))(\\d{7}|\\d{8})$";
	public static final String DX_MOBILE = "^((133)|(153)|(177)|(1700)|(18[0,1,9]))(\\d{7}|\\d{8})$";

	public static boolean isEmail(String email) {
		return email.matches(Email);
	}

	/**
	 * 确定手机号段
	 * 
	 * @param mobile
	 *            返回 yd：移动 tl：联通 dx：电信
	 * @return
	 */
	public static String disNumber(String mobile) {
		String result = "";
		if (mobile.matches(YD_MOBILE))
			result = "yd";
		else if (mobile.matches(LT_MOBILE))
			result = "lt";
		else if (mobile.matches(DX_MOBILE))
			result = "dx";
		return result;
	}

	public static boolean isUserName(String userName) {
		return (userName.matches(USER_NAME) && userName.length() > 3
				&& userName.length() < 31) || isEmail(userName);
	}

	public static boolean isMobile(String mobile) {
		return mobile.matches(MOBILE);
	}

	public static boolean isPhone(String phone) {
		return phone.matches(PHONE);
	}

	public static boolean isZipCode(String zipCode) {
		return zipCode.matches(ZIP_CODE);
	}

	public static void main(String[] s) {
		System.out.println(isMobile("13803228941"));
	}
}
