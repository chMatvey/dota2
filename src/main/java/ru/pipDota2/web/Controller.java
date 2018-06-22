package ru.pipDota2.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/")
    public String getHello(){
        return "Hello World";
    }

    @GetMapping("/lol")
    public String getLol(){
        return "lol";
    }
}
