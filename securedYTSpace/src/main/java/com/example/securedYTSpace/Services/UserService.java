package com.example.securedYTSpace.Services;

import com.example.securedYTSpace.Entities.User;
import com.example.securedYTSpace.Entities.UserRole;
import com.example.securedYTSpace.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User signup(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode password before saving
        Set<UserRole> roles = new HashSet<>(); // Create a new set for roles
        roles.add(UserRole.UPLOADER); // Add default role for new users
        user.setRoles(roles); // Set roles for user
        return userRepository.save(user); // Save user to database
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username); // Find user by username
    }
}
