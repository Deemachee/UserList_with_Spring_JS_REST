package ru.kata.pp_3_1_4_js_rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.pp_3_1_4_js_rest.model.User;
import ru.kata.pp_3_1_4_js_rest.service.UserService;

import java.util.List;

@RestController
@RequestMapping ("/api/users")
@AllArgsConstructor
public class AdminRestController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity <User> getUserById (@PathVariable ("id") int id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PostMapping
    public ResponseEntity<User> addUser (@RequestBody User user) {
        return ResponseEntity.ok(userService.add(user));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<User> editUser (@RequestBody User user, @PathVariable ("id") int id) {
        return ResponseEntity.ok(userService.update(user, id));
    }

    @DeleteMapping("/{id}")
    public void deleteUser (@PathVariable ("id") int id) {
        userService.delete(id);
    }
}