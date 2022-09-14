package ru.kata.pp_3_1_4_js_rest.configs;

import org.springframework.stereotype.Component;
import ru.kata.pp_3_1_4_js_rest.model.Role;
import ru.kata.pp_3_1_4_js_rest.model.User;
import ru.kata.pp_3_1_4_js_rest.service.RoleService;
import ru.kata.pp_3_1_4_js_rest.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class DataBaseInit {
    private final UserService userService;
    private final RoleService roleService;

    public DataBaseInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void startDB() {
        User user = new User("user", "user", 20, "user@gmail.com", "user");
        User admin = new User("admin", "admin", 30, "admin@gmail.com", "admin");
        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");
        roleService.add(userRole);
        roleService.add(adminRole);
        user.setRoles(Set.of(userRole));
        admin.setRoles(Set.of(adminRole, userRole));
        userService.add(user);
        userService.add(admin);
    }
}