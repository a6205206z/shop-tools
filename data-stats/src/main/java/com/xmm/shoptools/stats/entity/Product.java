package com.xmm.shoptools.stats.entity;

import java.util.Date;

/**
 * Xingmima.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 *
 * @author cean
 * @version com.xmm.shoptools.stats.entity, v 0.1
 * @date 16 /9/8.
 */
public class Product {
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
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets item url.
     *
     * @return the item url
     */
    public String getItem_url() {
        return item_url;
    }

    /**
     * Sets item url.
     *
     * @param item_url the item url
     */
    public void setItem_url(String item_url) {
        this.item_url = item_url;
    }

    /**
     * Gets pic url.
     *
     * @return the pic url
     */
    public String getPic_url() {
        return pic_url;
    }

    /**
     * Sets pic url.
     *
     * @param pic_url the pic url
     */
    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    /**
     * Gets rcid.
     *
     * @return the rcid
     */
    public long getRcid() {
        return rcid;
    }

    /**
     * Sets rcid.
     *
     * @param rcid the rcid
     */
    public void setRcid(long rcid) {
        this.rcid = rcid;
    }

    /**
     * Gets cid.
     *
     * @return the cid
     */
    public long getCid() {
        return cid;
    }

    /**
     * Sets cid.
     *
     * @param cid the cid
     */
    public void setCid(long cid) {
        this.cid = cid;
    }

    /**
     * Gets marker price.
     *
     * @return the marker price
     */
    public String getMarker_price() {
        return marker_price;
    }

    /**
     * Sets marker price.
     *
     * @param marker_price the marker price
     */
    public void setMarker_price(String marker_price) {
        this.marker_price = marker_price;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets post fee.
     *
     * @return the post fee
     */
    public double getPost_fee() {
        return post_fee;
    }

    /**
     * Sets post fee.
     *
     * @param post_fee the post fee
     */
    public void setPost_fee(double post_fee) {
        this.post_fee = post_fee;
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
     * Gets confirm goods count.
     *
     * @return the confirm goods count
     */
    public int getConfirm_goods_count() {
        return confirm_goods_count;
    }

    /**
     * Sets confirm goods count.
     *
     * @param confirm_goods_count the confirm goods count
     */
    public void setConfirm_goods_count(int confirm_goods_count) {
        this.confirm_goods_count = confirm_goods_count;
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
     * Gets sellable quantity.
     *
     * @return the sellable quantity
     */
    public int getSellable_quantity() {
        return sellable_quantity;
    }

    /**
     * Sets sellable quantity.
     *
     * @param sellable_quantity the sellable quantity
     */
    public void setSellable_quantity(int sellable_quantity) {
        this.sellable_quantity = sellable_quantity;
    }

    /**
     * Gets brand name.
     *
     * @return the brand name
     */
    public String getBrand_name() {
        return brand_name;
    }

    /**
     * Sets brand name.
     *
     * @param brand_name the brand name
     */
    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    /**
     * Gets list time.
     *
     * @return the list time
     */
    public Date getList_time() {
        return list_time;
    }

    /**
     * Sets list time.
     *
     * @param list_time the list time
     */
    public void setList_time(Date list_time) {
        this.list_time = list_time;
    }

    /**
     * Gets delist time.
     *
     * @return the delist time
     */
    public Date getDelist_time() {
        return delist_time;
    }

    /**
     * Sets delist time.
     *
     * @param delist_time the delist time
     */
    public void setDelist_time(Date delist_time) {
        this.delist_time = delist_time;
    }

    /**
     * Gets add time.
     *
     * @return the add time
     */
    public Date getAdd_time() {
        return add_time;
    }

    /**
     * Sets add time.
     *
     * @param add_time the add time
     */
    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    /**
     * Is delisting boolean.
     *
     * @return the boolean
     */
    public boolean is_delisting() {
        return is_delisting;
    }

    /**
     * Sets is delisting.
     *
     * @param is_delisting the is delisting
     */
    public void setIs_delisting(boolean is_delisting) {
        this.is_delisting = is_delisting;
    }

    /**
     * Gets rated.
     *
     * @return the rated
     */
    public double getRated() {
        return rated;
    }

    /**
     * Sets rated.
     *
     * @param rated the rated
     */
    public void setRated(double rated) {
        this.rated = rated;
    }

    /**
     * Gets updated.
     *
     * @return the updated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * Sets updated.
     *
     * @param updated the updated
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
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

    private long date;
    private long shopid;
    private long numiid;
    private String title;
    private String item_url;
    private String pic_url;
    private long rcid;
    private long cid;
    private String marker_price;
    private double price;
    private double post_fee;
    private int sold_total_count;
    private int confirm_goods_count;
    private int total_rated_count;
    private double total_sales;
    private int stock;
    private int sellable_quantity;
    private String brand_name;
    private Date list_time;
    private Date delist_time;
    private Date add_time;
    private boolean is_delisting;
    private double rated;
    private Date updated;
    private int i_favorite_num;
    private int i_share_num;
    private int i_pv;
}
