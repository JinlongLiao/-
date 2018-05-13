package com.silu.web.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "PROVIDER-USER")
public interface SettingFeignInterface {
	@RequestMapping(value = "/updateNavs/{id}/{title}/{order}/{context}/{desc}/{target}/{url}", method = RequestMethod.GET)
	int updateNav(@PathVariable("id") int id, @PathVariable("title") String title, @PathVariable("order") int order,
			@PathVariable("context") String context, @PathVariable("desc") String desc,
			@PathVariable("target") String target, @PathVariable("url") String url);

}
