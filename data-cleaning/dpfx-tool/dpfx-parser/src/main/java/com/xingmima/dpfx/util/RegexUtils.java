package com.xingmima.dpfx.util;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version RegexUtils, v 0.1
 * @date 2016 /9/1 15:05
 */
public class RegexUtils {

    /**
     * Find text string.
     *
     * @param text  the text
     * @param regex the regex
     * @return the string
     */
    public static String findText(String text, String regex) {
        if (StringUtils.isEmpty(text))
            return null;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        // 部分匹配，从当前位置开始匹配，找到一个匹配的子串，将移动下次匹配的位置。
        if (m.find()) {
            return m.group(0);
        }
        return null;
    }

    public static String getInteger(String text) {
        return findText(text, "\\d+");
    }

    /**
     * Gets numbers.
     *
     * @param text the text
     * @return the numbers
     */
    public static String getDecimal(String text) {
        return findText(text, "\\d+.\\d+");
    }

    public static Long getTaobaoId(String url) {
        return getTaobaoId(url, "id");
    }

    public static Long getTaobaoId(String url, String key) {
        String id = findText(url, key + "=(\\d+)").replace(key + "=", "");
        if (null != id) {
            return Long.parseLong(id);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(findText("bnow    skuComponentFirst: 'true',   rcid             : '50010404',  cid              : '50009047',         : true,chong: false,dbst           ccc   :         1471654322000,   stepdata         : {},xjcc: false,                type             : 'ifashion',",
                "[\\r\\s\\n]cid.*?(?=,)")); //dbst.*(?=,)


        System.out.println(findText("jsonp100({\"count\":756})",":.*?(?=})"));

    }
}
