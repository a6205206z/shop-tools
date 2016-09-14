package com.xingmima.dpfx.rest.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务APP：店铺信息
 */
public class TShop extends BaseEntity {
    /**
     * 标识列
     */
    private String id;

    /**
     * 店铺ID
     */
    private Long shopid;

    /**
     * 卖家会员ID
     */
    private Long sellerId;

    /**
     * 加密后的会员ID
     */
    private String securityId;

    /**
     * 卖家昵称:店铺主旺旺
     */
    private String nick;

    /**
     * 店铺所属的类目编号
     */
    private Long cid;

    /**
     * 店铺地址
     */
    private String storeUrl;

    /**
     * 主营类目
     */
    private String category;

    /**
     * 用户类型。可选值:B(B商家),C(C商家)
     */
    private String type;

    /**
     * 所在地区，如：四川 成都
     */
    private String location;

    /**
     * 客服电话
     */
    private String serviceNumber;

    /**
     * 开店时间
     */
    private Date storeDate;

    /**
     * 状态:0正常 1禁用
     */
    private String status;

    /**
     * 最新抓取批次
     */
    private Integer lastTimes;

    /**
     *
     */
    private Date created;

    /**
     * 修改时间。格式：yyyy-MM-dd HH:mm:ss
     */
    private Date updated;

    /**
     * 标识列
     *
     * @return id 标识列
     */
    public String getId() {
        return id;
    }

    /**
     * 标识列
     *
     * @param id 标识列
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 店铺ID
     *
     * @return shopid 店铺ID
     */
    public Long getShopid() {
        return shopid;
    }

    /**
     * 店铺ID
     *
     * @param shopid 店铺ID
     */
    public void setShopid(Long shopid) {
        this.shopid = shopid;
    }

    /**
     * 卖家会员ID
     *
     * @return seller_id 卖家会员ID
     */
    public Long getSellerId() {
        return sellerId;
    }

    /**
     * 卖家会员ID
     *
     * @param sellerId 卖家会员ID
     */
    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 加密后的会员ID
     *
     * @return security_id 加密后的会员ID
     */
    public String getSecurityId() {
        return securityId;
    }

    /**
     * 加密后的会员ID
     *
     * @param securityId 加密后的会员ID
     */
    public void setSecurityId(String securityId) {
        this.securityId = securityId == null ? null : securityId.trim();
    }

    /**
     * 卖家昵称:店铺主旺旺
     *
     * @return nick 卖家昵称:店铺主旺旺
     */
    public String getNick() {
        return nick;
    }

    /**
     * 卖家昵称:店铺主旺旺
     *
     * @param nick 卖家昵称:店铺主旺旺
     */
    public void setNick(String nick) {
        this.nick = nick == null ? null : nick.trim();
    }

    /**
     * 店铺所属的类目编号
     *
     * @return cid 店铺所属的类目编号
     */
    public Long getCid() {
        return cid;
    }

    /**
     * 店铺所属的类目编号
     *
     * @param cid 店铺所属的类目编号
     */
    public void setCid(Long cid) {
        this.cid = cid;
    }

    /**
     * 店铺地址
     *
     * @return store_url 店铺地址
     */
    public String getStoreUrl() {
        return storeUrl;
    }

    /**
     * 店铺地址
     *
     * @param storeUrl 店铺地址
     */
    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl == null ? null : storeUrl.trim();
    }

    /**
     * 主营类目
     *
     * @return category 主营类目
     */
    public String getCategory() {
        return category;
    }

    /**
     * 主营类目
     *
     * @param category 主营类目
     */
    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    /**
     * 用户类型。可选值:B(B商家),C(C商家)
     *
     * @return type 用户类型。可选值:B(B商家),C(C商家)
     */
    public String getType() {
        return type;
    }

    /**
     * 用户类型。可选值:B(B商家),C(C商家)
     *
     * @param type 用户类型。可选值:B(B商家),C(C商家)
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 所在地区，如：四川 成都
     *
     * @return location 所在地区，如：四川 成都
     */
    public String getLocation() {
        return location;
    }

    /**
     * 所在地区，如：四川 成都
     *
     * @param location 所在地区，如：四川 成都
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * 客服电话
     *
     * @return service_number 客服电话
     */
    public String getServiceNumber() {
        return serviceNumber;
    }

    /**
     * 客服电话
     *
     * @param serviceNumber 客服电话
     */
    public void setServiceNumber(String serviceNumber) {
        this.serviceNumber = serviceNumber == null ? null : serviceNumber.trim();
    }

    /**
     * 开店时间
     *
     * @return store_date 开店时间
     */
    public Date getStoreDate() {
        return storeDate;
    }

    /**
     * 开店时间
     *
     * @param storeDate 开店时间
     */
    public void setStoreDate(Date storeDate) {
        this.storeDate = storeDate;
    }

    /**
     * 状态:0正常 1禁用
     *
     * @return status 状态:0正常 1禁用
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态:0正常 1禁用
     *
     * @param status 状态:0正常 1禁用
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 最新抓取批次
     *
     * @return last_times 最新抓取批次
     */
    public Integer getLastTimes() {
        return lastTimes;
    }

    /**
     * 最新抓取批次
     *
     * @param lastTimes 最新抓取批次
     */
    public void setLastTimes(Integer lastTimes) {
        this.lastTimes = lastTimes;
    }

    /**
     * @return created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * 修改时间。格式：yyyy-MM-dd HH:mm:ss
     *
     * @return updated 修改时间。格式：yyyy-MM-dd HH:mm:ss
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * 修改时间。格式：yyyy-MM-dd HH:mm:ss
     *
     * @param updated 修改时间。格式：yyyy-MM-dd HH:mm:ss
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
        sb.append(", shopid=").append(shopid);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", securityId=").append(securityId);
        sb.append(", nick=").append(nick);
        sb.append(", cid=").append(cid);
        sb.append(", storeUrl=").append(storeUrl);
        sb.append(", category=").append(category);
        sb.append(", type=").append(type);
        sb.append(", location=").append(location);
        sb.append(", serviceNumber=").append(serviceNumber);
        sb.append(", storeDate=").append(storeDate);
        sb.append(", status=").append(status);
        sb.append(", lastTimes=").append(lastTimes);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
        sb.append("]");
        return sb.toString();
    }
}