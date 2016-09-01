package com.xingmima.dpfx.inter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xingmima.dpfx.util.Tool;
import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.*;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * html源码解析工具
 * <p>
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version DPFXParserImpl, v 0.1
 * @date 2016/8/30 13:56
 */
public class DPFXParserImpl implements DPFXParser {
    /**
     * 按id页面过滤参数
     */
    protected static final String FILTER_ID = "id";
    /**
     * 按class页面过滤参数
     */
    protected static final String FILTER_CLASS = "class";

    /**
     * 待分析源码临时储存对象
     */
    private String resource = null;

    /**
     * html解析工具类
     */
    private Parser parser = null;

    public DPFXParserImpl(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    /**
     * 重置对象内部参数
     */
    public void reset() {
        this.resource = null;
        this.parser = null;
    }

    /**
     * 自定义参数
     */
    private String param = null;
    /**
     * 轮次
     **/
    private Long runid = null;
    /**
     * 店铺ID
     */
    private Long shopid = null;

    public String getParam() {
        return param;
    }

    public Long getRunid() {
        return runid;
    }

    public Long getShopid() {
        return shopid;
    }

    /**
     * 初始化html解析工具
     *
     * @throws ParserException
     */
    private void initParser() throws ParserException {
        if (!StringUtils.isEmpty(this.resource)) {
            parser = new Parser(this.resource);
            parser.setEncoding(parser.getEncoding());
        } else {
            throw new ParserException("param resource empty");
        }
    }

    /**
     * 根据条件key过虑出列表数据
     *
     * @param key
     * @param value
     * @return
     * @throws ParserException
     */
    public NodeList extractAllNodesThatMatch(String key, String value)
            throws ParserException {
        try {
            this.initParser();
            NodeList tmp = parser.extractAllNodesThatMatch(new HasAttributeFilter(key, value));
            return tmp;
        } finally {
            parser = null;
        }
    }

    /**
     * 从入口连接处缩小范围, filter 用来过滤链接
     *
     * @param key    id, class
     * @param value  值
     * @param filter 二次过滤正则
     * @return
     * @throws ParserException
     */
    public Set<String> extractAllNodesThatMatch(String key, String value,
                                                LinkRegexFilter filter) throws ParserException {
        Set<String> links = new HashSet<String>();
        NodeList root = extractAllNodesThatMatch(key, value);
        if (null != root) {
            // 二次过滤全部满足正则的A标记
            NodeList list = root.extractAllNodesThatMatch(filter, true); // true
            for (int i = 0; i < list.size(); i++) {
                Node tag = list.elementAt(i);
                if (tag instanceof LinkTag) {
                    links.add(((LinkTag) tag).getLink());
                }
            }
        }
        return links;
    }

    /**
     * 获取子节点列表对象
     *
     * @param tagName
     * @return
     * @throws ParserException
     */
    public NodeList getSubNodes(String tagName)
            throws ParserException {
        try {
            TagNameFilter filter = new TagNameFilter(tagName);
            this.initParser();
            return parser.parse(new HasParentFilter(filter));
        } finally {
            parser = null;
        }
    }

    /**
     * 根据tag获取子节点
     *
     * @param div
     * @param node
     * @return
     * @throws ParserException
     */
    public NodeList getSubNodesByTag(Node div, TagNode node) {
        NodeList list = new NodeList();
        div.collectInto(list, new NodeClassFilter(node.getClass()));
        return list;
    }

    /**
     * 按节点类型加载html节点数组（如script）
     *
     * @param cls
     * @return
     * @throws ParserException
     */
    public Node[] getNodeClassFilter(Class cls) throws ParserException {
        Parser parser = null;
        try {
            this.initParser();
            return parser.extractAllNodesThatMatch(new NodeClassFilter(cls))
                    .toNodeArray();
        } finally {
            parser = null;
        }
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
        JSONObject obj = (JSONObject) JSON.parse(this.getParam());
        this.runid = obj.getLong("runid");
        this.shopid = obj.getLong("shopid");
        if (null == runid || null == shopid) {
            return false;
        }

        /*整理html数据*/
        int s = (this.param + "|").length();
        this.resource = this.resource.substring(s);
        return true;
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
