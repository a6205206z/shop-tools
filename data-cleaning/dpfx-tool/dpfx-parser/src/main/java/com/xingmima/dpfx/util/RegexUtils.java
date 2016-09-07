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

    /**
     * 分解详情页面中li参数 如 品牌：xxx
     *
     * @param text
     * @return
     */
    public static String[] valueParam(String text) {
        return Pattern.compile("[：:]").split(text, 2);
    }

    /**
     * 将数字转为汉字
     *
     * @param source
     * @return
     */
    public static String charDecoder(String source) {
        String[] data = source.replaceAll("\\&nbsp", "").split(";");
        if (data.length > 1) {
            StringBuffer s = new StringBuffer();
            for (int i = 0; i < data.length; i++) {
                String d = data[i];
                if (StringUtils.isEmpty(d))
                    continue;

                int e = d.indexOf("&#");
                if (e < 0) {
                    s.append(d);
                    continue;
                }

                s.append(d.substring(0, e));

                String v = d.substring(e + 2, d.length());
                if (StringUtils.isNumeric(v))
                    s.append((char) Integer.parseInt(v));
            }
            return s.toString();
        }
        return data[0];
    }


    public static void main(String[] args) {
        System.out.println(findText("bnow    skuComponentFirst: 'true',   rcid             : '50010404',  cid              : '50009047',         : true,chong: false,dbst           ccc   :         1471654322000,   stepdata         : {},xjcc: false,                type             : 'ifashion',",
                "[\\r\\s\\n]cid.*?(?=,)")); //dbst.*(?=,)


        System.out.println(findText("jsonp100({\"count\":756})", ":.*?(?=})"));


        System.out.println(findText("//tbskip.taobao.com/json/show_bid_count.htm?itemNumId=534248818107&old_quantity=1203&date=1472694923000\"","date=.*?(?=\")"));
        System.out.println(findText("jsonp198({\"dsr\":{\"gradeAvg\":5.50,\"itemId\":0,\"peopleNum\":0,\"periodSoldQuantity\":0,\"rateTotal\":68,\"sellerId\":0,\"spuId\":0,\"totalSoldQuantity\":0}})",
                "rateTotal\":.*?(?=,)"));
    }
}
