package com.silu.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.silu.web.entity.CommonMessage;
import com.silu.web.entity.Navigation;
import com.silu.web.feign.SettingFeignInterface;

/**
 * @author 廖金龙 获取 全部导航条设置
 *
 */
@Controller
public class SettingsController {
	private static Logger logger = Logger.getLogger(SettingsController.class);
	@Autowired
	public RestTemplate temp;
	@Value("${app.encoding}")
	public String encoding;
	@Autowired
	private SettingFeignInterface settingFeign;

	// 导航条
	/**
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/GetAllNavs")
	public String navigation(Model model) throws IOException {
		logger.debug("HelloWorld");
		String result = "";
		// 获取远端 数据
		result = temp.getForObject("http://provider-user/getAllNavigations", String.class);
		// 写出去
		List<Navigation> navis = JSONArray.parseArray(result, Navigation.class);
		if (navis != null)
			// for (int i = 0; i < navis.size(); i++) {
			// System.out.println(navis.get(i).getTitle());
			// }
			model.addAttribute("navis", navis);
		return "settings";
	}

	@RequestMapping(value = "/sortNav")
	public void sortNavs(@RequestParam("dir") String direction, @RequestParam("sort") int order,
			HttpServletResponse response) throws IOException {
		// System.out.println(order + "" + direction);
		String result = "";
		// 获取远端 数据
		String url = "http://provider-user/sortNav/" + direction + "/" + order;
		result = temp.getForObject(url, String.class);
		// CommonMessage message = JSONObject.parseObject(result,CommonMessage.class);
		response.setContentType("ContentType:application/json");
		response.getWriter().write(result);
	}

	@RequestMapping(value = "/getNavByOrder")
	public void getNavByOrder(int order, HttpServletResponse response) {
		Navigation navigation = temp.getForObject("http://provider-user/getNavByOrder/" + order, Navigation.class);
		response.setContentType("ContentType:application/json");
		response.setCharacterEncoding(encoding);
		try {
			response.getWriter().write(JSONObject.toJSON(navigation).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * @date 2018、5、13 更新Nav
	 */
	@RequestMapping("/updateNavs")
	@ResponseBody
	public CommonMessage updateNav(Navigation navigation) {
		int result = settingFeign.updateNav(JSONObject.toJSON(navigation).toString());

		CommonMessage message = new CommonMessage();
		if (result > 0) {
			message.setResult(true);
			message.setMessage("SUCCESS");
		}
		return message;
	}
}
