package com.xmm.shoptools.backend.vo;

/**
 * @author leidian
 * @version $Id: DitemsQuery.java, v 0.1 2016年9月9日 下午3:15:26 leidian Exp $
 */
public class DitemsQuery extends BaseQuery {
    private Long shopid;
    private Long date;
    
    public Long getShopid() {
        return shopid;
    }
    public void setShopid(Long shopid) {
        this.shopid = shopid;
    }
    public Long getDate() {
        return date;
    }
    public void setDate(Long date) {
        this.date = date;
    }
    
    
}

