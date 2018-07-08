package ru.pipDota2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.pipDota2.domain.User;
import ru.pipDota2.service.UserService;
import ru.pipDota2.web.forms.Error;
import ru.pipDota2.web.forms.Result;
import ru.pipDota2.web.forms.Success;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(final UserService userService){
        this.userService = userService;
    }

    @GetMapping("/get/user")
    public Result getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
            return new Success<>(user);
        } catch (Exception e){
            return new Error("You are not authorized");
        }
    }

    @GetMapping("/create/user")
    public Result createUser(@RequestParam("login") String login,
                             @RequestParam("password") String password){
        if (userService.createNewUser(login, password)){
            return new Success<String>("Вы успешно зарегестрированны!");
        } else {
            return new Error("Пользователь с таким логином уже существует");
        }

    }
}
