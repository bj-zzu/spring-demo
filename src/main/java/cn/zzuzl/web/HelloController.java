package cn.zzuzl.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/9/10.
 */
@Controller
@RequestMapping
public class HelloController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/")
    public String index() {
        logger.info("index");
        return "index";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        logger.info("hello");
        return "HelloWorld";
    }

    @RequestMapping("/redirect")
    public String redirect() {
        logger.info("redirect");
        return "redirect:/";
    }
}
