package com.xingmima.dpfx.util;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version Tool, v 0.1
 * @date 2016/9/1 14:19
 */
public class Tool {
    /**
     * 判断串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("[0-9]*");
        java.util.regex.Matcher match = pattern.matcher(str);
        if (match.matches() == false) {
            return false;
        } else {
            return true;
        }
    }
}
