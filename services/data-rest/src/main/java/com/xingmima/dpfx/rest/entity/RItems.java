package com.xingmima.dpfx.rest.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 业务APP：按天统计商品数据
 */
public class RItems extends BaseEntity{
    /**
     * 商品天报表数据（今天-昨天）
     */
    private String id;

    /**
     * 日期:YYYYMMDD
     */
    private Long date;

    /**
     * 店铺ID
     */
    private Long shopid;

    /**
     * 商品ID
     */
    private Long numiid;

    /**
     * 当天销售总金额
     */
    private BigDecimal totalSales;

    /**
     * 当天销量
     */
    private Integer soldTotalCount;

    /**
     * 当天评论数
     */
    private Integer totalRatedCount;

    /**
     * 当天库存量
     */
    private Integer stock;

    /**
     * 当天商品收藏数
     */
    private Integer iFavoriteNum;

    /**
     * 当天商品分享数
     */
    private Integer iShareNum;

    /**
     * 当天商品浏览量
     */
    private Integer iPv;

    /**
     * 旧标题
     */
    private String oldTitle;

    /**
     * 新标题
     */
    private String newTitle;

    /**
     * 旧价格
     */
    private String oldPrice;

    /**
     * 新价格
     */
    private String newPrice;

    /**
     * 旧主图
     */
    private String oldPic;

    /**
     * 新主图
     */
    private String newPic;

    /**
     * 统计完成时间
     */
    private Date created;

    /**
     * 商品天报表数据（今天-昨天）
     * @return id 商品天报表数据（今天-昨天）
     */
    public String getId() {
        return id;
    }

    /**
     * 商品天报表数据（今天-昨天）
     * @param id 商品天报表数据（今天-昨天）
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 日期:YYYYMMDD
     * @return date 日期:YYYYMMDD
     */
    public Long getDate() {
        return date;
    }

    /**
     * 日期:YYYYMMDD
     * @param date 日期:YYYYMMDD
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
     * 当天销售总金额
     * @return total_sales 当天销售总金额
     */
    public BigDecimal getTotalSales() {
        return totalSales;
    }

    /**
     * 当天销售总金额
     * @param totalSales 当天销售总金额
     */
    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }

    /**
     * 当天销量
     * @return sold_total_count 当天销量
     */
    public Integer getSoldTotalCount() {
        return soldTotalCount;
    }

    /**
     * 当天销量
     * @param soldTotalCount 当天销量
     */
    public void setSoldTotalCount(Integer soldTotalCount) {
        this.soldTotalCount = soldTotalCount;
    }

    /**
     * 当天评论数
     * @return total_rated_count 当天评论数
     */
    public Integer getTotalRatedCount() {
        return totalRatedCount;
    }

    /**
     * 当天评论数
     * @param totalRatedCount 当天评论数
     */
    public void setTotalRatedCount(Integer totalRatedCount) {
        this.totalRatedCount = totalRatedCount;
    }

    /**
     * 当天库存量
     * @return stock 当天库存量
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * 当天库存量
     * @param stock 当天库存量
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * 当天商品收藏数
     * @return i_favorite_num 当天商品收藏数
     */
    public Integer getiFavoriteNum() {
        return iFavoriteNum;
    }

    /**
     * 当天商品收藏数
     * @param iFavoriteNum 当天商品收藏数
     */
    public void setiFavoriteNum(Integer iFavoriteNum) {
        this.iFavoriteNum = iFavoriteNum;
    }

    /**
     * 当天商品分享数
     * @return i_share_num 当天商品分享数
     */
    public Integer getiShareNum() {
        return iShareNum;
    }

    /**
     * 当天商品分享数
     * @param iShareNum 当天商品分享数
     */
    public void setiShareNum(Integer iShareNum) {
        this.iShareNum = iShareNum;
    }

    /**
     * 当天商品浏览量
     * @return i_pv 当天商品浏览量
     */
    public Integer getiPv() {
        return iPv;
    }

    /**
     * 当天商品浏览量
     * @param iPv 当天商品浏览量
     */
    public void setiPv(Integer iPv) {
        this.iPv = iPv;
    }

    /**
     * 旧标题
     * @return old_title 旧标题
     */
    public String getOldTitle() {
        return oldTitle;
    }

    /**
     * 旧标题
     * @param oldTitle 旧标题
     */
    public void setOldTitle(String oldTitle) {
        this.oldTitle = oldTitle == null ? null : oldTitle.trim();
    }

    /**
     * 新标题
     * @return new_title 新标题
     */
    public String getNewTitle() {
        return newTitle;
    }

    /**
     * 新标题
     * @param newTitle 新标题
     */
    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle == null ? null : newTitle.trim();
    }

    /**
     * 旧价格
     * @return old_price 旧价格
     */
    public String getOldPrice() {
        return oldPrice;
    }

    /**
     * 旧价格
     * @param oldPrice 旧价格
     */
    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice == null ? null : oldPrice.trim();
    }

    /**
     * 新价格
     * @return new_price 新价格
     */
    public String getNewPrice() {
        return newPrice;
    }

    /**
     * 新价格
     * @param newPrice 新价格
     */
    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice == null ? null : newPrice.trim();
    }

    /**
     * 旧主图
     * @return old_pic 旧主图
     */
    public String getOldPic() {
        return oldPic;
    }

    /**
     * 旧主图
     * @param oldPic 旧主图
     */
    public void setOldPic(String oldPic) {
        this.oldPic = oldPic == null ? null : oldPic.trim();
    }

    /**
     * 新主图
     * @return new_pic 新主图
     */
    public String getNewPic() {
        return newPic;
    }

    /**
     * 新主图
     * @param newPic 新主图
     */
    public void setNewPic(String newPic) {
        this.newPic = newPic == null ? null : newPic.trim();
    }

    /**
     * 统计完成时间
     * @return created 统计完成时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 统计完成时间
     * @param created 统计完成时间
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
        sb.append(", numiid=").append(numiid);
        sb.append(", totalSales=").append(totalSales);
        sb.append(", soldTotalCount=").append(soldTotalCount);
        sb.append(", totalRatedCount=").append(totalRatedCount);
        sb.append(", stock=").append(stock);
        sb.append(", iFavoriteNum=").append(iFavoriteNum);
        sb.append(", iShareNum=").append(iShareNum);
        sb.append(", iPv=").append(iPv);
        sb.append(", oldTitle=").append(oldTitle);
        sb.append(", newTitle=").append(newTitle);
        sb.append(", oldPrice=").append(oldPrice);
        sb.append(", newPrice=").append(newPrice);
        sb.append(", oldPic=").append(oldPic);
        sb.append(", newPic=").append(newPic);
        sb.append(", created=").append(created);
        sb.append("]");
        return sb.toString();
    }
}