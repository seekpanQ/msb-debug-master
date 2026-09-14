package com.mashibing.debug.memory3.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;


/**
 * @author sunzhiqiang
 * @date 2021/12/02 00:08:22
 */
@RestController
public class OomController {

    @RequestMapping("/oom")
    public void oom() {
        String str = "asdasd";
        while (true) {
            str += str + new Random().toString();
        }
    }

    @RequestMapping("/test")
    public void test(HttpServletRequest request) {
        request.getRequestURL();
    }
}
