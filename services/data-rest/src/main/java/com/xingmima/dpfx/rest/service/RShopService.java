package com.xingmima.dpfx.rest.service;

import com.alibaba.fastjson.JSON;
import com.xingmima.dpfx.rest.dao.RItemDao;
import com.xingmima.dpfx.rest.dao.RShopDao;
import com.xingmima.dpfx.rest.dao.TShopDao;
import com.xingmima.dpfx.rest.dto.JyDetailDTO;
import com.xingmima.dpfx.rest.dto.QueryShopDTO;
import com.xingmima.dpfx.rest.util.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


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
    @Autowired
    private TShopDao tsdao;

    //@Cacheable(value = "shop:bydate", key = "#root.targetClass+#root.methodName+#root.args")

    /**
     * 查询店铺时间段统计信息
     *
     * @param shopid the shopid
     * @param type   the type
     * @return shop diff info
     */
    @Cacheable(value = "shop:result")
    public QueryShopDTO getShopDiffInfo(Long shopid, String type) {
        QueryShopDTO dto = new QueryShopDTO();
        if (null == shopid) {
            return dto;
        }

        /*默认昨天*/
        if (StringUtils.isEmpty(type)) {
            type = "0";
        }
        try {
            //店铺基础信息
            dto.setInfo(this.getRShopInfo(shopid));

            Integer[] date = this.getDateArray(type);

            List<HashMap<String, Object>> list = this.getShopPvDiff(shopid, date);
            if (null != list && !list.isEmpty()) {
                JyDetailDTO tmp = this.getBigDecimalJyDetailDTO(list, "pv");
                dto.setShopPv(tmp);
                log.debug("setShopPv:" + JSON.toJSONString(tmp));
            }

            list = this.getShopSalesDiff(shopid, date);
            if (null != list && !list.isEmpty()) {
                JyDetailDTO tmp = this.getIntegerJyDetailDTO(list, "sold_total_count");
                dto.setSalesNum(tmp);
                log.debug("setSalesNum:" + JSON.toJSONString(tmp));

                tmp = this.getBigDecimalJyDetailDTO(list, "total_sales");
                dto.setSalesMoney(tmp);
                log.debug("setSalesMoney:" + JSON.toJSONString(tmp));
            }

            list = this.getShopGoodsDiff(shopid, date);
            if (null != list && !list.isEmpty()) {
                JyDetailDTO tmp = this.getIntegerJyDetailDTO(list, "sale_goods_num");
                dto.setGoodsNum(tmp);
                log.debug("setGoodsNum:" + JSON.toJSONString(tmp));

                tmp = this.getIntegerJyDetailDTO(list, "favorite_num");
                dto.setShopFav(tmp);
                log.debug("setShopFav:" + JSON.toJSONString(tmp));
            }

            List<HashMap<String, Object>> hotgoods = null;
            List<HashMap<String, Object>> hotfav = null;

            if ("1".equals(type)) {
                //加载时间段数据
                hotgoods = this.getShopHotSales(shopid, date);
                hotfav = this.getShopHotFavorite(shopid, date);
            } else {
                //加载整店数据
                hotgoods = this.getShopHotSales(shopid);
                hotfav = this.getShopHotFavorite(shopid);
            }

            if (null != hotgoods && !hotgoods.isEmpty()) {
                log.debug("setHotgoods:" + JSON.toJSONString(hotgoods));
                dto.setHotgoods(hotgoods);
            }

            if (null != hotfav && !hotfav.isEmpty()) {
                log.debug("setHotfav:" + JSON.toJSONString(hotfav));
                dto.setHotfav(hotfav);
            }
        } catch (Exception e) {
            log.error("处理失败:", e);
        }
        return dto;
    }

    /**
     * Integer
     *
     * @param list
     * @param key
     * @return
     */
    private JyDetailDTO getIntegerJyDetailDTO(List<HashMap<String, Object>> list, String key) {
        JyDetailDTO tmp = null;
        Integer d1 = null, d2 = null;
        for (HashMap<String, Object> map : list) {
            if (map.get("k").equals("d1")) {
                d1 = (Integer) map.get(key);
            } else if (map.get("k").equals("d2")) {
                d2 = (Integer) map.get(key);
            }
        }
        if (d1 != null) {
            tmp = new JyDetailDTO();
            tmp.setVal(d1.toString());
            if (d2 == null) {
                tmp.setFlag("normal");
                tmp.setDiff("0");
            } else {
                tmp.setFlag(this.compareTo(new BigDecimal(d1), new BigDecimal(d2)));
                tmp.setDiff((d1 - d2) + "");
            }
        }
        return tmp;
    }

    /**
     * BigDecimal
     *
     * @param list
     * @param key
     * @return
     */
    private JyDetailDTO getBigDecimalJyDetailDTO(List<HashMap<String, Object>> list, String key) {
        JyDetailDTO tmp = null;

        BigDecimal d1 = null, d2 = null;
        for (HashMap<String, Object> map : list) {
            if (map.get("k").equals("d1")) {
                d1 = (BigDecimal) map.get(key);
            } else if (map.get("k").equals("d2")) {
                d2 = (BigDecimal) map.get(key);
            }
        }
        if (d1 != null) {
            tmp = new JyDetailDTO();
            tmp.setVal(d1.toString());
            if (d2 == null) {
                tmp.setFlag("normal");
                tmp.setDiff("0");
            } else {
                tmp.setFlag(this.compareTo(d1, d2));
                tmp.setDiff(d1.subtract(d2).toString());
            }
        }
        return tmp;
    }

    private String compareTo(BigDecimal d1, BigDecimal d2) {
        if (d1.compareTo(d2) > 0) {
            return "over";
        } else if (d1.compareTo(d2) == 0) {
            return "normal";
        }
        return "lower";
    }

    /**
     * 查询店铺基础信息
     *
     * @param shopid the shopid
     * @return the shop pv diff
     */
//    @Cacheable(value = "shop:info")
    public HashMap<String, Object> getRShopInfo(Long shopid) {
        //店铺基础信息
        HashMap<String, Object> info = tsdao.getRShopInfo(shopid);
        if (null != info) {
            HashMap<String, Object> di = tsdao.getDShopInfo(shopid);
            if (null != di) {
                info.putAll(di);
            }
            log.debug("setInfo:" + JSON.toJSONString(info));
        }
        return info;
    }

    /**
     * 查询店铺PV
     *
     * @param shopid the shopid
     * @param date   the date
     * @return the shop pv diff
     */
//    @Cacheable(value = "shop:pv")
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
//    @Cacheable(value = "shop:sales")
    public List<HashMap<String, Object>> getShopSalesDiff(Long shopid, Integer[] date) {
        return ridao.getShopSalesDiff(shopid, Helper.getTodayAsSecondInt(-1), date[3]);
    }

    /**
     * 查询宝贝数量差值
     *
     * @param shopid the shopid
     * @param date   the date
     * @return the shop goods diff
     */
//    @Cacheable(value = "shop:goods")
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
//    @Cacheable(value = "shop:date:hostsales")
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
//    @Cacheable(value = "shop:date:hostfav")
    public List<HashMap<String, Object>> getShopHotFavorite(Long shopid, Integer[] date) {
        return ridao.getShopHotFavorite(shopid, date[1], date[0]);
    }

    /**
     * 查询全店的热销宝贝
     *
     * @param shopid the shopid
     * @return the shop goods diff
     */
//    @Cacheable(value = "shop:all:hostsales")
    public List<HashMap<String, Object>> getShopHotSales(Long shopid) {
        return ridao.getShopAllHotSales(shopid);
    }

    /**
     * 查询全店的人气宝贝
     *
     * @param shopid the shopid
     * @return the shop goods diff
     */
//    @Cacheable(value = "shop:all:hostfav")
    public List<HashMap<String, Object>> getShopHotFavorite(Long shopid) {
        return ridao.getShopAllHotFavorite(shopid);
    }

    /**
     * 按查询类型生成日期
     *
     * @param type the type
     * @return the integer [ ]
     */
    public Integer[] getDateArray(String type) {
        Integer[] date = new Integer[]{0, 0, 0, 0};
//        if ("1".equals(type)) { /*近7天对比*/
//            //今天
//            date[0] = Helper.getTodayAsSecondInt(0);
//            //前7天
//            date[1] = Helper.getTodayAsSecondInt(-7);
//            date[2] = date[1];
//            //上个前7天
//            date[3] = Helper.getTodayAsSecondInt(-14);
//        } else
//
        if ("1".equals(type)) {
            /*近30天对比*/
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
        testGetDateArray(date);
        return date;
    }


    /**
     * 获取店铺体检7日报表.
     *
     * @param shopid the shopid
     * @return the hash map
     */
    public HashMap<String, HashMap<String, Object>> getShopReportSevenDay(long shopid) {
        HashMap<String, HashMap<String, Object>> report = null;
        HashMap<String, Object> today = ridao.getShopReport(0, shopid);
        HashMap<String, Object> sevenAgo = ridao.getShopReport(7, shopid);
        if (today != null) {
            report = new HashMap<String, HashMap<String, Object>>();
            report.put("today", today);
            if (sevenAgo != null) {
                report.put("ago", sevenAgo);
            }
        }
        return report;
    }

    /**
     * 测试
     *
     * @param d
     */
    private void testGetDateArray(Integer[] d) {
//        Integer[] d = this.getDateArray(type);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < d.length; i++) {
            log.debug(d[i] + " f== " + df.format(new Date(new Long(d[i].toString() + "000"))));
        }
    }
}
