package com.silu.web.controller;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONArray;
import com.silu.web.entity.Navigation;

/**
 * @author 廖金龙 获取 全部导航条设置
 *
 */
@Controller
public class SettingsController {
	private static Logger logger = Logger.getLogger(SettingsController.class);
	@Autowired
	public RestTemplate temp;

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
	public String sortNavs(@RequestParam("dir") String direction, @RequestParam("sort") int order) {
//		System.out.println(order + "" + direction);
		return "settings";
	}
}
