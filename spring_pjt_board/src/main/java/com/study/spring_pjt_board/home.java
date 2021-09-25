package com.study.spring_pjt_board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class home {

    @RequestMapping("/")
    public String home(){
        return "index";
    }
}
