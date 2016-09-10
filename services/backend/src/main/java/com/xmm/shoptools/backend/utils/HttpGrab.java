package com.xmm.shoptools.backend.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.net.ssl.SSLContext;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
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
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.cookie.MalformedCookieException;
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

/**
 * 网抓取类
 * 
 * @author Jems
 * @date 2014年12月15日下午1:23:21
 * @version 1.0
 * @see <a>https://hc.apache.org/httpcomponents-client-ga/examples.html</a>
 */
public class HttpGrab {

	private static final Logger log = LoggerFactory.getLogger(HttpGrab.class);

	private static Integer timeOut = 30000; // 20 秒超时

	// 上线修改
//	private static int RANDOMSEED = 2;

	// 加载user-agent定义
//	static Properties prop = new Properties();
//	static {
//		try {
//			prop.load(new InputStreamReader(HttpGrab.class.getClassLoader()
//					.getResourceAsStream("user-agent.properties"), "UTF-8"));
//		} catch (IOException e) {
//			log.error("error : " + e.getMessage());
//		}
//	}

	/**
	 * Get请求，用于lblm网页数据抓取,firefox提交请求时参数完整模拟
	 * 
	 * @param url
	 *            抓取的目标url
	 * @param host
	 *            如：list.taobao.com
	 * @param ua
	 *            如：Mozilla/5.0 (Windows NT 6.1; rv:33.0) Gecko/20100101
	 *            Firefox/33.0
	 * @param accept
	 *            如：application/json,或 html
	 * @param referer
	 *            页面来源(上一级url)，重要（淘宝页面不传会没有结果）
	 * @param charset
	 *            字符编码集，多为UTF-8, GBK
	 * @return 网页文本数据
	 * 
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
	 * @param url
	 *            抓取的目标url
	 * @param host
	 *            如：list.taobao.com
	 * @param ua
	 *            如：Mozilla/5.0 (Windows NT 6.1; rv:33.0) Gecko/20100101
	 *            Firefox/33.0
	 * @param accept
	 *            如：application/json,或 html
	 * @param referer
	 *            页面来源(上一级url)，重要（淘宝页面不传会没有结果）
	 * @param charset
	 *            字符编码集，多为UTF-8, GBK
	 * @return 网页文本数据
	 * 
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
				.<CookieSpecProvider> create()
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
	 * @param referer
	 *            上级页面url
	 * @param accept
	 * @param userAgent
	 * @param ajax
	 *            异步请求头
	 * @param parmas
	 * @param charset
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String post(String url, String host, String referer,
			String accept, String userAgent, boolean ajax,
			Map<String, String> parmas, String charset, String cookies)
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
			if (cookies != null && !"".equals(cookies))
				request.addHeader("Cookie", cookies);

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
	 * @param isMulti
	 *            true，连续到底
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
	 * @param charset
	 *            编码需正确
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
	 * 生成0~seed之间的随机数
	 * 
	 * @param seed
	 * @return
	 */
	public static Integer random(int seed) {
		Random r = new Random();
		return (r.nextInt() >>> 1) % seed;
	}

	/**
	 * 随机生成ua
	 * 
	 * @return
	 */
	public static String getUserAget() {
//		String value = (String) prop.get(random(RANDOMSEED));
//		if (StringUtils.isNotEmpty(value)) {
//			return value;
//		}
		// 默认ua
		return "Mozilla/5.0 (Windows NT 6.1; rv:34.0) Gecko/20100101 Firefox/34.0";
	}

	public static void main(String[] s)
			throws ClientProtocolException, IOException {
		// String res = HttpGrab
		// .get4("http://detail.tmall.com/item.htm?id=41746352496", "GBK");
		// System.out.println("tm:" + res);
		// String res = HttpGrab.get4(
		// "http://item.taobao.com/item.htm?ft=t&id=20692543367", "GBK");
		// System.out.println("tb:" + res);
		// String res = HttpGrab.get4(
		// "http://detail.tmall.hk/hk/item.htm?spm=a2231.7718719.9575656734.4.t80eli&id=40636096613",
		// "GBK");
		// System.out.println("hk:" + res);
		// HttpGrab.get302Url("http://detail.tmall.com/item.htm?id=41746352496",true,"");
		
		String url = "http://www.kuaidi100.com/query?id=1&type=zhongtong&postid=530463513915&valicode=&temp="+ Math.random();
		
		Long x = new Date().getTime()/1000;
		String ck = "Hm_lvt_22ea01af58ba2be0fec7c11b25e88e6c="+x+"; Hm_lpvt_22ea01af58ba2be0fec7c11b25e88e6c="+x;
		System.out.println(url);
		System.out.println(ck);
		System.out.println(get(url, "www.kuaidi100.com", getUserAget(), "", "", "utf-8", ck));
	}
}
