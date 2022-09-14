package ru.kata.pp_3_1_4_js_rest.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.pp_3_1_4_js_rest.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    User add (User user);
    User update (User user, int id);
    void delete (int id);
    List<User> getAllUsers();
    User getById (int id);
    User findByUsername (String email);
}
