package com.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class TestAuthController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello Wold";
    }

    @RequestMapping("/hello-secured")
    public String helloSecured(){
        return "hello Wold Secured";
    }
    
}
