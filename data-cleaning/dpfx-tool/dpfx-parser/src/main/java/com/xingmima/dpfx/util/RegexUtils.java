package com.xingmima.dpfx.util;

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
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        // 部分匹配，从当前位置开始匹配，找到一个匹配的子串，将移动下次匹配的位置。
        if (m.find()) {
            return m.group(0);
        }
        return null;
    }

    /**
     * Gets numbers.
     *
     * @param text the text
     * @return the numbers
     */
    public static String getNumbers(String text) {
        return findText(text, "\\d+.\\d+");
    }
}
