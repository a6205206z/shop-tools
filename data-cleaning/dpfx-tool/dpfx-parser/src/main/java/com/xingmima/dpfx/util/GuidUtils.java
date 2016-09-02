package com.xingmima.dpfx.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.security.SecureRandom;

/**
 * 
 * <p>Title:UUID生成工具类</p>
 * <p>Description:</p>
 *
 * @author Freeman
 * @version 1.0, 2009-3-19
 */
public class GuidUtils {
    private static final Logger logger = LoggerFactory.getLogger(GuidUtils.class);

    public GuidUtils() {
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
