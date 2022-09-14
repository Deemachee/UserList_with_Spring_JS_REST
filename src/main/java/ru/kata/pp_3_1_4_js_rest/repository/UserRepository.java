package ru.kata.pp_3_1_4_js_rest.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kata.pp_3_1_4_js_rest.model.User;

@Repository
public interface UserRepository extends JpaRepository <User, Integer> {
    @Query("from User e where e.email = :email")
    User getUserByEmail(@Param("email") String email);
}
