package com.xingmima.dpfx.rest.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xingmima.dpfx.rest.dao.RItemDao;
import com.xingmima.dpfx.rest.dao.RShopDao;
import com.xingmima.dpfx.rest.dto.QueryShopDTO;
import com.xingmima.dpfx.rest.entity.RShop;
import com.xingmima.dpfx.rest.util.Helper;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version RShopService, v 0.1
 * @date 2016 /9/14 12:53
 */
@Service
public class RShopService {
    private static Logger log = LoggerFactory.getLogger(RShopService.class);

    @Autowired
    private RShopDao rsdao;
    @Autowired
    private RItemDao ridao;

    /**
     * Gets r shop by shop.
     *
     * @param shopid the shopid
     * @param date   the date
     * @return the r shop by shop
     */
//@Cacheable(value = "shop:bydate", key = "#root.targetClass+#root.methodName+#root.args")
    @Cacheable(value = "shop:bydate")
    public RShop getRShopByShop(Long shopid, Integer date) {
        return rsdao.getRShopByShop(shopid, date);
    }

    /**
     * 查询店铺时间段统计信息
     *
     * @param shopid the shopid
     * @param type   the type
     * @return shop diff info
     */
    public QueryShopDTO getShopDiffInfo(Long shopid, String type) {
        /*默认昨天*/
        if (StringUtils.isEmpty(type)) {
            type = "0";
        }
        Integer[] date = this.getDateArray(type);

        QueryShopDTO dto = new QueryShopDTO();

        List<HashMap<String, Object>> pv = this.getShopPvDiff(shopid, date);
//        for (HashMap map : pv) {
//            System.out.println(map.get("k") + " " + map.get("pv"));
//        }

        List<HashMap<String, Object>> sales = this.getShopSalesDiff(shopid, date);
//        for (HashMap map : sales) {
//            System.out.println(map.get("k") + " " + map.get("sold_total_count") + " " + map.get("total_sales"));
//        }

        List<HashMap<String, Object>> goods = this.getShopGoodsDiff(shopid, date);
//        for (HashMap map : goods) {
//            System.out.println(map.get("k") + " " + map.get("sale_goods_num") + " " + map.get("favorite_num"));
//        }

        List<HashMap<String, Object>> hotgoods = this.getShopHotSales(shopid, date);
        if (null != hotgoods && !hotgoods.isEmpty()) {
            dto.setHotgoods(hotgoods);
        }

        List<HashMap<String, Object>> hotfav = this.getShopHotFavorite(shopid, date);
        if (null != hotfav && !hotfav.isEmpty()) {
            dto.setHotfav(hotfav);
        }
        return dto;
    }

    /**
     * 查询店铺PV
     *
     * @param shopid the shopid
     * @param date   the date
     * @return the shop pv diff
     */
    @Cacheable(value = "shop:pv")
    public List<HashMap<String, Object>> getShopPvDiff(Long shopid, Integer[] date) {
        return rsdao.getShopPvDiff(shopid, date[1], date[0], date[3], date[2]);
    }

    /**
     * 查询店铺销量统计
     *
     * @param shopid the shopid
     * @param date   the date
     * @return the shop sales diff
     */
    @Cacheable(value = "shop:sales")
    public List<HashMap<String, Object>> getShopSalesDiff(Long shopid, Integer[] date) {
        return ridao.getShopSalesDiff(shopid, date[1], date[0], date[3], date[2]);
    }

    /**
     * 查询宝贝数量差值
     *
     * @param shopid the shopid
     * @param date   the date
     * @return the shop goods diff
     */
    @Cacheable(value = "shop:goods")
    public List<HashMap<String, Object>> getShopGoodsDiff(Long shopid, Integer[] date) {
        return rsdao.getShopGoodsDiff(shopid, date[1], date[3]);
    }

    /**
     * 查询时间段内的热销宝贝
     *
     * @param shopid the shopid
     * @param date   the date
     * @return the shop goods diff
     */
    @Cacheable(value = "shop:hostsales")
    public List<HashMap<String, Object>> getShopHotSales(Long shopid, Integer[] date) {
        return ridao.getShopHotSales(shopid, date[1], date[0]);
    }

    /**
     * 查询时间段内的人气宝贝
     *
     * @param shopid the shopid
     * @param date   the date
     * @return the shop goods diff
     */
    @Cacheable(value = "shop:hostfav")
    public List<HashMap<String, Object>> getShopHotFavorite(Long shopid, Integer[] date) {
        return ridao.getShopHotFavorite(shopid, date[1], date[0]);
    }

    /**
     * 按查询类型生成日期
     *
     * @param type the type
     * @return the integer [ ]
     */
    public Integer[] getDateArray(String type) {
        Integer[] date = new Integer[]{0, 0, 0, 0};
        if ("1".equals(type)) { /*近7天对比*/
            //今天
            date[0] = Helper.getTodayAsSecondInt(0);
            //前7天
            date[1] = Helper.getTodayAsSecondInt(-7);
            date[2] = date[1];
            //上个前7天
            date[3] = Helper.getTodayAsSecondInt(-14);
        } else if ("2".equals(type)) { /*近30天对比*/
            //今天
            date[0] = Helper.getTodayAsSecondInt(0);
            //前30天
            date[1] = Helper.getTodayAsSecondInt(-30);
            date[2] = date[1];
            //上个前30天
            date[3] = Helper.getTodayAsSecondInt(-60);
        } else { /*昨天日期对比*/
            //今天
            date[0] = Helper.getTodayAsSecondInt(0);
            //昨天
            date[1] = Helper.getTodayAsSecondInt(-1);
            date[2] = date[1];
            //前天
            date[3] = Helper.getTodayAsSecondInt(-2);
        }
        return date;
    }


    /**
     * 获取店铺体检7日报表.
     *
     * @param shopid the shopid
     * @return the hash map
     */
    public HashMap<String,HashMap<String,Object>> getShopReportSevenDay(long shopid){
        HashMap<String,HashMap<String,Object>> report = null;
        HashMap<String,Object> today = ridao.getShopReport(0,shopid);
        HashMap<String,Object> sevenAgo = ridao.getShopReport(7,shopid);
        if(today != null){
            report = new HashMap<String,HashMap<String,Object>>();
            report.put("today",today);
            if(sevenAgo != null){
                report.put("ago",sevenAgo);
            }
        }
        return report;
    }

    /**
     * 测试
     *
     * @param type
     */
    private void testGetDateArray(String type) {
        Integer[] d = this.getDateArray(type);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < d.length; i++) {
            System.out.println(d[i] + " f== " + df.format(new Date(new Long(d[i].toString() + "000"))));
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new RShopService().testGetDateArray("1");
    }
}
