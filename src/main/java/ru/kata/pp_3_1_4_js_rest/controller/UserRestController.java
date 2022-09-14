package ru.kata.pp_3_1_4_js_rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.pp_3_1_4_js_rest.model.User;
import ru.kata.pp_3_1_4_js_rest.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping ("/api/user")
@AllArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping
    public User getAuthorisedUser (Principal principal) {
        return userService.findByUsername(principal.getName());
    }
}
