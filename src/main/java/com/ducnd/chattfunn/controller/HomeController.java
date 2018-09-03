package com.ducnd.chattfunn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping(value = "/")
    public String home(){
        return "well come to Chatfunn!";
    }
}
