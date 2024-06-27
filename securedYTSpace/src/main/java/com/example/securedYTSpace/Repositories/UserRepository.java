package com.example.securedYTSpace.Repositories;

import com.example.securedYTSpace.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
