package com.silu.web.controller;

import java.io.IOException;
import java.io.Writer;
import java.net.URLDecoder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.silu.web.index.IndexEntity.IndexEntity;

@Controller
public class IndexController {
	private static Logger logger = Logger.getLogger(IndexController.class);
	@Autowired
	public RestTemplate temp;

	@RequestMapping(value = "/")
	public String toIndex(Model model,HttpServletRequest req) {
		logger.debug("HelloWorld");
		String result = "";
		// 获取远端 数据
		result = temp.getForObject("http://provider-user/index/view", String.class);
		// 转换 为Java 对象
		IndexEntity entity = JSONObject.parseObject(result, IndexEntity.class);
		// 写入 Model
		model.addAttribute("index", entity);
		model.addAttribute("host",req.getServletContext().getAttribute("host"));
		logger.info(req.getServletContext().getAttribute("host"));
		return "index";
	}

	@RequestMapping(value = "/settings")
	public String toSettings(Model model) {
		logger.debug("HelloWorld");
		String result = "";
		// // 获取远端 数据
		// result = temp.getForObject("http://provider-user/index/view", String.class);
		// // 转换 为Java 对象
		// IndexEntity entity = JSONObject.parseObject(result, IndexEntity.class);
		// // 写入 Model
		// model.addAttribute("index", entity);
		return "settings";
	}

	@RequestMapping(value = "/infoService")
	public String toinfoService(Model model) {
		logger.debug("HelloWorld");
		String result = "";
		// // 获取远端 数据
		// result = temp.getForObject("http://provider-user/index/view", String.class);
		// // 转换 为Java 对象
		// IndexEntity entity = JSONObject.parseObject(result, IndexEntity.class);
		// // 写入 Model
		// model.addAttribute("index", entity);
		return "infoService";
	}

	@RequestMapping(value = "/last")
	public String toLast(Model model) {
		logger.debug("HelloWorld");
		String result = "";
		// // 获取远端 数据
		// result = temp.getForObject("http://provider-user/index/view", String.class);
		// // 转换 为Java 对象
		// IndexEntity entity = JSONObject.parseObject(result, IndexEntity.class);
		// // 写入 Model
		// model.addAttribute("index", entity);
		return "last";
	}

	// 导航条
	@RequestMapping(value = "/navigation")
	@ResponseBody
	public void navigation(HttpServletResponse res) throws IOException {
		logger.debug("HelloWorld");
		String result = "";
		// 获取远端 数据
		result = temp.getForObject("http://provider-user/getAllNavigations", String.class);
		// 写出去
		logger.debug(result);
		res.setCharacterEncoding("utf-8");
		res.setContentType("application/json;charset=utf-8");
		Writer w = res.getWriter();
		w.write(URLDecoder.decode(result, "utf-8"));
		w.close();

	}
}
