package ru.kata.pp_3_1_4_js_rest.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.pp_3_1_4_js_rest.model.User;
import ru.kata.pp_3_1_4_js_rest.repository.UserRepository;

import java.util.List;

@Service ("userServiceImp")
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImp.class);

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @Override
    @Transactional
    public User add(User user) {
        User exist = findByUsername(user.getEmail());
        if (exist != null) {
            log.info("User <<{}>> is already exist", user.getUsername());
            return null;
        } else {
            log.info("User <<{}>> was added to database", user.getUsername());
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
    }

    @Override
    @Transactional
    public User update(User user, int id) {
        User existingUser = userRepository.findById(id).orElseThrow();
        existingUser.setName(user.getName());
        existingUser.setLastName(user.getLastName());
        existingUser.setAge(user.getAge());
        existingUser.setEmail(user.getEmail());
        existingUser.setRoles(user.getRoles());
        return userRepository.save(existingUser);
    }

    @Override
    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(int id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь с email = " + email + " не найден");
        }
        return user;
    }
}
