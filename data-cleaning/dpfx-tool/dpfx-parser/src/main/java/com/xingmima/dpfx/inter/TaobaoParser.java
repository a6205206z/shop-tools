package com.xingmima.dpfx.inter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.StringTokenizer;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version TaobaoParser, v 0.1
 * @date 2016/9/3 14:54
 */
public class TaobaoParser extends DPFXJsonParserImpl {
    /**
     * 自定义参数
     */
    protected String param = null;
    /**
     * 轮次
     **/
    private Long runid = null;
    /**
     * 店铺ID
     */
    private Long shopid = null;

    public String getParam() {
        return this.param;
    }

    public Long getRunid() {
        return runid;
    }

    public Long getShopid() {
        return shopid;
    }

    public TaobaoParser(String resource) {
        super(resource);
    }

    /**
     * 初始化原始数据
     *
     * @return
     */
    public boolean initSpiderShop() {
        StringTokenizer st = new StringTokenizer(this.resource, "|");
        try {
            while (st.hasMoreTokens()) {
                this.param = st.nextToken();
                break;
            }
        } finally {
        }
        if (StringUtils.isEmpty(this.param)) {
            return false;
        }

        log.info(this.param);
        try {
            JSONObject obj = (JSONObject) JSON.parse(this.param);
            if (null != obj) {
                try {
                    this.runid = obj.getLong("runid");
                    this.shopid = obj.getLong("shopid");
                } catch (Exception e) {
                    log.error("{} {}", obj.toJSONString(), e);
                }
            }
        } catch (Exception e) {
            log.error("init spider shop:", e);
        }
        if (null == runid || null == shopid) {
            return false;
        }

        /*整理html数据*/
        int s = (this.param + "|").length();
        this.resource = this.resource.substring(s);
        return true;
    }

    /**
     * 添加url前缀
     *
     * @param url
     * @return
     */
    public String httpPrefix(String url) {
        if (url != null && !url.startsWith("http:")) {
            return "http:" + url;
        }
        return url;
    }

    /**
     * Read html file string.
     *
     * @param filePath    the file path
     * @param charsetName the charset name
     * @return the string
     */
    public static String readHtmlFile(String filePath, String charsetName) {
        StringBuffer str = new StringBuffer();
        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), charsetName);//考虑到编码格式
                BufferedReader br = new BufferedReader(read);
                String line = null;
                while ((line = br.readLine()) != null) {
                    str.append(line);
                }
                read.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }
}
