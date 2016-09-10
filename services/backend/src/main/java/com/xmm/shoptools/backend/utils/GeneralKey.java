package com.xmm.shoptools.backend.utils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneralKey {

	// 编码
	public static final String UTF8 = "UTF-8";

	public static String convertParams(Map<String, Object> params)
			throws Exception {
		StringBuilder sb = new StringBuilder();

		if (params != null && params.size() > 0) {
			List<String> paramNames = new ArrayList<String>(params.size());
			paramNames.addAll(params.keySet());
			Collections.sort(paramNames);
			for (String paramName : paramNames) {
				String value = String.valueOf(params.get(paramName));
				value = URLEncoder.encode(value == null ? "null" : value,
						"UTF-8");
				sb.append(paramName).append("=").append(value).append("&");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	public static String sign(String url, Map<String, Object> params) {

		String sign = "";
		try {
			String paramString = convertParams(params);
			String path = "";
			if (null != url) {
				URI uri = new URI(url);
				path = uri.getHost() + uri.getPath();
			}
			sign = getMd5(getMd5(path, UTF8) + paramString, UTF8);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return sign;
	}

	public static String getMd5(String value, String charset) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(value.getBytes(charset));
			return toHexString(md5.digest());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return "";
	}

	private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	public static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
			sb.append(HEX_DIGITS[b[i] & 0x0f]);
		}
		return sb.toString();
	}

	public static void main(String[] s) throws UnsupportedEncodingException {
		// http://api.lanbalanma.com/rest/user/getUserIntegral?model=SHV-E250S&accesstoken=0775B1B793A047163953051522FA71B0&density=320&umeng_device_token=As0Fc0ktO2bzDhfKGNykGJ-H9QqrGT04WREjUi4fIW-X&locale=cn&cpu=exynos4&userid=10015&deviceid=353930051056456&sign=d63b6b46b3f459073d702ff95752fb63&lblmversion=1.0.0&source=lanbalanma&sysversion=19&brand=samsung&openid=0B70B215365BF6F279AAF23292E33AA3&device_width=720&device_height=1280
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lblmversion", "1.0.0");
		params.put("brand", "samsung");
		params.put("time", "2013-12-22 18:35:50");
		params.put("accesstoken", "0775B1B793A047163953051522FA71B0");
		params.put("pagenum", "1");
		params.put("locale", "cn");
		params.put("source", "lanbalanma");
		params.put("device_width", "720");
		params.put("pagecount", "30");
		params.put("openid", "0B70B215365BF6F279AAF23292E33AA3");
		params.put("userid", "10015");
		params.put("density", "320");
		params.put("model", "SM-N910C");
		params.put("cpu", "exynos4");

		params.put("umeng_device_token",
				"Ailq5KNAvMlji2aGhIQ_zOc5yn4VlgiIFzFxg_DkrHaq");
		params.put("device_height", "1280");
		params.put("deviceid", "4e0cc446-1f70-4411-bcc3-edfd5f58c206");
		params.put("sysversion", "19");
		// params.put("sign", "41fb21dc01d90ba8c1e00acf402a3427");
		String sign = GeneralKey
				.sign("http://api.lanbalanma.com/rest/hp/experience", params);
		System.out.println(sign);

		System.out.println(sign.equals("031c7b19c4418d0375cf50ee6b77be25"));
	}
}
