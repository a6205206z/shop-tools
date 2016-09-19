package com.xingmima.dpfx.rest.service;

import com.alibaba.fastjson.JSON;
import com.xingmima.dpfx.rest.dao.RItemDao;
import com.xingmima.dpfx.rest.dao.RShopDao;
import com.xingmima.dpfx.rest.dto.JyDetailDTO;
import com.xingmima.dpfx.rest.dto.QueryShopDTO;
import com.xingmima.dpfx.rest.entity.RShop;
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
 * @date 2016/9/14 12:53
 */
@Service
public class RShopService {
    private static Logger log = LoggerFactory.getLogger(RShopService.class);

    @Autowired
    private RShopDao rsdao;
    @Autowired
    private RItemDao ridao;

    //@Cacheable(value = "shop:bydate", key = "#root.targetClass+#root.methodName+#root.args")
    @Cacheable(value = "shop:bydate")
    public RShop getRShopByShop(Long shopid, Integer date) {
        return rsdao.getRShopByShop(shopid, date);
    }

    /**
     * 查询店铺时间段统计信息
     *
     * @param shopid
     * @param type
     * @return
     */
    public QueryShopDTO getShopDiffInfo(Long shopid, String type) {
        /*默认昨天*/
        if (StringUtils.isEmpty(type)) {
            type = "0";
        }
        Integer[] date = this.getDateArray(type);

        QueryShopDTO dto = new QueryShopDTO();

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

        list = this.getShopHotSales(shopid, date);
        if (null != list && !list.isEmpty()) {
            log.debug("setHotgoods:" + JSON.toJSONString(list));
            dto.setHotgoods(list);
        }

        list = this.getShopHotFavorite(shopid, date);
        if (null != list && !list.isEmpty()) {
            log.debug("setHotfav:" + JSON.toJSONString(list));
            dto.setHotfav(list);
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
        JyDetailDTO tmp = new JyDetailDTO();

        Integer d1 = null, d2 = null;
        for (HashMap<String, Object> map : list) {
            if (map.get("k").equals("d1")) {
                d1 = (Integer) map.get(key);
            } else if (map.get("k").equals("d2")) {
                d2 = (Integer) map.get(key);
            }
        }
        if (d1 != null) {
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
        JyDetailDTO tmp = new JyDetailDTO();

        BigDecimal d1 = null, d2 = null;
        for (HashMap<String, Object> map : list) {
            if (map.get("k").equals("d1")) {
                d1 = (BigDecimal) map.get(key);
            } else if (map.get("k").equals("d2")) {
                d2 = (BigDecimal) map.get(key);
            }
        }
        if (d1 != null) {
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
     * 查询店铺PV
     *
     * @param shopid the shopid
     * @param date   the date
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
     */
    @Cacheable(value = "shop:sales")
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
//        if ("1".equals(type)) { /*近7天对比*/
//            //今天
//            date[0] = Helper.getTodayAsSecondInt(0);
//            //前7天
//            date[1] = Helper.getTodayAsSecondInt(-7);
//            date[2] = date[1];
//            //上个前7天
//            date[3] = Helper.getTodayAsSecondInt(-14);
//        } else if ("2".equals(type)) {
            /*近30天对比*/
        //今天
//        date[0] = Helper.getTodayAsSecondInt(0);
//        //前30天
//        date[1] = Helper.getTodayAsSecondInt(-30);
//        date[2] = date[1];
//        //上个前30天
//        date[3] = Helper.getTodayAsSecondInt(-60);
//        } else { /*昨天日期对比*/
//            //今天
        date[0] = Helper.getTodayAsSecondInt(0);
        //昨天
        date[1] = Helper.getTodayAsSecondInt(-1);
        date[2] = date[1];
        //前天
        date[3] = Helper.getTodayAsSecondInt(-2);
//        }

        testGetDateArray(date);
        return date;
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
