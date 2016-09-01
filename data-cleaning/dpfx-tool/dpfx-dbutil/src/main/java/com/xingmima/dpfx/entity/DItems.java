package com.xingmima.dpfx.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DItems {
    /**
     * 标识列
     */
    private String id;

    /**
     * 数据抓取日期：YYYYMMDDHH
     */
    private Long date;

    /**
     * 商品ID
     */
    private Long numiid;

    /**
     * 商品标题,不能超过60字节
     */
    private String title;

    /**
     * 图片链接地址
     */
    private String url;

    /**
     * 商品价格
     */
    private String price;

    /**
     * 销售价格
     */
    private String salesPrice;

    /**
     * 销售量
     */
    private Integer salesNum;

    /**
     * 销售额
     */
    private Long totalSales;

    /**
     * 库存
     */
    private Integer stockNum;

    /**
     * 商品评价人数
     */
    private Integer ratedNum;

    /**
     * 自动上架时间
     */
    private Date listTime;

    /**
     * 自动下架时间（跟据上架时间计算+7天）
     */
    private Date delistTime;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 是否下架 true 正常 false下架
     */
    private Boolean isDelisting;

    /**
     * 商品动态评分
     */
    private BigDecimal rated;

    /**
     * 维护时间
     */
    private Date updated;

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
     * 商品ID
     * @return numiid 商品ID
     */
    public Long getNumiid() {
        return numiid;
    }

    /**
     * 商品ID
     * @param numiid 商品ID
     */
    public void setNumiid(Long numiid) {
        this.numiid = numiid;
    }

    /**
     * 商品标题,不能超过60字节
     * @return title 商品标题,不能超过60字节
     */
    public String getTitle() {
        return title;
    }

    /**
     * 商品标题,不能超过60字节
     * @param title 商品标题,不能超过60字节
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 图片链接地址
     * @return url 图片链接地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 图片链接地址
     * @param url 图片链接地址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 商品价格
     * @return price 商品价格
     */
    public String getPrice() {
        return price;
    }

    /**
     * 商品价格
     * @param price 商品价格
     */
    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    /**
     * 销售价格
     * @return sales_price 销售价格
     */
    public String getSalesPrice() {
        return salesPrice;
    }

    /**
     * 销售价格
     * @param salesPrice 销售价格
     */
    public void setSalesPrice(String salesPrice) {
        this.salesPrice = salesPrice == null ? null : salesPrice.trim();
    }

    /**
     * 销售量
     * @return sales_num 销售量
     */
    public Integer getSalesNum() {
        return salesNum;
    }

    /**
     * 销售量
     * @param salesNum 销售量
     */
    public void setSalesNum(Integer salesNum) {
        this.salesNum = salesNum;
    }

    /**
     * 销售额
     * @return total_sales 销售额
     */
    public Long getTotalSales() {
        return totalSales;
    }

    /**
     * 销售额
     * @param totalSales 销售额
     */
    public void setTotalSales(Long totalSales) {
        this.totalSales = totalSales;
    }

    /**
     * 库存
     * @return stock_num 库存
     */
    public Integer getStockNum() {
        return stockNum;
    }

    /**
     * 库存
     * @param stockNum 库存
     */
    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    /**
     * 商品评价人数
     * @return rated_num 商品评价人数
     */
    public Integer getRatedNum() {
        return ratedNum;
    }

    /**
     * 商品评价人数
     * @param ratedNum 商品评价人数
     */
    public void setRatedNum(Integer ratedNum) {
        this.ratedNum = ratedNum;
    }

    /**
     * 自动上架时间
     * @return list_time 自动上架时间
     */
    public Date getListTime() {
        return listTime;
    }

    /**
     * 自动上架时间
     * @param listTime 自动上架时间
     */
    public void setListTime(Date listTime) {
        this.listTime = listTime;
    }

    /**
     * 自动下架时间（跟据上架时间计算+7天）
     * @return delist_time 自动下架时间（跟据上架时间计算+7天）
     */
    public Date getDelistTime() {
        return delistTime;
    }

    /**
     * 自动下架时间（跟据上架时间计算+7天）
     * @param delistTime 自动下架时间（跟据上架时间计算+7天）
     */
    public void setDelistTime(Date delistTime) {
        this.delistTime = delistTime;
    }

    /**
     * 创建时间
     * @return created 创建时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 创建时间
     * @param created 创建时间
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * 是否下架 true 正常 false下架
     * @return is_delisting 是否下架 true 正常 false下架
     */
    public Boolean getIsDelisting() {
        return isDelisting;
    }

    /**
     * 是否下架 true 正常 false下架
     * @param isDelisting 是否下架 true 正常 false下架
     */
    public void setIsDelisting(Boolean isDelisting) {
        this.isDelisting = isDelisting;
    }

    /**
     * 商品动态评分
     * @return rated 商品动态评分
     */
    public BigDecimal getRated() {
        return rated;
    }

    /**
     * 商品动态评分
     * @param rated 商品动态评分
     */
    public void setRated(BigDecimal rated) {
        this.rated = rated;
    }

    /**
     * 维护时间
     * @return updated 维护时间
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * 维护时间
     * @param updated 维护时间
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", date=").append(date);
        sb.append(", numiid=").append(numiid);
        sb.append(", title=").append(title);
        sb.append(", url=").append(url);
        sb.append(", price=").append(price);
        sb.append(", salesPrice=").append(salesPrice);
        sb.append(", salesNum=").append(salesNum);
        sb.append(", totalSales=").append(totalSales);
        sb.append(", stockNum=").append(stockNum);
        sb.append(", ratedNum=").append(ratedNum);
        sb.append(", listTime=").append(listTime);
        sb.append(", delistTime=").append(delistTime);
        sb.append(", created=").append(created);
        sb.append(", isDelisting=").append(isDelisting);
        sb.append(", rated=").append(rated);
        sb.append(", updated=").append(updated);
        sb.append("]");
        return sb.toString();
    }
}