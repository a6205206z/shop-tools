package com.xingmima.dpfx.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DShop {
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
     * 店铺名称
     */
    private String title;

    /**
     * 信用总分（“好评”加一分，“中评”不加分，“差评”扣一分。分越高，等级越高）
     */
    private Long creditScore;

    /**
     * 信用等级（是根据score生成的），信用等级：淘宝会员在淘宝网上的信用度，分为20个级别，级别如：level = 1 时，表示一心；level = 2 时，表示二心
     */
    private String creditLevel;

    /**
     * 收到的评价总条数。取值范围:大于零的整数
     */
    private Integer creditTotalNum;

    /**
     * 收到的好评总条数。取值范围:大于零的整数
     */
    private Integer creditGoodNum;

    /**
     * 好评率
     */
    private BigDecimal rating;

    /**
     * 最后修改时间。格式：yyyy-MM-dd HH:mm:ss
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
     * 店铺名称
     * @return title 店铺名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 店铺名称
     * @param title 店铺名称
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 信用总分（“好评”加一分，“中评”不加分，“差评”扣一分。分越高，等级越高）
     * @return credit_score 信用总分（“好评”加一分，“中评”不加分，“差评”扣一分。分越高，等级越高）
     */
    public Long getCreditScore() {
        return creditScore;
    }

    /**
     * 信用总分（“好评”加一分，“中评”不加分，“差评”扣一分。分越高，等级越高）
     * @param creditScore 信用总分（“好评”加一分，“中评”不加分，“差评”扣一分。分越高，等级越高）
     */
    public void setCreditScore(Long creditScore) {
        this.creditScore = creditScore;
    }

    /**
     * 信用等级（是根据score生成的），信用等级：淘宝会员在淘宝网上的信用度，分为20个级别，级别如：level = 1 时，表示一心；level = 2 时，表示二心
     * @return credit_level 信用等级（是根据score生成的），信用等级：淘宝会员在淘宝网上的信用度，分为20个级别，级别如：level = 1 时，表示一心；level = 2 时，表示二心
     */
    public String getCreditLevel() {
        return creditLevel;
    }

    /**
     * 信用等级（是根据score生成的），信用等级：淘宝会员在淘宝网上的信用度，分为20个级别，级别如：level = 1 时，表示一心；level = 2 时，表示二心
     * @param creditLevel 信用等级（是根据score生成的），信用等级：淘宝会员在淘宝网上的信用度，分为20个级别，级别如：level = 1 时，表示一心；level = 2 时，表示二心
     */
    public void setCreditLevel(String creditLevel) {
        this.creditLevel = creditLevel == null ? null : creditLevel.trim();
    }

    /**
     * 收到的评价总条数。取值范围:大于零的整数
     * @return credit_total_num 收到的评价总条数。取值范围:大于零的整数
     */
    public Integer getCreditTotalNum() {
        return creditTotalNum;
    }

    /**
     * 收到的评价总条数。取值范围:大于零的整数
     * @param creditTotalNum 收到的评价总条数。取值范围:大于零的整数
     */
    public void setCreditTotalNum(Integer creditTotalNum) {
        this.creditTotalNum = creditTotalNum;
    }

    /**
     * 收到的好评总条数。取值范围:大于零的整数
     * @return credit_good_num 收到的好评总条数。取值范围:大于零的整数
     */
    public Integer getCreditGoodNum() {
        return creditGoodNum;
    }

    /**
     * 收到的好评总条数。取值范围:大于零的整数
     * @param creditGoodNum 收到的好评总条数。取值范围:大于零的整数
     */
    public void setCreditGoodNum(Integer creditGoodNum) {
        this.creditGoodNum = creditGoodNum;
    }

    /**
     * 好评率
     * @return rating 好评率
     */
    public BigDecimal getRating() {
        return rating;
    }

    /**
     * 好评率
     * @param rating 好评率
     */
    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    /**
     * 最后修改时间。格式：yyyy-MM-dd HH:mm:ss
     * @return updated 最后修改时间。格式：yyyy-MM-dd HH:mm:ss
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * 最后修改时间。格式：yyyy-MM-dd HH:mm:ss
     * @param updated 最后修改时间。格式：yyyy-MM-dd HH:mm:ss
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
        sb.append(", shopid=").append(shopid);
        sb.append(", title=").append(title);
        sb.append(", creditScore=").append(creditScore);
        sb.append(", creditLevel=").append(creditLevel);
        sb.append(", creditTotalNum=").append(creditTotalNum);
        sb.append(", creditGoodNum=").append(creditGoodNum);
        sb.append(", rating=").append(rating);
        sb.append(", updated=").append(updated);
        sb.append("]");
        return sb.toString();
    }
}