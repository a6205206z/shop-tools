package com.xmm.shoptools.stats;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Xingmima.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 *
 * @author cean
 * @version com.xmm.shoptools.stats, v 0.1
 * @date 16 /9/8.
 */
public class Helper {
    /**
     * Gets today as second.
     *
     * @return the today as second
     */
    public static String getTodayAsSecond() {
        String result = "0";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = df.parse(df.format(calendar.getTime()));
            calendar.setTime(date);
            result = Long.toString(calendar.getTimeInMillis() / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
