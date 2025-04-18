package com.example.springbackend.service;

import com.example.springbackend.model.User;
import com.example.springbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    private final PasswordEncoder passwordEncoder;
//
//    // ✅ Register a new user
//    public ResponseEntity<String> registerUser(User user) {
//        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("User with email already exists");
//        }
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRole("USER"); // Default role
//        userRepository.save(user);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
//    }

    // ✅ Get all users (useful for assigning tasks)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ Get user by ID
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    // ✅ Get user by Email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // ✅ Update user info (optional)
    public ResponseEntity<String> updateUser(String id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            userRepository.save(user);
            return ResponseEntity.ok("User updated successfully");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"));
    }

    // ✅ Delete user (optional, e.g., for admin)
    public ResponseEntity<String> deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
