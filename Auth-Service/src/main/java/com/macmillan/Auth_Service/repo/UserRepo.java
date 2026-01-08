package com.macmillan.Auth_Service.repo;

import com.macmillan.Auth_Service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);

    User getUserByUsername(String username);
}
