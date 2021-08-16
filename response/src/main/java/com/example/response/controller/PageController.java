package com.example.response.controller;

import com.example.response.dto.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageController {

    @RequestMapping("/main")
    public String main(){
        return "main.html";
    }

    //ResponseEntity

    @ResponseBody
    @GetMapping("/user")
    public User user(){
        User user = new User();
        user.setName("steve");
        user.setAddress("korea");

        return user;
    }

}
