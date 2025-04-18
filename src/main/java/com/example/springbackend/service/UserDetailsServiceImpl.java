//package com.example.springbackend.service;
//
//import com.example.springbackend.model.User;
//import com.example.springbackend.model.UserPrincipal;
//import com.example.springbackend.repository.UserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final UserRepository userRepo;
//
//    public UserDetailsServiceImpl(UserRepository userRepo) {
//        this.userRepo = userRepo;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepo.findByName(username).orElseThrow(() ->
//                new RuntimeException("User not found with username: " + username));
//
//        return new UserPrincipal(user);
//    }
//}
