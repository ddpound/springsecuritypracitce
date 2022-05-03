package com.sp.fc.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = "/")
    public String index(){
        return "메인 인덱스입니다.";
    }
}
