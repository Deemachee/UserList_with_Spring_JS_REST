package ru.kata.pp_3_1_4_js_rest.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.pp_3_1_4_js_rest.model.Role;
import ru.kata.pp_3_1_4_js_rest.repository.RoleRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class RoleServiceImp implements RoleService{

    private static final Logger log = LoggerFactory.getLogger(RoleServiceImp.class);

    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public void add(Role role) {
        if (role.getName().equals("ROLE_ADMIN") || role.getName().equals("ROLE_USER")) {
            if (getAllRoles().stream().noneMatch(r -> r.getName().equals(role.getName()))) {
                roleRepository.save(role);
                log.info("Role <<{}>> was added to database", role.getName());
            } else {
                log.info("Role <<{}>> was already exists", role.getName());
            }
        } else {
            log.info("Can't add role <<{}>>, it can be only ROLE_ADMIN or ROLE_USER", role.getName());
        }
        roleRepository.save(role);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getById(int id) {
        return roleRepository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Role> getAllRoles() {
        return new HashSet<>(roleRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Role> getRoles(int [] ides) {
        Set <Role> roles = new HashSet<>();
        for (int ide : ides) {
            roles.add(roleRepository.getById(ide));
        }
        return roles;
    }
}
