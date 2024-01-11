package org.myapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class MyRestController {
    @GetMapping("/")
    public String getHello(){
        return "hello world";
    }

    @GetMapping("/users")
    public String getUsers(){
        return "hello world";
    }
}
