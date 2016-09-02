package com.xingmima.dpfx.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DDsr {
    /**
     * 标识列
     */
    private String id;

    /**
     * 数据抓取日期：YYYYMMDDHH
     */
    private Long date;

    /**
     * 店铺ID
     */
    private Long shopid;

    /**
     * 宝贝与描述相符
     */
    private BigDecimal detail;

    /**
     * 高、低、持平行业评分：计算规则：(店铺得分-同行业平均分)/(同行业店铺最高得分-同行业平均分)
     */
    private BigDecimal dHy;

    /**
     * 动态评分分布情况
     */
    private String dJson;

    /**
     * 卖家的服务态度
     */
    private BigDecimal seller;

    /**
     * 高、低、持平行业评分：计算规则：(店铺得分-同行业平均分)/(同行业店铺最高得分-同行业平均分)
     */
    private BigDecimal sHy;

    /**
     * 动态评分分布情况
     */
    private String sJson;

    /**
     * 卖家发货的速度
     */
    private BigDecimal rating;

    /**
     * 高、低、持平行业评分：计算规则：(店铺得分-同行业平均分)/(同行业店铺最高得分-同行业平均分)
     */
    private BigDecimal rHy;

    /**
     * 动态评分分布情况
     */
    private String rJson;

    /**
     * 抓取时间
     */
    private Date created;

    /**
     * 标识列
     * @return id 标识列
     */
    public String getId() {
        return id;
    }

    /**
     * 标识列
     * @param id 标识列
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 数据抓取日期：YYYYMMDDHH
     * @return date 数据抓取日期：YYYYMMDDHH
     */
    public Long getDate() {
        return date;
    }

    /**
     * 数据抓取日期：YYYYMMDDHH
     * @param date 数据抓取日期：YYYYMMDDHH
     */
    public void setDate(Long date) {
        this.date = date;
    }

    /**
     * 店铺ID
     * @return shopid 店铺ID
     */
    public Long getShopid() {
        return shopid;
    }

    /**
     * 店铺ID
     * @param shopid 店铺ID
     */
    public void setShopid(Long shopid) {
        this.shopid = shopid;
    }

    /**
     * 宝贝与描述相符
     * @return detail 宝贝与描述相符
     */
    public BigDecimal getDetail() {
        return detail;
    }

    /**
     * 宝贝与描述相符
     * @param detail 宝贝与描述相符
     */
    public void setDetail(BigDecimal detail) {
        this.detail = detail;
    }

    /**
     * 高、低、持平行业评分：计算规则：(店铺得分-同行业平均分)/(同行业店铺最高得分-同行业平均分)
     * @return d_hy 高、低、持平行业评分：计算规则：(店铺得分-同行业平均分)/(同行业店铺最高得分-同行业平均分)
     */
    public BigDecimal getdHy() {
        return dHy;
    }

    /**
     * 高、低、持平行业评分：计算规则：(店铺得分-同行业平均分)/(同行业店铺最高得分-同行业平均分)
     * @param dHy 高、低、持平行业评分：计算规则：(店铺得分-同行业平均分)/(同行业店铺最高得分-同行业平均分)
     */
    public void setdHy(BigDecimal dHy) {
        this.dHy = dHy;
    }

    /**
     * 动态评分分布情况
     * @return d_json 动态评分分布情况
     */
    public String getdJson() {
        return dJson;
    }

    /**
     * 动态评分分布情况
     * @param dJson 动态评分分布情况
     */
    public void setdJson(String dJson) {
        this.dJson = dJson == null ? null : dJson.trim();
    }

    /**
     * 卖家的服务态度
     * @return seller 卖家的服务态度
     */
    public BigDecimal getSeller() {
        return seller;
    }

    /**
     * 卖家的服务态度
     * @param seller 卖家的服务态度
     */
    public void setSeller(BigDecimal seller) {
        this.seller = seller;
    }

    /**
     * 高、低、持平行业评分：计算规则：(店铺得分-同行业平均分)/(同行业店铺最高得分-同行业平均分)
     * @return s_hy 高、低、持平行业评分：计算规则：(店铺得分-同行业平均分)/(同行业店铺最高得分-同行业平均分)
     */
    public BigDecimal getsHy() {
        return sHy;
    }

    /**
     * 高、低、持平行业评分：计算规则：(店铺得分-同行业平均分)/(同行业店铺最高得分-同行业平均分)
     * @param sHy 高、低、持平行业评分：计算规则：(店铺得分-同行业平均分)/(同行业店铺最高得分-同行业平均分)
     */
    public void setsHy(BigDecimal sHy) {
        this.sHy = sHy;
    }

    /**
     * 动态评分分布情况
     * @return s_json 动态评分分布情况
     */
    public String getsJson() {
        return sJson;
    }

    /**
     * 动态评分分布情况
     * @param sJson 动态评分分布情况
     */
    public void setsJson(String sJson) {
        this.sJson = sJson == null ? null : sJson.trim();
    }

    /**
     * 卖家发货的速度
     * @return rating 卖家发货的速度
     */
    public BigDecimal getRating() {
        return rating;
    }

    /**
     * 卖家发货的速度
     * @param rating 卖家发货的速度
     */
    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    /**
     * 高、低、持平行业评分：计算规则：(店铺得分-同行业平均分)/(同行业店铺最高得分-同行业平均分)
     * @return r_hy 高、低、持平行业评分：计算规则：(店铺得分-同行业平均分)/(同行业店铺最高得分-同行业平均分)
     */
    public BigDecimal getrHy() {
        return rHy;
    }

    /**
     * 高、低、持平行业评分：计算规则：(店铺得分-同行业平均分)/(同行业店铺最高得分-同行业平均分)
     * @param rHy 高、低、持平行业评分：计算规则：(店铺得分-同行业平均分)/(同行业店铺最高得分-同行业平均分)
     */
    public void setrHy(BigDecimal rHy) {
        this.rHy = rHy;
    }

    /**
     * 动态评分分布情况
     * @return r_json 动态评分分布情况
     */
    public String getrJson() {
        return rJson;
    }

    /**
     * 动态评分分布情况
     * @param rJson 动态评分分布情况
     */
    public void setrJson(String rJson) {
        this.rJson = rJson == null ? null : rJson.trim();
    }

    /**
     * 抓取时间
     * @return created 抓取时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 抓取时间
     * @param created 抓取时间
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", date=").append(date);
        sb.append(", shopid=").append(shopid);
        sb.append(", detail=").append(detail);
        sb.append(", dHy=").append(dHy);
        sb.append(", dJson=").append(dJson);
        sb.append(", seller=").append(seller);
        sb.append(", sHy=").append(sHy);
        sb.append(", sJson=").append(sJson);
        sb.append(", rating=").append(rating);
        sb.append(", rHy=").append(rHy);
        sb.append(", rJson=").append(rJson);
        sb.append(", created=").append(created);
        sb.append("]");
        return sb.toString();
    }
}