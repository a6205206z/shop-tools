package com.xingmima.dpfx.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xingmima.dpfx.entity.DDsr;
import com.xingmima.dpfx.entity.DItems;
import com.xingmima.dpfx.entity.DRated;
import com.xingmima.dpfx.entity.DShop;
import com.xingmima.dpfx.inter.TaobaoParser;
import com.xingmima.dpfx.tags.StrongTag;
import com.xingmima.dpfx.util.Constant;
import com.xingmima.dpfx.util.GuidUtils;
import com.xingmima.dpfx.util.RegexUtils;
import org.apache.commons.lang.StringUtils;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version TaobaoShopInfo, v 0.1
 * @date 2016/8/30 13:58
 */
public class TaobaoItemDetail extends TaobaoParser {
    public TaobaoItemDetail(String resource) {
        super(resource);
        if (!StringUtils.isEmpty(resource) && resource.length() > 200) {
            log.info(resource.substring(0, 100));
        } else {
            log.info(resource);
        }
    }

    public TaobaoItemDetail call() {
        boolean isOk = this.initSpiderShop();
        if (!isOk) {
            log.error("format error==", Constant.HTML_FORMAT_ERROR);
            return null;
        }
        return this;
    }

    /**
     * Handel dsr
     *
     * @return the d dsr
     * @throws ParserException the parser exception
     */
    public DItems handelItemInfo() throws ParserException {
        /*检查模块是否存在*/
        NodeList list = this.extractAllNodesThatMatch(FILTER_ID, "dsr");
        if (list == null || list.size() <= 0) {
            log.info("not found item!==={}", this.getShopid());
            return null;
        }
        return null;
    }

    public static void main(String[] args) {
        try {
//            String res = HttpUtil.get4("https://rate.taobao.com/user-rate-f07ef4944ee3876030f6f5b4186767b6.htm?spm=2013.1.1000126.2.pUZ5CK", "GBK");
//            res = "11111111111|20027371|西红柿|bbb|" + res;
            String res = readHtmlFile("d://file//shopinfo.html", "GBK");
            TaobaoItemDetail dox = new TaobaoItemDetail(res).call();

//            DShop obj = dox.handleShopInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
