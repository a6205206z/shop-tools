package com.xmm.shoptools.backend.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类。
 * 
 * @author
 */
public abstract class StringUtils {

	private StringUtils() {
	}

	/**
	 * 检查指定的字符串是否为空。
	 * <ul>
	 * <li>SysUtils.isEmpty(null) = true</li>
	 * <li>SysUtils.isEmpty("") = true</li>
	 * <li>SysUtils.isEmpty("   ") = true</li>
	 * <li>SysUtils.isEmpty("abc") = false</li>
	 * </ul>
	 * 
	 * @param value
	 *            待检查的字符串
	 * @return true/false
	 */
	public static boolean isEmpty(String value) {
		int strLen;
		if (value == null || (strLen = value.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(value.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查对象是否为数字型字符串,包含负数开头的。
	 */
	public static boolean isNumeric(Object obj) {
		if (obj == null) {
			return false;
		}
		char[] chars = obj.toString().toCharArray();
		int length = chars.length;
		if (length < 1)
			return false;

		int i = 0;
		if (length > 1 && chars[0] == '-')
			i = 1;

		for (; i < length; i++) {
			if (!Character.isDigit(chars[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查指定的字符串列表是否不为空。
	 */
	public static boolean areNotEmpty(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			result = false;
		} else {
			for (String value : values) {
				result &= !isEmpty(value);
			}
		}
		return result;
	}

	/**
	 * 把通用字符编码的字符串转化为汉字编码。
	 */
	public static String unicodeToChinese(String unicode) {
		StringBuilder out = new StringBuilder();
		if (!isEmpty(unicode)) {
			for (int i = 0; i < unicode.length(); i++) {
				out.append(unicode.charAt(i));
			}
		}
		return out.toString();
	}

	public static String toUnderlineStyle(String name) {
		StringBuilder newName = new StringBuilder();
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (Character.isUpperCase(c)) {
				if (i > 0) {
					newName.append("_");
				}
				newName.append(Character.toLowerCase(c));
			} else {
				newName.append(c);
			}
		}
		return newName.toString();
	}

	public static String convertString(byte[] data, int offset, int length) {
		if (data == null) {
			return null;
		} else {
			try {
				return new String(data, offset, length, "UTF-8");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static byte[] convertBytes(String data) {
		if (data == null) {
			return null;
		} else {
			try {
				return data.getBytes("UTF-8");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 字符串替换(src=源,oldstr=要替换的内容,newstr为要替换为的内容)
	 * 
	 * @param src
	 * @param oldstr
	 * @param newstr
	 * @return
	 */
	public static String replace(String src, String oldstr, String newstr) {
		StringBuffer dest = new StringBuffer();
		int beginIndex = 0;
		int endIndex = 0;
		while (true) {
			endIndex = src.indexOf(oldstr, beginIndex);
			if (endIndex >= 0) {
				dest.append(src.substring(beginIndex, endIndex));
				dest.append(newstr);
				beginIndex = endIndex + oldstr.length();
			} else {
				dest.append(src.substring(beginIndex));
				break;
			}
		}
		return dest.toString();
	}

	/**
	 * 替换特殊字符(左右尖括号,单斜线,双斜线,单引号)
	 * 
	 * @param str
	 * @return
	 */
	public static String ftStr(String str) {
		str = replace(str, "\\", "&#92;");
		str = replace(str, "'", "&#39;");
		str = replace(str, "\"", "&quot;");
		str = replace(str, "<", "&lt;");
		str = replace(str, ">", "&gt;");
		str = replace(str, "<<", "&raquo;");
		str = replace(str, ">>", "&laquo;");
		str = replace(str, "'", "");
		str = replace(str, "\"", "");
		return str;
	}

	public static boolean isNumber(String str) {
		java.util.regex.Pattern pattern = java.util.regex.Pattern
				.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
		java.util.regex.Matcher match = pattern.matcher(str);
		if (match.matches() == false) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 检测是否有emoji字符
	 * 
	 * @param source
	 * @return 一旦含有就抛出
	 */
	public static boolean containsEmoji(String source) {
		if (StringUtils.isEmpty(source)) {
			return false;
		}
		int len = source.length();
		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);
			if (isEmojiCharacter(codePoint)) {
				// do nothing，判断到了这里表明，确认有表情字符
				return true;
			}
		}
		return false;
	}

	private static boolean isEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
				|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
				|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}

	/**
	 * 过滤emoji 或者 其他非文字类型的字符
	 * 
	 * @param source
	 * @return
	 */
	public static String filterEmoji(String source) {
		if (!containsEmoji(source)) {
			return source;// 如果不包含，直接返回
		}
		// 到这里铁定包含
		StringBuilder buf = null;
		int len = source.length();
		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);

			if (isEmojiCharacter(codePoint)) {
				if (buf == null) {
					buf = new StringBuilder(source.length());
				}

				buf.append(codePoint);
			} else {
			}
		}
		if (buf == null) {
			return source;// 如果没有找到 emoji表情，则返回源字符串
		} else {
			if (buf.length() == len) {// 这里的意义在于尽可能少的toString，因为会重新生成字符串
				buf = null;
				return source;
			} else {
				return buf.toString();
			}
		}
	}

	public static String getCnPropertiesName(String propertiesName) {
		if (StringUtils.isEmpty(propertiesName)) {
			return "";
		}
		String[] pns = propertiesName.split(";");
		if (pns != null && pns.length > 0) {
			StringBuffer str = new StringBuffer();
			for (int i = 0; i < pns.length; i++) {
				String[] p = pns[i].split(":");
				if (p.length >= 3) {
					str.append(replacePropFlag(p[2])).append(":").append(replacePropFlag(p[3]));
				} else {
					str.append(p[0]).append(":").append(p[1]);
				}
				if (i < pns.length - 1) {
					str.append(";");
				}
			}
			return str.toString();
		}
		return "";
	}

	public static String replacePropFlag(String str) {
		if (isEmpty(str)) {
			return "";
		}
		return str.replaceAll("#cln#", ":").replaceAll("#scln#", ";");
	}

	public static String convertEmail(String str) {
		if (StringUtils.isEmpty(str))
			return "";
		if (str.indexOf("@") > 0) {
			return convertStar(str.split("@")[0], 3, 3) + "@" + str.split("@")[1];
		}
		return "";
	}

	public static String convertStar(String str, int frontLen, int endLen) {
		if (StringUtils.isEmpty(str))
			return "";
		if (str.length() <= frontLen || str.length() <= endLen) {
			return str + "*";
		}
		int len = str.length() - frontLen - endLen;
		String xing = "";
		for (int i = 0; i < len; i++) {
			xing += '*';
			if (i == 5)
				break;
		}
		if (len == 0) {
			return str.substring(0, frontLen) + "*";
		} else
			return str.substring(0, frontLen) + xing + str.substring(str.length() - endLen);
	}

	public static String findText(String text, String regex) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(text);
		// 部分匹配，从当前位置开始匹配，找到一个匹配的子串，将移动下次匹配的位置。
		if (m.find()) {
			return m.group(0);
		}
		return null;
	}

	/**
	 * 运营商
	 * 
	 * @return dx:电信 lt:联通 yd:移动
	 */
	public static String checkMobikle(String mobile) {

		String dx = "^[1](3[3])|(8[019])\\d{8}$";
		Pattern p = Pattern.compile(dx);
		Matcher m = p.matcher(mobile);
		if (m.find())
			return "dx";

		String lt = "^[1](3[0-2]|5[56]|8[56]|4[5]|7[6])\\d{8}$";
		p = Pattern.compile(lt);
		m = p.matcher(mobile);
		if (m.find())
			return "lt";

		String yd = "^[1](3[4-9]|5[012789]|8[23478]|4[7]|7[8])\\d{8}$";
		p = Pattern.compile(yd);
		m = p.matcher(mobile);
		if (m.find())
			return "yd";

		return "";
	}

	public static String getRandomString(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static void main(String[] s) {
		System.out.println(findText("100", "\\d+"));
		System.out.println(convertStar("13803228941", 3, 4));
	}
}
