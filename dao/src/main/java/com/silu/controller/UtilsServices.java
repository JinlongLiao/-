package com.silu.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.silu.controller
 * 
 * @author 廖金龙 2018年4月22日下午6:18:46
 */
@RestController
public class UtilsServices {
	// 提供 Spring Cloud 介绍 数据
	// 暂时 硬编码 为防止 中文在 teminal 中 出现乱码 进行UrlEncode
	@GetMapping(value = "/index/view")
	public String indexView() throws UnsupportedEncodingException {
		String context = "Spring Cloud是一系列框架的有序集合。它利用Spring Boot的开发便利性巧妙地简化了分布式系统基础设施的开发，如服务发现注册、配置中心、消息总线、负载均衡、断路器、数据监控等，都可以用Spring Boot的开发风格做到一键启动和部署。Spring并没有重复制造轮子，它只是将目前各家公司开发的比较成熟、经得起实际考验的服务框架组合起来，通过Spring Boot风格进行再封装屏蔽掉了复杂的配置和实现原理，最终给开发者留出了一套简单易懂、易部署和易维护的分布式系统开发工具包。";
		String title = "Spring Cloud 微服务";
		title = URLEncoder.encode(title, "UTF-8");
		context = URLEncoder.encode(context, "UTF-8");
		// System.out.println(URLDecoder.decode(context, "UTF-8"));
		String result = "{\"title\":\"" + title + "\",\"context\":\"" + context + "\"}";
		return result;
	}

	// public static void main(String[] args) {
	// try {
	// System.out.println(new UtilsServices().indexView());
	// ;
	// } catch (UnsupportedEncodingException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
}
