package ru.kata.pp_3_1_4_js_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.pp_3_1_4_js_rest.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
