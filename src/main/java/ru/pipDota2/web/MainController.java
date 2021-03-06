package ru.pipDota2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping(value = {
            "/",
            "/heroes",
            "/items",
            "/articles",
            "/signIn",
            "/signUp",
            "/heroes/:id",
            "/items/:id"
    })
    public String getMainPage() {
        return "index";
    }
}
