//package com.example.springbackend.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        return http.csrf(customizer -> customizer.disable()).
////                authorizeHttpRequests(request -> request.anyRequest().authenticated()).
////                httpBasic(Customizer.withDefaults()).
////                sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
////    }
////
//
//    //navinreddy
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        return http.csrf(customizer -> customizer.disable()).
//                authorizeHttpRequests(request -> request.requestMatchers("/users/register").permitAll().anyRequest().authenticated()).
//                httpBasic(Customizer.withDefaults()).
//                sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
//                build();
//    }
//
//
////    @Bean
////    public UserDetailsService userDetailsService() {
////
////        UserDetails user1 = User
////                .withDefaultPasswordEncoder()
////                .username("kiran")
////                .password("k@123")
////                .roles("USER")
////                .build();
////
////        UserDetails user2 = User
////                .withDefaultPasswordEncoder()
////                .username("harsh")
////                .password("h@123")
////                .roles("ADMIN")
////                .build();
////        return new InMemoryUserDetailsManager(user1, user2);
////    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
//        provider.setUserDetailsService(userDetailsService);
//
//        return provider;
//    }
//}
