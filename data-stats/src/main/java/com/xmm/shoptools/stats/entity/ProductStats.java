package com.xmm.shoptools.stats.entity;

/**
 * Xingmima.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 *
 * @author cean
 * @version com.xmm.shoptools.stats.entity, v 0.1
 * @date 16 /9/9.
 */
public class ProductStats {
    /**
     * Gets date.
     *
     * @return the date
     */
    public long getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(long date) {
        this.date = date;
    }

    /**
     * Gets shopid.
     *
     * @return the shopid
     */
    public long getShopid() {
        return shopid;
    }

    /**
     * Sets shopid.
     *
     * @param shopid the shopid
     */
    public void setShopid(long shopid) {
        this.shopid = shopid;
    }

    /**
     * Gets numiid.
     *
     * @return the numiid
     */
    public long getNumiid() {
        return numiid;
    }

    /**
     * Sets numiid.
     *
     * @param numiid the numiid
     */
    public void setNumiid(long numiid) {
        this.numiid = numiid;
    }

    /**
     * Gets total sales.
     *
     * @return the total sales
     */
    public double getTotal_sales() {
        return total_sales;
    }

    /**
     * Sets total sales.
     *
     * @param total_sales the total sales
     */
    public void setTotal_sales(double total_sales) {
        this.total_sales = total_sales;
    }

    /**
     * Gets sold total count.
     *
     * @return the sold total count
     */
    public int getSold_total_count() {
        return sold_total_count;
    }

    /**
     * Sets sold total count.
     *
     * @param sold_total_count the sold total count
     */
    public void setSold_total_count(int sold_total_count) {
        this.sold_total_count = sold_total_count;
    }

    /**
     * Gets total rated count.
     *
     * @return the total rated count
     */
    public int getTotal_rated_count() {
        return total_rated_count;
    }

    /**
     * Sets total rated count.
     *
     * @param total_rated_count the total rated count
     */
    public void setTotal_rated_count(int total_rated_count) {
        this.total_rated_count = total_rated_count;
    }

    /**
     * Gets stock.
     *
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets stock.
     *
     * @param stock the stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Gets i favorite num.
     *
     * @return the i favorite num
     */
    public int getI_favorite_num() {
        return i_favorite_num;
    }

    /**
     * Sets i favorite num.
     *
     * @param i_favorite_num the favorite num
     */
    public void setI_favorite_num(int i_favorite_num) {
        this.i_favorite_num = i_favorite_num;
    }

    /**
     * Gets i share num.
     *
     * @return the i share num
     */
    public int getI_share_num() {
        return i_share_num;
    }

    /**
     * Sets i share num.
     *
     * @param i_share_num the share num
     */
    public void setI_share_num(int i_share_num) {
        this.i_share_num = i_share_num;
    }

    /**
     * Gets i pv.
     *
     * @return the i pv
     */
    public int getI_pv() {
        return i_pv;
    }

    /**
     * Sets i pv.
     *
     * @param i_pv the pv
     */
    public void setI_pv(int i_pv) {
        this.i_pv = i_pv;
    }

    /**
     * Gets old title.
     *
     * @return the old title
     */
    public String getOld_title() {
        return old_title;
    }

    /**
     * Sets old title.
     *
     * @param old_title the old title
     */
    public void setOld_title(String old_title) {
        this.old_title = old_title;
    }

    /**
     * Gets new title.
     *
     * @return the new title
     */
    public String getNew_title() {
        return new_title;
    }

    /**
     * Sets new title.
     *
     * @param new_title the new title
     */
    public void setNew_title(String new_title) {
        this.new_title = new_title;
    }

    /**
     * Gets old price.
     *
     * @return the old price
     */
    public String getOld_price() {
        return old_price;
    }

    /**
     * Sets old price.
     *
     * @param old_price the old price
     */
    public void setOld_price(String old_price) {
        this.old_price = old_price;
    }

    /**
     * Gets new price.
     *
     * @return the new price
     */
    public String getNew_price() {
        return new_price;
    }

    /**
     * Sets new price.
     *
     * @param new_price the new price
     */
    public void setNew_price(String new_price) {
        this.new_price = new_price;
    }

    /**
     * Gets old pic.
     *
     * @return the old pic
     */
    public String getOld_pic() {
        return old_pic;
    }

    /**
     * Sets old pic.
     *
     * @param old_pic the old pic
     */
    public void setOld_pic(String old_pic) {
        this.old_pic = old_pic;
    }

    /**
     * Gets new pic.
     *
     * @return the new pic
     */
    public String getNew_pic() {
        return new_pic;
    }

    /**
     * Sets new pic.
     *
     * @param new_pic the new pic
     */
    public void setNew_pic(String new_pic) {
        this.new_pic = new_pic;
    }

    private long date;
    private long shopid;
    private long numiid;
    private double total_sales;
    private int sold_total_count;
    private int total_rated_count;
    private int stock;
    private int i_favorite_num;
    private int i_share_num;
    private int i_pv;
    private String old_title;
    private String new_title;
    private String old_price;
    private String new_price;
    private String old_pic;
    private String new_pic;
}
