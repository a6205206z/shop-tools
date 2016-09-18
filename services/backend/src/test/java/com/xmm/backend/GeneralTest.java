package com.xmm.backend;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

/**
 * @author leidian
 * @version $Id: GeneralTest.java, v 0.1 2016年9月18日 下午1:17:53 leidian Exp $
 */
public class GeneralTest extends BaseJunit4Test {
    
    @Test
    public void testName() throws Exception {
//        String result = null;
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar calendar = Calendar.getInstance();
//        try {
//            Date date = df.parse(df.format(calendar.getTime()));
//            calendar.setTime(date);
//            result = Long.toString(calendar.getTimeInMillis() / 1000);
//            System.out.println("result---:"+result);//1474128000
//            Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
//            System.out.println("timestamp:"+timestamp);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }
    
    @Test
    public void testName3() throws Exception {
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.HOUR_OF_DAY, 0);
//        cal.set(Calendar.SECOND, 0);
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.MILLISECOND, 0);
//        long time = cal.getTimeInMillis()/1000;
//        System.out.println("time----"+time);//1474128000
//        Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
//        System.out.println("timestamp:-------"+timestamp);
    }
    
}

