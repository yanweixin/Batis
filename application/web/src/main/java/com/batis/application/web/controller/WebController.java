package com.batis.application.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String mainPage() {
        return "redirect:/index.html";
    }

    @GetMapping("/doLogin")
    public String doLogin() {
        return "123";
    }
}
