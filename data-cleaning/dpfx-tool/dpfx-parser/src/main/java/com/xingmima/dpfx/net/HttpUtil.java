package com.xingmima.dpfx.net;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.cookie.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version HttpUtil, v 0.1
 * @date 2016/9/01 10:58
 */
public class HttpUtil {

    private static Logger log = LoggerFactory.getLogger(HttpUtil.class);

    private static Integer timeOut = 30000; // 20 秒超时

    /**
     * Get请求，用于lblm网页数据抓取,firefox提交请求时参数完整模拟
     *
     * @param url     抓取的目标url
     * @param host    如：list.taobao.com
     * @param ua      如：Mozilla/5.0 (Windows NT 6.1; rv:33.0) Gecko/20100101
     *                Firefox/33.0
     * @param accept  如：application/json,或 file
     * @param referer 页面来源(上一级url)，重要（淘宝页面不传会没有结果）
     * @param charset 字符编码集，多为UTF-8, GBK
     * @return 网页文本数据
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String get(String url, String host, String ua, String accept,
                             String referer, String charset)
            throws ClientProtocolException, IOException {
        return get(url, host, ua, accept, referer, charset, null);
    }

    /**
     * Get请求，用于lblm网页数据抓取,firefox提交请求时参数完整模拟
     *
     * @param url     抓取的目标url
     * @param host    如：list.taobao.com
     * @param ua      如：Mozilla/5.0 (Windows NT 6.1; rv:33.0) Gecko/20100101
     *                Firefox/33.0
     * @param accept  如：application/json,或 file
     * @param referer 页面来源(上一级url)，重要（淘宝页面不传会没有结果）
     * @param charset 字符编码集，多为UTF-8, GBK
     * @return 网页文本数据
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String get(String url, String host, String ua, String accept,
                             String referer, String charset, String cookieInfo)
            throws ClientProtocolException, IOException {

		/* 1 生成 CloseableHttpClient 对象并设置参数 */
        CloseableHttpClient client = HttpClients.createDefault();
        try {

			/* 2 生成 HttpGet 对象并设置参数 */
            HttpGet request = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置 get 请求超时
                    .setConnectionRequestTimeout(timeOut)
                    .setConnectTimeout(timeOut).setSocketTimeout(timeOut)
                    .setCircularRedirectsAllowed(true).build();
            request.setConfig(requestConfig);

            // add request header
            // 必填字段
            request.addHeader(HttpHeaders.HOST, host);
            request.addHeader(HttpHeaders.REFERER, referer);
            request.addHeader(HttpHeaders.USER_AGENT, ua);
            if (null != cookieInfo)
                request.addHeader("Cookie", cookieInfo);

            // 没啥影响
            request.addHeader(HttpHeaders.ACCEPT, accept);
            request.addHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-cn,zh;q=0.5");
            request.addHeader(HttpHeaders.ACCEPT_CHARSET,
                    "GB2312,utf-8;q=0.7,*;q=0.7");// 编码需测试
            request.addHeader(HttpHeaders.CACHE_CONTROL, "max-age=0");

			/* 3 执行 HTTP GET 请求 */
            CloseableHttpResponse response = client.execute(request);

            try {
                int status = response.getStatusLine().getStatusCode();

                log.debug("conn-status:" + status + "*" + url);
                if (status == HttpStatus.SC_OK) {
                    /* 5 处理 HTTP 响应内容 */
                    return getHtml(response.getEntity(), charset);
                } else {
                    return null;
                }
            } finally {
                response.close();
            }
        } finally {
            /* 6 .释放连接 */
            client.close();
        }
    }

    public static String get2(String url, String host, String ua, String accept,
                              String referer, String charset, String cookieInfo)
            throws ClientProtocolException, IOException {

		/* 1 生成 CloseableHttpClient 对象并设置参数 */
        CloseableHttpClient client = HttpClientBuilder.create()
                .setRedirectStrategy(new SpaceRedirectStrategy()).build();
        try {
            /* 2 生成 HttpGet 对象并设置参数 */
            HttpGet request = new HttpGet(url);

            RequestConfig requestConfig = RequestConfig.custom()
                    // .setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY)
                    // 设置 get 请求超时
                    .setConnectionRequestTimeout(timeOut)
                    .setConnectTimeout(timeOut).setSocketTimeout(timeOut)
                    .setCircularRedirectsAllowed(true).build();
            request.setConfig(requestConfig);

            // add request header
            // 此处host不设置
            // if (host != null)
            // request.addHeader(HttpHeaders.HOST, host);
            request.addHeader(HttpHeaders.REFERER, referer);
            request.addHeader(HttpHeaders.USER_AGENT, ua);
            if (null != cookieInfo)
                request.addHeader("Cookie", cookieInfo);

            // 没啥影响
            request.addHeader(HttpHeaders.ACCEPT, accept);
            request.addHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-cn,zh;q=0.5");
            request.addHeader(HttpHeaders.ACCEPT_CHARSET,
                    "GB2312,utf-8;q=0.7,*;q=0.7");// 编码需测试
            request.addHeader(HttpHeaders.CACHE_CONTROL, "max-age=0");

			/* 3 执行 HTTP GET 请求 */
            CloseableHttpResponse response = client.execute(request,
                    new BasicHttpContext());

            try {
                int status = response.getStatusLine().getStatusCode();

                log.debug("conn-status:" + status + "*" + url);
                if (status == HttpStatus.SC_OK) {
					/* 5 处理 HTTP 响应内容 */
                    return getHtml(response.getEntity(), charset);
                } else {
                    return null;
                }
            } finally {
                response.close();
            }
        } finally {
			/* 6 .释放连接 */
            client.close();
        }
    }

    // 自定义CookieSpecProvider接口
    static CookieSpecProvider specProvider = new CookieSpecProvider() {
        public CookieSpec create(HttpContext context) {
            return new BrowserCompatSpec() {
                @Override
                public void validate(Cookie cookie, CookieOrigin origin)
                        throws MalformedCookieException {
                }
            };
        }
    };

    /**
     * 自定义cookies策略get
     *
     * @param url
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String get3(String url, String host, String ua, String accept,
                              String referer, String charset)
            throws ClientProtocolException, IOException {

        // 注册了自定义策略
        Registry<CookieSpecProvider> reg = RegistryBuilder
                .<CookieSpecProvider>create()
                .register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory())
                .register(CookieSpecs.BROWSER_COMPATIBILITY,
                        new BrowserCompatSpecFactory())
                .register("spec", specProvider).build();

		/* 1 生成 CloseableHttpClient 对象并设置参数 */
        CloseableHttpClient client = HttpClients.custom()
                .setDefaultCookieSpecRegistry(reg).build();// 绑定
        try {

			/* 2 生成 HttpGet 对象并设置参数 */
            HttpGet request = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setCookieSpec("spec")
                    // 设置 get 请求超时
                    .setConnectionRequestTimeout(timeOut)
                    .setConnectTimeout(timeOut).setSocketTimeout(timeOut)
                    .setCircularRedirectsAllowed(true).build();
            request.setConfig(requestConfig);

            // add request header
            // 必填字段
            request.addHeader(HttpHeaders.HOST, host);
            request.addHeader(HttpHeaders.REFERER, referer);
            request.addHeader(HttpHeaders.USER_AGENT, ua);

            // 没啥影响
            request.addHeader(HttpHeaders.ACCEPT, accept);
            request.addHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-cn,zh;q=0.5");
            request.addHeader(HttpHeaders.ACCEPT_CHARSET,
                    "GB2312,utf-8;q=0.7,*;q=0.7");// 编码需测试
            request.addHeader(HttpHeaders.CACHE_CONTROL, "max-age=0");

			/* 3 执行 HTTP GET 请求 */
            CloseableHttpResponse response = client.execute(request);

            try {
                int status = response.getStatusLine().getStatusCode();

                log.debug("conn-status:" + status + "*" + url);
                if (status == HttpStatus.SC_OK) {
					/* 5 处理 HTTP 响应内容 */
                    return getHtml(response.getEntity(), charset);
                } else {
                    return null;
                }
            } finally {
                response.close();
            }
        } finally {
			/* 6 .释放连接 */
            client.close();
        }
    }

    public static BasicClientCookie ck(String key, String value) {
        BasicClientCookie cookie = new BasicClientCookie(key, value);
        cookie.setVersion(0);
        cookie.setDomain("127.0.0.1");
        cookie.setPath("/");
        return cookie;
    }

    /**
     * 信任对方（https）所有证书
     *
     * @return
     * @version v1.0
     */
    public static CloseableHttpClient createSSLInsecureClient() {
        try {
            // Trust own CA and all self-signed certs
            SSLContext sslcontext = SSLContexts.custom()
                    .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                    .build();
            // Allow TLSv1 protocol only

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext, new String[]{"TLSv1"}, null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

            // SSLConnectionSocketFactory sslsf = new
            // SSLConnectionSocketFactory(
            // sslcontext, new String[]{"SSLv3"}, null, null);

            CloseableHttpClient client = HttpClients.custom()
                    .setSSLSocketFactory(sslsf).build();

            return client;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String get4(String url, String charset)
            throws ClientProtocolException, IOException {
        CloseableHttpClient client = createSSLInsecureClient();
        try {
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = client.execute(request);
            try {
                int status = response.getStatusLine().getStatusCode();

                log.debug("conn-status:" + status + "*" + url);

                if (status == HttpStatus.SC_OK) {
                    return getHtml(response.getEntity(), charset);
                } else {
                    return null;
                }
            } finally {
                response.close();
            }
        } finally {
            client.close();
        }
    }

    /**
     * POST提交请求
     *
     * @param url
     * @param host
     * @param referer   上级页面url
     * @param accept
     * @param userAgent
     * @param ajax      异步请求头
     * @param parmas
     * @param charset
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String post(String url, String host, String referer,
                              String accept, String userAgent, boolean ajax,
                              Map<String, String> parmas, String charset)
            throws ClientProtocolException, IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpPost request = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(timeOut)
                    .setConnectTimeout(timeOut).setSocketTimeout(timeOut)
                    .build();
            request.setConfig(requestConfig);

            request.addHeader(HttpHeaders.HOST, host);
            if (referer != null)
                request.addHeader(HttpHeaders.REFERER, referer);
            if (accept != null)
                request.addHeader(HttpHeaders.ACCEPT, accept);
            if (userAgent != null)
                request.addHeader(HttpHeaders.USER_AGENT, userAgent);
            if (ajax)
                request.addHeader("X-Requested-With", "XMLHttpRequest");

            // 参数设置
            if (null != parmas) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                for (String key : parmas.keySet()) {
                    nvps.add(new BasicNameValuePair(key, parmas.get(key)));
                }
                request.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
            }
            // DefaultHttpParams.getDefaultParams().setParameter(
            // "http.protocol.cookie-policy",
            // CookiePolicy.BROWSER_COMPATIBILITY);

            // StringEntity entity = new
            // StringEntity("{\"product_id\":\"9313\"}",
            // "utf-8");
            // entity.setContentType("application/x-www-form-urlencoded;
            // charset=UTF-8");
            // entity.setContentEncoding("utf-8");
            // request.setEntity(entity);

            CloseableHttpResponse response = client.execute(request);
            try {
                int status = response.getStatusLine().getStatusCode();

                log.debug("Http status:" + status);
                if (status == HttpStatus.SC_OK) {
                    // 返回响应数据
                    // return EntityUtils.toString(response.getEntity());
                    return getHtml(response.getEntity(), charset);
                } else {
                    return null;
                }
            } finally {
                response.close();
            }
        } finally {
            client.close();
        }
    }

    public static String get302Url(String url, boolean isMulti)
            throws ClientProtocolException, IOException {
        return get302Url(url, isMulti, null);
    }

    /**
     * 302连续跳转，获取真实连接
     *
     * @param url
     * @param isMulti true，连续到底
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String get302Url(String url, boolean isMulti,
                                   String cookieInfo) throws ClientProtocolException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpGet request = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    // 禁用自动转向
                    .setRedirectsEnabled(false)
                    .setCircularRedirectsAllowed(false)
                    .setConnectionRequestTimeout(timeOut)
                    .setConnectTimeout(timeOut).setSocketTimeout(timeOut)
                    .build();
            request.setConfig(requestConfig);

            if (null != cookieInfo)
                request.addHeader("Cookie", cookieInfo);
            System.out.println(url);
            CloseableHttpResponse response = client.execute(request);
            try {
                int status = response.getStatusLine().getStatusCode();
                log.debug("Http status:" + status + "&" + url);

                if (status == 302) {
                    url = response.getFirstHeader(HttpHeaders.LOCATION)
                            .getValue();

                    if (isMulti) {
                        return get302Url(url, isMulti);
                    } else {
                        // 因zhe800一次就能拿到目标url，不再继续处理
                        return url;
                    }
                } else if (status == 301) {
                    // 淘宝--跳天猫的情况
                    url = response.getFirstHeader(HttpHeaders.LOCATION)
                            .getValue();
                    return url;
                } else if (status == 200) {
                    return url;
                } else {
                    // 无url转向时，返回页面html信息
                    return getHtml(response.getEntity(), "UTF-8");
                }
            } finally {
                response.close();
            }
        } finally {
            client.close();
        }
    }

    /**
     * 返回html数据
     *
     * @param entity
     * @param charset 编码需正确
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    private static String getHtml(HttpEntity entity, String charset)
            throws IllegalStateException, IOException {
        StringBuilder result = new StringBuilder();
        if (entity != null) {
            InputStream instream = entity.getContent();
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(instream, charset));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result.append(line); // 此处若添加换行，将影响到解析+ "\n"
                }
                instream.close();
            } catch (IOException ex) {
                throw ex;
            } finally {
                instream.close();
            }
        }
        return result.toString();
    }

    /**
     * 随机生成ua
     *
     * @return
     */
    public static String getUserAget() {
        // 默认ua
        return "Mozilla/5.0 (Windows NT 6.1; rv:34.0) Gecko/20100101 Firefox/34.0";
    }

    public static void main(String[] args) {
        String referer = "https://list.tmall.com/search_product.htm?q=%CB%D5%C4%FE&type=p&vmarket=&spm=875.7931836%2FA.a2227oh.d100&from=mallfp..pc_1_searchbutton";
        try {
            String html = HttpUtil.get2("https://suning.tmall.com/", "tmall.com", HttpUtil.getUserAget(),
                    "text/html", referer, "GB2312", "");
            System.out.println(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
