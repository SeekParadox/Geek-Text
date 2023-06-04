//package com.geektext.backend.security;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//
//    @Autowired
//    CustomUserDetailsService customUserDetailsService;
//    @Bean
//    BCryptPasswordEncoder passwordEncoder() {
//       return new BCryptPasswordEncoder(16);
//    }
//
//    @Bean
//    CustomUserDetailsService customUserDetailsService() {
//        return this.customUserDetailsService;
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(customUserDetailsService());
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
//
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests()
//                    .requestMatchers(HttpMethod.POST, "/register/**")
//                        .permitAll()
//                    .requestMatchers("/api/books/**", "/", "home", "/register/**")
//                        .permitAll()
//                    .requestMatchers(HttpMethod.PUT, "/api/books/updatebypublisher")
//                        .permitAll()
//                        .anyRequest()
//                        .authenticated()
//                    .and()
//                    .formLogin()
//                        .loginPage("/login")
//                        .permitAll()
//                    .and()
//                    .rememberMe()
//                        .userDetailsService(new CustomUserDetailsService())
//                    .and()
//                    .logout()
//                        .logoutUrl("/logout")
//                    .deleteCookies("JSESSIONID", "remember-me");
//
//        return http.build();
//    }
//
//    @Service
//    static class CustomUserDetailsService implements UserDetailsService {
//        @Autowired
//        UserRepository repository;
//
//        @Override
//        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//            List<UserEntity> users = repository.findAllByUsername(username);
//            if (users.isEmpty()) throw new UsernameNotFoundException("Username not found");
//
//            UserEntity matchedUser = users.get(0);
//            Set<GrantedAuthority> grantedAuthorities = new HashSet<>(matchedUser
//                    .getAuthorities());
//
//            return new User(matchedUser.getUsername(), matchedUser.getPassword(), grantedAuthorities);
//        }
//    }
//}
