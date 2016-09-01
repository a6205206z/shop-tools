package com.xingmima.dpfx.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DServices {
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
     * 平均退款速度：近30天卖家处理完结一笔退款申请平均花费的时长:0.0天
     */
    private BigDecimal shLoc;

    /**
     * 相对行业值比较
     */
    private String shOperator;

    /**
     * 行业值：2.68天
     */
    private BigDecimal shHy;

    /**
     * 近30天售后率：指卖家在近30天退款成功的笔数（包含售后成立）占近30天支付宝成交笔数的比率:0.0%
     */
    private BigDecimal shlLoc;

    /**
     * 相对行业值比较
     */
    private String shlOperator;

    /**
     * 行业值：9.45%
     */
    private BigDecimal shlHy;

    /**
     * 近30天纠纷退款：淘宝介入处理且判为卖家责任的退款:0.0%
     */
    private BigDecimal jfLoc;

    /**
     * 相对行业值比较
     */
    private String jfOperator;

    /**
     * 行业值：0.01%
     */
    private BigDecimal jfHy;

    /**
     * 近30天被处罚总次数:0.0%
     */
    private Integer cfLoc;

    /**
     * 相对行业值比较
     */
    private String cfOperator;

    /**
     * 行业值：0.7%
     */
    private BigDecimal cfHy;

    /**
     * 近30天被处罚明细：[]
     */
    private String cfJson;

    /**
     * 退款完结时长 = 退款完结总时长 / 退款完结总笔数:0.0%
     */
    private BigDecimal tmTkLoc;

    /**
     * 相对行业值比较
     */
    private String tmTkOperator;

    /**
     * 行业值：0.7%
     */
    private BigDecimal tmTkHy;

    /**
     * 退款自主完结率 = 商家自主完结退款笔数 / 店铺完结退款总笔数:96.65%
     */
    private BigDecimal tmTkwjlLoc;

    /**
     * 相对行业值比较
     */
    private String tmTkwjlOperator;

    /**
     * 行业值：0.7%
     */
    private BigDecimal tmTkwjlHy;

    /**
     * 纠纷退款率 = 判责商家责任笔数 / 支付宝成交笔数:0.0052%
     */
    private BigDecimal tmTklLoc;

    /**
     * 相对行业值比较
     */
    private String tmTklOperator;

    /**
     * 行业值：0.7%
     */
    private BigDecimal tmTklHy;

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
     * 平均退款速度：近30天卖家处理完结一笔退款申请平均花费的时长:0.0天
     * @return sh_loc 平均退款速度：近30天卖家处理完结一笔退款申请平均花费的时长:0.0天
     */
    public BigDecimal getShLoc() {
        return shLoc;
    }

    /**
     * 平均退款速度：近30天卖家处理完结一笔退款申请平均花费的时长:0.0天
     * @param shLoc 平均退款速度：近30天卖家处理完结一笔退款申请平均花费的时长:0.0天
     */
    public void setShLoc(BigDecimal shLoc) {
        this.shLoc = shLoc;
    }

    /**
     * 相对行业值比较
     * @return sh_operator 相对行业值比较
     */
    public String getShOperator() {
        return shOperator;
    }

    /**
     * 相对行业值比较
     * @param shOperator 相对行业值比较
     */
    public void setShOperator(String shOperator) {
        this.shOperator = shOperator == null ? null : shOperator.trim();
    }

    /**
     * 行业值：2.68天
     * @return sh_hy 行业值：2.68天
     */
    public BigDecimal getShHy() {
        return shHy;
    }

    /**
     * 行业值：2.68天
     * @param shHy 行业值：2.68天
     */
    public void setShHy(BigDecimal shHy) {
        this.shHy = shHy;
    }

    /**
     * 近30天售后率：指卖家在近30天退款成功的笔数（包含售后成立）占近30天支付宝成交笔数的比率:0.0%
     * @return shl_loc 近30天售后率：指卖家在近30天退款成功的笔数（包含售后成立）占近30天支付宝成交笔数的比率:0.0%
     */
    public BigDecimal getShlLoc() {
        return shlLoc;
    }

    /**
     * 近30天售后率：指卖家在近30天退款成功的笔数（包含售后成立）占近30天支付宝成交笔数的比率:0.0%
     * @param shlLoc 近30天售后率：指卖家在近30天退款成功的笔数（包含售后成立）占近30天支付宝成交笔数的比率:0.0%
     */
    public void setShlLoc(BigDecimal shlLoc) {
        this.shlLoc = shlLoc;
    }

    /**
     * 相对行业值比较
     * @return shl_operator 相对行业值比较
     */
    public String getShlOperator() {
        return shlOperator;
    }

    /**
     * 相对行业值比较
     * @param shlOperator 相对行业值比较
     */
    public void setShlOperator(String shlOperator) {
        this.shlOperator = shlOperator == null ? null : shlOperator.trim();
    }

    /**
     * 行业值：9.45%
     * @return shl_hy 行业值：9.45%
     */
    public BigDecimal getShlHy() {
        return shlHy;
    }

    /**
     * 行业值：9.45%
     * @param shlHy 行业值：9.45%
     */
    public void setShlHy(BigDecimal shlHy) {
        this.shlHy = shlHy;
    }

    /**
     * 近30天纠纷退款：淘宝介入处理且判为卖家责任的退款:0.0%
     * @return jf_loc 近30天纠纷退款：淘宝介入处理且判为卖家责任的退款:0.0%
     */
    public BigDecimal getJfLoc() {
        return jfLoc;
    }

    /**
     * 近30天纠纷退款：淘宝介入处理且判为卖家责任的退款:0.0%
     * @param jfLoc 近30天纠纷退款：淘宝介入处理且判为卖家责任的退款:0.0%
     */
    public void setJfLoc(BigDecimal jfLoc) {
        this.jfLoc = jfLoc;
    }

    /**
     * 相对行业值比较
     * @return jf_operator 相对行业值比较
     */
    public String getJfOperator() {
        return jfOperator;
    }

    /**
     * 相对行业值比较
     * @param jfOperator 相对行业值比较
     */
    public void setJfOperator(String jfOperator) {
        this.jfOperator = jfOperator == null ? null : jfOperator.trim();
    }

    /**
     * 行业值：0.01%
     * @return jf_hy 行业值：0.01%
     */
    public BigDecimal getJfHy() {
        return jfHy;
    }

    /**
     * 行业值：0.01%
     * @param jfHy 行业值：0.01%
     */
    public void setJfHy(BigDecimal jfHy) {
        this.jfHy = jfHy;
    }

    /**
     * 近30天被处罚总次数:0.0%
     * @return cf_loc 近30天被处罚总次数:0.0%
     */
    public Integer getCfLoc() {
        return cfLoc;
    }

    /**
     * 近30天被处罚总次数:0.0%
     * @param cfLoc 近30天被处罚总次数:0.0%
     */
    public void setCfLoc(Integer cfLoc) {
        this.cfLoc = cfLoc;
    }

    /**
     * 相对行业值比较
     * @return cf_operator 相对行业值比较
     */
    public String getCfOperator() {
        return cfOperator;
    }

    /**
     * 相对行业值比较
     * @param cfOperator 相对行业值比较
     */
    public void setCfOperator(String cfOperator) {
        this.cfOperator = cfOperator == null ? null : cfOperator.trim();
    }

    /**
     * 行业值：0.7%
     * @return cf_hy 行业值：0.7%
     */
    public BigDecimal getCfHy() {
        return cfHy;
    }

    /**
     * 行业值：0.7%
     * @param cfHy 行业值：0.7%
     */
    public void setCfHy(BigDecimal cfHy) {
        this.cfHy = cfHy;
    }

    /**
     * 近30天被处罚明细：[]
     * @return cf_json 近30天被处罚明细：[]
     */
    public String getCfJson() {
        return cfJson;
    }

    /**
     * 近30天被处罚明细：[]
     * @param cfJson 近30天被处罚明细：[]
     */
    public void setCfJson(String cfJson) {
        this.cfJson = cfJson == null ? null : cfJson.trim();
    }

    /**
     * 退款完结时长 = 退款完结总时长 / 退款完结总笔数:0.0%
     * @return tm_tk_loc 退款完结时长 = 退款完结总时长 / 退款完结总笔数:0.0%
     */
    public BigDecimal getTmTkLoc() {
        return tmTkLoc;
    }

    /**
     * 退款完结时长 = 退款完结总时长 / 退款完结总笔数:0.0%
     * @param tmTkLoc 退款完结时长 = 退款完结总时长 / 退款完结总笔数:0.0%
     */
    public void setTmTkLoc(BigDecimal tmTkLoc) {
        this.tmTkLoc = tmTkLoc;
    }

    /**
     * 相对行业值比较
     * @return tm_tk_operator 相对行业值比较
     */
    public String getTmTkOperator() {
        return tmTkOperator;
    }

    /**
     * 相对行业值比较
     * @param tmTkOperator 相对行业值比较
     */
    public void setTmTkOperator(String tmTkOperator) {
        this.tmTkOperator = tmTkOperator == null ? null : tmTkOperator.trim();
    }

    /**
     * 行业值：0.7%
     * @return tm_tk_hy 行业值：0.7%
     */
    public BigDecimal getTmTkHy() {
        return tmTkHy;
    }

    /**
     * 行业值：0.7%
     * @param tmTkHy 行业值：0.7%
     */
    public void setTmTkHy(BigDecimal tmTkHy) {
        this.tmTkHy = tmTkHy;
    }

    /**
     * 退款自主完结率 = 商家自主完结退款笔数 / 店铺完结退款总笔数:96.65%
     * @return tm_tkwjl_loc 退款自主完结率 = 商家自主完结退款笔数 / 店铺完结退款总笔数:96.65%
     */
    public BigDecimal getTmTkwjlLoc() {
        return tmTkwjlLoc;
    }

    /**
     * 退款自主完结率 = 商家自主完结退款笔数 / 店铺完结退款总笔数:96.65%
     * @param tmTkwjlLoc 退款自主完结率 = 商家自主完结退款笔数 / 店铺完结退款总笔数:96.65%
     */
    public void setTmTkwjlLoc(BigDecimal tmTkwjlLoc) {
        this.tmTkwjlLoc = tmTkwjlLoc;
    }

    /**
     * 相对行业值比较
     * @return tm_tkwjl_operator 相对行业值比较
     */
    public String getTmTkwjlOperator() {
        return tmTkwjlOperator;
    }

    /**
     * 相对行业值比较
     * @param tmTkwjlOperator 相对行业值比较
     */
    public void setTmTkwjlOperator(String tmTkwjlOperator) {
        this.tmTkwjlOperator = tmTkwjlOperator == null ? null : tmTkwjlOperator.trim();
    }

    /**
     * 行业值：0.7%
     * @return tm_tkwjl_hy 行业值：0.7%
     */
    public BigDecimal getTmTkwjlHy() {
        return tmTkwjlHy;
    }

    /**
     * 行业值：0.7%
     * @param tmTkwjlHy 行业值：0.7%
     */
    public void setTmTkwjlHy(BigDecimal tmTkwjlHy) {
        this.tmTkwjlHy = tmTkwjlHy;
    }

    /**
     * 纠纷退款率 = 判责商家责任笔数 / 支付宝成交笔数:0.0052%
     * @return tm_tkl_loc 纠纷退款率 = 判责商家责任笔数 / 支付宝成交笔数:0.0052%
     */
    public BigDecimal getTmTklLoc() {
        return tmTklLoc;
    }

    /**
     * 纠纷退款率 = 判责商家责任笔数 / 支付宝成交笔数:0.0052%
     * @param tmTklLoc 纠纷退款率 = 判责商家责任笔数 / 支付宝成交笔数:0.0052%
     */
    public void setTmTklLoc(BigDecimal tmTklLoc) {
        this.tmTklLoc = tmTklLoc;
    }

    /**
     * 相对行业值比较
     * @return tm_tkl_operator 相对行业值比较
     */
    public String getTmTklOperator() {
        return tmTklOperator;
    }

    /**
     * 相对行业值比较
     * @param tmTklOperator 相对行业值比较
     */
    public void setTmTklOperator(String tmTklOperator) {
        this.tmTklOperator = tmTklOperator == null ? null : tmTklOperator.trim();
    }

    /**
     * 行业值：0.7%
     * @return tm_tkl_hy 行业值：0.7%
     */
    public BigDecimal getTmTklHy() {
        return tmTklHy;
    }

    /**
     * 行业值：0.7%
     * @param tmTklHy 行业值：0.7%
     */
    public void setTmTklHy(BigDecimal tmTklHy) {
        this.tmTklHy = tmTklHy;
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
        sb.append(", shLoc=").append(shLoc);
        sb.append(", shOperator=").append(shOperator);
        sb.append(", shHy=").append(shHy);
        sb.append(", shlLoc=").append(shlLoc);
        sb.append(", shlOperator=").append(shlOperator);
        sb.append(", shlHy=").append(shlHy);
        sb.append(", jfLoc=").append(jfLoc);
        sb.append(", jfOperator=").append(jfOperator);
        sb.append(", jfHy=").append(jfHy);
        sb.append(", cfLoc=").append(cfLoc);
        sb.append(", cfOperator=").append(cfOperator);
        sb.append(", cfHy=").append(cfHy);
        sb.append(", cfJson=").append(cfJson);
        sb.append(", tmTkLoc=").append(tmTkLoc);
        sb.append(", tmTkOperator=").append(tmTkOperator);
        sb.append(", tmTkHy=").append(tmTkHy);
        sb.append(", tmTkwjlLoc=").append(tmTkwjlLoc);
        sb.append(", tmTkwjlOperator=").append(tmTkwjlOperator);
        sb.append(", tmTkwjlHy=").append(tmTkwjlHy);
        sb.append(", tmTklLoc=").append(tmTklLoc);
        sb.append(", tmTklOperator=").append(tmTklOperator);
        sb.append(", tmTklHy=").append(tmTklHy);
        sb.append(", created=").append(created);
        sb.append("]");
        return sb.toString();
    }
}