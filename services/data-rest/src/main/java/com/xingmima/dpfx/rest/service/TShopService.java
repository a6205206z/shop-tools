package com.xingmima.dpfx.rest.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xingmima.dpfx.rest.entity.TShop;
import com.xingmima.dpfx.rest.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Xingmima.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 *
 * @author cean
 * @version com.xingmima.dpfx.rest.service, v 0.1
 * @date 16 /9/19.
 */
@Service
public class TShopService {
    private static Logger log = LoggerFactory.getLogger(TShopService.class);

    /**
     * Gets t shop list from taobao.
     *
     * @param key the key
     * @return the t shop list from taobao
     */
    public List<TShop> getTShopListFromTaobao(String key) {
        List<TShop> result = null;

        final String url = "https://shopsearch.taobao.com/search?app=shopsearch&imgfile=&commend=all&ssid=s5-e&search_type=shop&sourceId=tb.index&spm=a21bo.50862.201856-taobao-item.1&ie=utf8&initiative_id=tbindexz_20160919&q=" + key;
        String respBody = HttpUtil.xGET(url);

        if (!StringUtils.isEmpty(respBody)) {
            Pattern pattern = Pattern.compile("g_page_config = \\{(.*?)\\};");
            Matcher matcher = pattern.matcher(respBody);
            if (matcher.find()) {
                String jsonStr = String.format("{%s}",matcher.group(1));
                JSONObject jb = JSONObject.parseObject(jsonStr);
                JSONArray ja = null;
                String location = "head";
                while (jb != null){
                    if(location == "head") {
                        jb = jb.getJSONObject("mods");
                        location = "mods";
                    }else if(location == "mods"){
                        jb = jb.getJSONObject("shoplist");
                        location = "shoplist";
                    }else if(location == "shoplist"){
                        jb = jb.getJSONObject("data");
                        location = "data";
                    }else if(location == "data"){
                        ja = jb.getJSONArray("shopItems");
                        jb = null;
                    }
                }

                if(ja != null){
                    result = new ArrayList<>();
                    for(Object item: ja.toArray()){
                        JSONObject jitem = (JSONObject)item;
                        TShop ts = new TShop();
                        ts.setCreated(new Date());
                        ts.setId(UUID.randomUUID().toString());
                        ts.setLastTimes(0);
                        ts.setLocation(jitem.getString("provcity"));
                        ts.setLogoUrl("https:"+jitem.getString("picUrl"));
                        ts.setNick(jitem.getString("nick"));
                        ts.setSellerId(jitem.getLongValue("uid"));
                        ts.setShopid(jitem.getLongValue("nid"));
                        ts.setUpdated(new Date());
                        ts.setStoreUrl("https:"+jitem.getString("shopUrl"));
                        ts.setType(ts.getStoreUrl().contains("tmall")?"B":"C");
                        result.add(ts);
                    }
                }
            }
        }

        return result;
    }
}
