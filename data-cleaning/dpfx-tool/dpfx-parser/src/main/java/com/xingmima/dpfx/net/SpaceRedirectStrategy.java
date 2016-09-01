package com.xingmima.dpfx.net;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

import org.apache.http.ProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.util.TextUtils;

public class SpaceRedirectStrategy extends DefaultRedirectStrategy {

	private static final String[] REDIRECT_METHODS = new String[] {
			HttpGet.METHOD_NAME, HttpPost.METHOD_NAME, HttpHead.METHOD_NAME };

	@Override
	protected boolean isRedirectable(final String method) {
		for (final String m : REDIRECT_METHODS) {
			if (m.equalsIgnoreCase(method)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 重写createLocationURI 方法，过滤空格参数
	 */
	@Override
	protected URI createLocationURI(final String location)
			throws ProtocolException {
		try {
			final URIBuilder b = new URIBuilder(new URI(location.replaceAll(
					"\\s", "")).normalize());
			final String host = b.getHost();
			if (host != null) {
				b.setHost(host.toLowerCase(Locale.ENGLISH));
			}
			final String path = b.getPath();
			if (TextUtils.isEmpty(path)) {
				b.setPath("/");
			}
			return b.build();
		} catch (final URISyntaxException ex) {
			throw new ProtocolException("Invalid redirect URI: " + location, ex);
		}
	}

}
