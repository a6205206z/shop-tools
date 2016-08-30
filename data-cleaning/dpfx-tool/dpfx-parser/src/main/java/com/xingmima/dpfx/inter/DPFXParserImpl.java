package com.xingmima.dpfx.inter;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.*;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.util.HashSet;
import java.util.Set;

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
     * 初始化html解析工具
     *
     * @throws ParserException
     */
    public void initParser() throws ParserException {
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

}
