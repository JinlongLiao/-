package com.silu.web.index.IndexEntity;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author 廖金龙 首页 Spring Cloud 介绍
 *
 */
public class IndexEntity {
	// 对Get 默认进行 UrlDecode
	private String title;
	private String context;

	public String getTitle() throws UnsupportedEncodingException {
		return URLDecoder.decode(title, "UTF-8");
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() throws UnsupportedEncodingException {
		return URLDecoder.decode(context, "UTF-8");
	}

	public void setContext(String context) {
		this.context = context;
	}

}
