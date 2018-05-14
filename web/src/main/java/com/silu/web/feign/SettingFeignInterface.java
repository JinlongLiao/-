package com.silu.web.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PROVIDER-USER")
public interface SettingFeignInterface {
	// @RequestMapping(value =
	// "/updateNavs/{id}/{title}/{order}/{context}/{desc}/{target}/{url}", method =
	// RequestMethod.GET)
	@RequestMapping(value = "/updateNavs", method = RequestMethod.POST)
	int updateNav(@RequestParam("nav") String nav);

}
