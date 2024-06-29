package com.app.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/auth")
@PreAuthorize("denyAll()") // Por defecto niega el accceso a todos los endpoint
public class TestAuthController {

    @RequestMapping("/hello")
    @PreAuthorize("permitAll()") // Permite el acceso a todos los usuarios
    public String hello(){
        return "hello World";
    }

    @RequestMapping("/hello-secured")
    @PreAuthorize("hasAuthority('READ')") // Authoriza acceso segun permisos
    public String helloSecured(){
        return "hello World Secured";
    }

    @GetMapping("/hello-secured2")
    @PreAuthorize("hasAuthority('CREATE')") // Authoriza acceso segun permisos
    public String helloSecured2() {
        return "hello World Secured 2";
    }
    
    
}
