package com.xingmima.dpfx.rest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.security.SecureRandom;
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

    private static final Logger logger = LoggerFactory.getLogger(Helper.class);

    public static String getTodayAsSecond() {
        return getTodayAsSecond(0);
    }

    public static Integer getTodayAsSecondInt(int d) {
        return Integer.parseInt(getTodayAsSecond(d));
    }

    /**
     * Gets today as second.
     *
     * @return the today as second
     */
    public static String getTodayAsSecond(int d) {
        String result = "0";
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, d);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        result = Long.toString(calendar.getTimeInMillis() / 1000);
        return result;
    }

    private static int getInt(byte bytes[]) {
        return (bytes[0] & 0xff) << 24 | (bytes[1] & 0xff) << 16 | (bytes[2] & 0xff) << 8
                | bytes[1] & 0xff;
    }

    private static String hexFormat(int value, int dignum) {
        char chars[] = new char[dignum];
        for (int i = 1; i <= dignum; i++) {
            int curValue = value >> 4;
            int quotient = Math.abs(value - (curValue << 4));
            value = curValue;
            chars[i - 1] = Character.forDigit(quotient, 16);
        }

        return new String(chars);
    }

    /**
     * 获取36字符串
     *
     * @return
     */
    public static String getGuid() {
        StringBuffer tmpBuffer = new StringBuffer();
        SecureRandom seeder = new SecureRandom();
        try {
            InetAddress inet = InetAddress.getLocalHost();
            byte bytes[] = inet.getAddress();
            String hexInetAddress = hexFormat(getInt(bytes), 8);
            String thisHashCode = hexFormat(new Object().hashCode(), 8);
            tmpBuffer.append("-");
            tmpBuffer.append(hexInetAddress.substring(0, 4));
            tmpBuffer.append("-");
            tmpBuffer.append(hexInetAddress.substring(4));
            tmpBuffer.append("-");
            tmpBuffer.append(thisHashCode.substring(0, 4));
            tmpBuffer.append("-");
            tmpBuffer.append(thisHashCode.substring(4));
            String midValue = tmpBuffer.toString();
            long timeNow = System.currentTimeMillis();
            int timeLow = (int) timeNow & -1;
            int node = seeder.nextInt();
            return hexFormat(timeLow, 8) + midValue + hexFormat(node, 8);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获取32UUID
     *
     * @return
     */
    public static String getGuid32() {
        StringBuffer tmpBuffer = new StringBuffer();
        SecureRandom seeder = new SecureRandom();
        try {
            InetAddress inet = InetAddress.getLocalHost();
            byte bytes[] = inet.getAddress();
            String hexInetAddress = hexFormat(getInt(bytes), 8);
            String thisHashCode = hexFormat(new Object().hashCode(), 8);
            tmpBuffer.append(hexInetAddress.substring(0, 4));
            tmpBuffer.append(hexInetAddress.substring(4));
            tmpBuffer.append(thisHashCode.substring(0, 4));
            tmpBuffer.append(thisHashCode.substring(4));
            String midValue = tmpBuffer.toString();
            long timeNow = System.currentTimeMillis();
            int timeLow = (int) timeNow & -1;
            int node = seeder.nextInt();
            return hexFormat(timeLow, 8) + midValue + hexFormat(node, 8);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
