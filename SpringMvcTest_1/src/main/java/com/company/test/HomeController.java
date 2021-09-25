package com.company.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/join/form")
    public String join(Member member){
        return "join/form";
    }

}
