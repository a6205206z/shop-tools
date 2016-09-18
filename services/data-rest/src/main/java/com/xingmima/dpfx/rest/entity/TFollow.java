package com.xingmima.dpfx.rest.entity;

import java.util.Date;
/**
 * 业务APP：我的关注
 */
public class TFollow extends BaseEntity{
    /**
     * 关注表，标识列
     */
    private String id;

    /**
     * 会员ID
     */
    private String uid;

    /**
     * 店铺ID
     */
    private Long shopid;

    /**
     * true:绑定的自己店铺 false 关注
     */
    private Boolean isBinding;

    /**
     * true:正常 false:取消
     */
    private Boolean status;

    /**
     * 修改时间。格式：yyyy-MM-dd HH:mm:ss
     */
    private Date updated;

    /**
     * 关注表，标识列
     * @return id 关注表，标识列
     */
    public String getId() {
        return id;
    }

    /**
     * 关注表，标识列
     * @param id 关注表，标识列
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 会员ID
     * @return uid 会员ID
     */
    public String getUid() {
        return uid;
    }

    /**
     * 会员ID
     * @param uid 会员ID
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
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
     * true:绑定的自己店铺 false 关注
     * @return is_binding true:绑定的自己店铺 false 关注
     */
    public Boolean getIsBinding() {
        return isBinding;
    }

    /**
     * true:绑定的自己店铺 false 关注
     * @param isBinding true:绑定的自己店铺 false 关注
     */
    public void setIsBinding(Boolean isBinding) {
        this.isBinding = isBinding;
    }

    /**
     * true:正常 false:取消
     * @return status true:正常 false:取消
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * true:正常 false:取消
     * @param status true:正常 false:取消
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * 修改时间。格式：yyyy-MM-dd HH:mm:ss
     * @return updated 修改时间。格式：yyyy-MM-dd HH:mm:ss
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * 修改时间。格式：yyyy-MM-dd HH:mm:ss
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
        sb.append(", uid=").append(uid);
        sb.append(", shopid=").append(shopid);
        sb.append(", isBinding=").append(isBinding);
        sb.append(", status=").append(status);
        sb.append(", updated=").append(updated);
        sb.append("]");
        return sb.toString();
    }
}