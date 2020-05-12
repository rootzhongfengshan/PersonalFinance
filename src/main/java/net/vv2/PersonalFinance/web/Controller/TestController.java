package net.vv2.PersonalFinance.web.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("helloTest")
    public String Test() {
        return "我们是共产主义";
    }

}
