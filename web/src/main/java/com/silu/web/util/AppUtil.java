package com.silu.web.util;

import java.util.Map;

import org.springframework.stereotype.Controller;

/**
 * @author liaojl
 * @date 2018年5月19日下午6:35:16
 */
@Controller
public class AppUtil {

	private Map<Object, Object> app;

	public Map<Object, Object> getApp() {
		return app;
	}

	public void setApp(Map<Object, Object> app) {
		this.app = app;
	}

}
