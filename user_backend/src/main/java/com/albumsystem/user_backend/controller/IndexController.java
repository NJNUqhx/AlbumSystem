package com.albumsystem.user_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//使其变成链接对应函数
public class IndexController {

    @RequestMapping("/")
    public String Index(){
        return "pk/index.html";
    }

}
