package com.silu.web.config;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class WebConfig {
    private static Logger logger = Logger.getLogger(WebConfig.class);

//    @RequestMapping(value = "/")
//    public String toIndex() {
//        logger.debug("HelloWorld");
//        return "index";
//    }
}
