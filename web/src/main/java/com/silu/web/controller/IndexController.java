package com.silu.web.controller;

import java.io.IOException;
import java.io.Writer;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.silu.web.entity.Main;
import com.silu.web.entity.Navigation;
import com.silu.web.index.IndexEntity.IndexEntity;
import com.silu.web.util.ConfigYaml;

@Controller
public class IndexController {
	private static Logger logger = Logger.getLogger(IndexController.class);
	@Autowired
	public RestTemplate temp;
	// 从配置文件获取相应的 配置信息
	@Value("${app.encoding}")
	public String encoding;
	@Value("${app.sorry}")
	public String sorry;
	@Value("${app.sorryDesc}")
	public String sorryDesc;
	@Value("${app.sorryTitle}")
	public String sorryTitle;
	@Value("${app.sorryUrl}")
	public String sorryUrl;
	// 微服务地址
	// @Value("${app.php.url}")
	// public String phpUrl;
	// @Value("${app.java.url}")
	// public String javaUrl;
	// @Value("${app.nodeJs.url}")
	// public String nodeJsUrl;
	// @Value("${app.php.Nme}")
	// public String phpNme;
	// @Value("${app.java.Nme}")
	// public String javaNme;
	// @Value("${app.nodeJs.Nme}")
	// public String nodeJsNme;
	// 抛弃原有Spring提供 使用自定义
	public String phpUrl = ConfigYaml.getPHP_URL();
	public String javaUrl = ConfigYaml.getJAVA_URL();
	public String nodeJsUrl = ConfigYaml.getNODEJS_URL();
	public String phpNme = ConfigYaml.getPHP_NAME();
	public String javaNme = ConfigYaml.getJAVA_NAME();
	public String nodeJsNme = ConfigYaml.getNODEJS_NAME();

	// 微服务 页面导向与 获取远端 信息
	@RequestMapping(value = "/index")
	public String toIndex(Model model, HttpServletRequest req) {
		logger.debug("HelloWorld");
		String result = "";
		// 获取远端 数据
		result = temp.getForObject(javaUrl + "/index/view", String.class);
		// 转换 为Java 对象
		IndexEntity entity = JSONObject.parseObject(result, IndexEntity.class);
		// 写入 Model
		model.addAttribute("index", entity);
		// model.addAttribute("host",req.getServletContext().getAttribute("host"));
		logger.info(req.getServletContext().getAttribute("host"));
		return "index";
	}

	// Java Eureka Client 封装完成的 客户端 获取远端 的注册 中心所有 注册微服务的信息
	@Autowired
	DiscoveryClient discoveryClient;

	// 2018/5/18 漏电保护器
	@HystrixCommand(fallbackMethod = "toMianError")
	// 201805 16 获取远端NODEjs 数据 首页的 巨幕信息
	@RequestMapping(value = "/")
	public String toMian(Model model, HttpServletRequest req) throws JSONException {
		// 获取远端 数据 (NOde )
		String result = temp.postForObject(nodeJsUrl + "/main", null, String.class);
		Gson gson = new Gson();

		JSONArray jsons = new JSONArray(result);
		Main main = gson.fromJson(jsons.getJSONObject(0).toString(), Main.class);
		List<ServiceInstance> serviceInstances = discoveryClient.getInstances(nodeJsNme);
		for (ServiceInstance serviceInstance : serviceInstances) {
			main.setM_bg_img(
					"http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/" + main.getM_bg_img());
			;
		}
		model.addAttribute("json", main);
		return "main";
	}

	// 首页 获取信息 出错时 的方法
	public String toMianError(Model model, HttpServletRequest req) throws JSONException {

		Main main = new Main();
		main.setM_bt_desc(sorryDesc);
		main.setM_bt_title(sorryTitle);
		main.setM_bt_url(sorryUrl);
		main.setM_title(sorryTitle);
		main.setM_content(sorryDesc);
		model.addAttribute("json", main);
		return "main";
	}

	// 2018/5/18 添加 断路 保护
	@HystrixCommand(fallbackMethod = "toSettingsError")
	// Java 关于 导航栏所有信息
	@RequestMapping(value = "/settings")
	public String toSettings(Model model) {
		logger.debug("HelloWorld");
		// // 获取远端 数据
		// result = temp.getForObject("http://provider-user/index/view", String.class);
		// // 转换 为Java 对象
		// IndexEntity entity = JSONObject.parseObject(result, IndexEntity.class);
		// // 写入 Model
		// model.addAttribute("index", entity);
		return "settings";
	}

	// 获取 远端 信息失败时候 回调方法
	public String toSettingsError(Model model) {
		logger.debug("HelloWorld");
		// // 获取远端 数据
		// result = temp.getForObject("http://provider-user/index/view", String.class);
		// // 转换 为Java 对象
		// IndexEntity entity = JSONObject.parseObject(result, IndexEntity.class);
		// // 写入 Model
		// model.addAttribute("index", entity);
		return "settings";
	}

	// 导向 微服务介绍 页面
	@RequestMapping(value = "/infoService")
	public String toinfoService(Model model) {
		logger.debug("HelloWorld");
		// // 获取远端 数据
		// result = temp.getForObject("http://provider-user/index/view", String.class);
		// // 转换 为Java 对象
		// IndexEntity entity = JSONObject.parseObject(result, IndexEntity.class);
		// // 写入 Model
		// model.addAttribute("index", entity);
		return "infoService";
	}

	// 导向 微服务介绍 页面
	@RequestMapping(value = "/getInfoService")
	public void infoService(HttpServletResponse res) throws IOException {
		logger.debug("HelloWorld");
		// // 获取远端 数据
		// result = temp.getForObject("http://provider-user/index/view", String.class);
		// // 转换 为Java 对象
		// IndexEntity entity = JSONObject.parseObject(result, IndexEntity.class);
		// // 写入 Model
		// model.addAttribute("index", entity);
		String result = temp.getForObject(phpUrl + "/utf8-php/php/getContent.php", String.class);
		res.setCharacterEncoding(encoding);
		res.setContentType("application/json");
		// System.out.println(result);
		res.getWriter().write(result);
	}

	// 总结页面导向
	@RequestMapping(value = "/last")
	public String toLast(Model model) {
		logger.debug("HelloWorld");
		// String result = "";
		// // 获取远端 数据
		// result = temp.getForObject("http://provider-user/index/view", String.class);
		// // 转换 为Java 对象
		// IndexEntity entity = JSONObject.parseObject(result, IndexEntity.class);
		// // 写入 Model
		// model.addAttribute("index", entity);
		return "last";
	}

	// 2018/5/18 漏电保护器
	// 获取导航条信息 在前端页面来基本显示
	@RequestMapping(value = "/navigation")
	@ResponseBody
	@HystrixCommand(fallbackMethod = "navigationError")
	public void navigation(HttpServletResponse res) throws IOException {
		logger.debug("HelloWorld");
		String result = "";
		// 获取远端 数据
		result = temp.getForObject(javaUrl + "/getAllNavigations", String.class);
		// 写出去
		logger.debug(result);
		res.setCharacterEncoding("utf-8");
		res.setContentType("application/json");
		Writer w = res.getWriter();
		w.write(URLDecoder.decode(result, encoding));
		w.close();

	}

	public void navigationError(HttpServletResponse res) throws Exception {
		logger.debug("HelloWorld");
		Navigation navigation1 = new Navigation();
		navigation1.setId(0);
		navigation1.setContext(sorry);
		navigation1.setDesc(sorryDesc);
		navigation1.setOrder(0);
		navigation1.setTarget("#");
		navigation1.setTitle(sorryTitle);
		navigation1.setUrl(sorryUrl);
		Navigation navigation2 = new Navigation();
		navigation2.setId(0);
		navigation2.setContext(sorry);
		navigation2.setDesc(sorryDesc);
		navigation2.setOrder(0);
		navigation2.setTarget("#");
		navigation2.setTitle(sorryTitle);
		navigation2.setUrl(sorryUrl);
		Navigation navigation3 = new Navigation();
		navigation3.setId(0);
		navigation3.setContext(sorry);
		navigation3.setDesc(sorryDesc);
		navigation3.setOrder(0);
		navigation3.setTarget("#");
		navigation3.setTitle(sorryTitle);
		navigation3.setUrl(sorryUrl);
		Navigation navigation4 = new Navigation();
		navigation4.setId(0);
		navigation4.setContext(sorry);
		navigation4.setDesc(sorryDesc);
		navigation4.setOrder(0);
		navigation4.setTarget("#");
		navigation4.setTitle(sorryTitle);
		navigation4.setUrl(sorryUrl);

		// 转换 JsonArray
		com.alibaba.fastjson.JSONArray jsonArray = new com.alibaba.fastjson.JSONArray();
		jsonArray.add(navigation1);
		jsonArray.add(navigation2);
		jsonArray.add(navigation3);
		jsonArray.add(navigation4);
		String result = jsonArray.toString();
		// 写出去
		// System.out.println(result);
		logger.debug(result);
		res.setCharacterEncoding("utf-8");
		res.setContentType("application/json");
		Writer w = res.getWriter();
		w.write(URLDecoder.decode(result, encoding));
		w.close();

	}

	@RequestMapping("/getInfoPhpContent")
	public void getPhpContentInfo(HttpServletResponse res, String id) throws IOException {
		String result = temp.getForObject(phpUrl + "/utf8-php/php/getContent.php?client=java&id=" + id, String.class);
		res.setCharacterEncoding(encoding);
		res.setContentType("text/html");
		if (result == null) {
			result = "<script>alert('小朋友不乖哦(≧∇≦)ﾉ(≧∇≦)ﾉ(≧∇≦)ﾉ(≧∇≦)ﾉ(≧∇≦)ﾉ(≧∇≦)ﾉ(≧∇≦)ﾉ 根本没有这条记录●﹏● ●﹏● ●﹏● ●﹏●');</script>";
		}
		// System.out.println("adsfdfsa" + new
		// String(org.bouncycastle.util.encoders.Base64.decode(result)));
		try {
			result = new String(org.bouncycastle.util.encoders.Base64.decode(result),encoding);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// 解码后 输出
		res.getWriter().write(result);
	}
	// public static void main(String[] args) throws IOException {
	// new IndexController().navigationError();
	// }
}
