package ru.kata.pp_3_1_4_js_rest.service;

import ru.kata.pp_3_1_4_js_rest.model.Role;

import java.util.Set;

public interface RoleService {
    void add (Role role);
    Role getById (int id);
    Set<Role> getRoles(int [] ides);
    Set<Role> getAllRoles();
}
