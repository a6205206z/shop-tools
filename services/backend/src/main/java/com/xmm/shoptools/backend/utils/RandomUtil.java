/*
	Copyright (c) 2014, Felix
	All rights reserved.
 */
package com.xmm.shoptools.backend.utils;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

/**
 * @corporation 星密码
 * @author bojue
 * @date 2016年7月4日 下午1:54:12
 * @description  随机数工具类
 */
public class RandomUtil {
	
	/**
	  * @Fields random : 随机器
	  */
	private static final Random random = new SecureRandom();
	
	/**
	  * 随机生成指定位数的数值串
	  * @param digits
	  * @return 
	  * @return String
	 */
	public static String getRandomNum(int digits){
		StringBuffer randomStr = new StringBuffer();
		for (int i = 0; i < digits; i++) {  
	        randomStr.append((int)Math.floor(Math.random() * 10));
	    }  
		return randomStr.toString();
	}
	
	/**
	  * 随机生成指定位数的字母串
	  * @param length
	  * @return 
	  * @return String
	  * @throws
	  */
	public static String getRandomtWord(Integer length) {
		char[] possiblesChars = "aabbccddeeffgghhiijjkkllmmnnooppqqrrssttuuvvwwxxyyzz".toCharArray();
        StringBuffer word = new StringBuffer(length.intValue());
        for (int i = 0; i < length.intValue(); i++) {
            word.append(possiblesChars[random.nextInt(possiblesChars.length)]);
        }
        return word.toString();
    }
	
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		uuid=uuid.replace("-", "");
		return uuid;
	}
    
}
