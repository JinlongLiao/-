package com.silu.controller;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.silu.entity.Navigation;
import com.silu.repository.NavigationRepository;

/**
 * com.silu.controller
 * 
 * @author 廖金龙 2018年4月22日下午6:56:35 导航条 查询服务
 */
@RestController
public class NavigationController {

	@Autowired
	private NavigationRepository navigationRepository;

	// 查询全部
	@RequestMapping("/getAllNavigations")
	public List<Navigation> getAllNavigations() {
		List<Navigation> navigations = null;
		navigations = this.navigationRepository.findAll();
		return navigations;
	}
}
