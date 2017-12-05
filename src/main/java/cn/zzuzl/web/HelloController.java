package cn.zzuzl.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping("/login")
    public String login(String username, String password, HttpServletResponse response) {
        response.addCookie(new Cookie("username", username));
        response.addCookie(new Cookie("error", "password error"));
        return "redirect:/userlogin.html";
    }
}
