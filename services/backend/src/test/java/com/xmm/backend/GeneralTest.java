package com.xmm.backend;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xmm.shoptools.backend.admin.web.init.InitConfig;
import com.xmm.shoptools.backend.service.TjobService;
import com.xmm.shoptools.backend.service.TspiderService;
import com.xmm.shoptools.backend.vo.PageResult;
import com.xmm.shoptools.backend.vo.TjobQuery;
import com.xmm.shoptools.backend.vo.TjobVO;

/**
 * @author leidian
 * @version $Id: GeneralTest.java, v 0.1 2016年9月18日 下午1:17:53 leidian Exp $
 */
public class GeneralTest extends BaseJunit4Test {
    @Autowired
    TspiderService tspiderService;
    
    @Autowired
    private TjobService tjobService;
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
    
    @Test
    public void testName4() throws Exception {
//        TjobQuery query = new TjobQuery();
//        query.setRows(Integer.MAX_VALUE);
//        PageResult<TjobVO> pr = tjobService.query(query);
//        List<TjobVO> rows = pr.getRows();
////        String spider_name =  InitConfig.SPIDER_NAME;
//        String[] splits = new String[]{"TaobaoShopSpider,TaobaoShopProductSpider,TmallShopSpider,TmallShopProductSpider"};
//        for (TjobVO tjobVO : rows) {
//            String sn = tjobVO.getSpiderName().replaceAll("\'", "");
//            System.out.println(sn);
//            for (String s : splits) {
//                if(s.equals(sn)){
//                    rows.remove(tjobVO);
//                }
//            }
//        }
    }
    
}

