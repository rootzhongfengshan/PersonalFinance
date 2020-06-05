package net.vv2.personal.finance.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("helloTest")
    public String Test() {
        return "我们是共产主义";
    }

}
