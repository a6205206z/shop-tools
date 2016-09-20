package com.xingmima.dpfx.rest.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version QueryShopDTO, v 0.1
 * @date 2016/9/19 16:52
 */
public class QueryShopDTO extends BaseDTO {
    private HashMap<String, Object> info = null;
    private JyDetailDTO shopPv = null;
    private JyDetailDTO salesNum = null;
    private JyDetailDTO salesMoney = null;
    private JyDetailDTO goodsNum = null;
    private JyDetailDTO shopFav = null;

    private List<HashMap<String, Object>> hotgoods = new ArrayList<HashMap<String, Object>>();
    private List<HashMap<String, Object>> hotfav = new ArrayList<HashMap<String, Object>>();

    public HashMap<String, Object> getInfo() {
        return info;
    }

    public void setInfo(HashMap<String, Object> info) {
        this.info = info;
    }

    public JyDetailDTO getShopPv() {
        return shopPv;
    }

    public void setShopPv(JyDetailDTO shopPv) {
        this.shopPv = shopPv;
    }

    public JyDetailDTO getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(JyDetailDTO salesNum) {
        this.salesNum = salesNum;
    }

    public JyDetailDTO getSalesMoney() {
        return salesMoney;
    }

    public void setSalesMoney(JyDetailDTO salesMoney) {
        this.salesMoney = salesMoney;
    }

    public JyDetailDTO getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(JyDetailDTO goodsNum) {
        this.goodsNum = goodsNum;
    }

    public JyDetailDTO getShopFav() {
        return shopFav;
    }

    public void setShopFav(JyDetailDTO shopFav) {
        this.shopFav = shopFav;
    }

    public List<HashMap<String, Object>> getHotgoods() {
        return hotgoods;
    }

    public void setHotgoods(List<HashMap<String, Object>> hotgoods) {
        this.hotgoods = hotgoods;
    }

    public List<HashMap<String, Object>> getHotfav() {
        return hotfav;
    }

    public void setHotfav(List<HashMap<String, Object>> hotfav) {
        this.hotfav = hotfav;
    }


}
