package com.silu.dao.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.silu.dao.entity.Navigation;
import com.silu.dao.jdbcTemple.NavigationDao;
import com.silu.dao.repository.NavigationRepository;

/**
 * com.silu.controller
 * 
 * @author 廖金龙 2018年4月22日下午6:56:35 导航条 查询服务
 */
@RestController
public class NavigationController {

	@Autowired
	private NavigationRepository navigationRepository;
	@Autowired
	private NavigationDao navigationDao;

	// 查询全部
	@RequestMapping("/getAllNavigations")
	public List<Navigation> getAllNavigations() {
		List<Navigation> navigations = null;
		navigations = this.navigationRepository.findAll();
		// 页面排序
		navigations.sort(new Comparator<Navigation>() {

			@Override
			public int compare(Navigation o1, Navigation o2) {
				// TODO Auto-generated method stub
				return o1.getOrder() - o2.getOrder();
			}
		});
		return navigations;
	}

	@RequestMapping("/sortNav/{dir}/{order}")
	// 查询全部
	public List<Map<Object, Object>> doSort(@PathVariable String dir, @PathVariable int order) {
		List<Map<Object, Object>> results = new ArrayList<>();
		HashMap<Object, Object> map = new HashMap<>();
		try {
			navigationDao.doSort(dir, order);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			map.put("RESULT", false);
			map.put("MESSAGE", e.getMessage());
			results.add(map);
			e.printStackTrace();
			return results;
		}
		map.put("RESULT", true);
		map.put("MESSAGE", "");
		results.add(map);
		return results;
	}

	@RequestMapping("/getNavByOrder/{order}")
	public Navigation getNavByOrder(@PathVariable int order) {
		Navigation navigation = navigationRepository.findByOrder(order);
		return navigation;

	}

	/// * @RequestMapping(value =
	/// "/updateNavs/{id}/{title}/{order}/{context}/{desc}/{target}/{url}", method =
	/// RequestMethod.GET)
	// public int updateNavs(Navigation navigation) {
	// return navigationDao.updatNav(navigation);
	//
	// }*/

	@RequestMapping(value = "/updateNavs/{id}/{title}/{order}/{context}/{desc}/{target}/{url}", method = RequestMethod.GET)
	public int updateNavs(@PathVariable("id") int id, @PathVariable("title") String title,
			@PathVariable("order") int order, @PathVariable("context") String context,
			@PathVariable("desc") String desc, @PathVariable("target") String target, @PathVariable("url") String url)
			throws UnsupportedEncodingException {
		Navigation navigation = new Navigation();
		navigation.setId(id);
		navigation.setContext(URLDecoder.decode(context, "UTF-8"));
		navigation.setOrder(order);
		navigation.setDesc(URLDecoder.decode(desc, "UTF-8"));
		navigation.setOrder(order);
		navigation.setTitle(URLDecoder.decode(title, "UTF-8"));
		navigation.setTarget(target);
		return navigationDao.updatNav(navigation);

	}
}
