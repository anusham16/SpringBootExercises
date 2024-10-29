package com.example.demo.securityConfiguration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class DemoSecurityConfiguration {

//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
//        UserDetails user= User.builder()
//                .username("Anusha")
//                .password("{noop}1234")
//                .roles("Employee")
//                .build();
//
//        UserDetails user1= User.builder()
//                .username("Abhi")
//                .password("{noop}1234")
//                .roles("Manager")
//                .build();
//
//        UserDetails user2= User.builder()
//                .username("chinni")
//                .password("{noop}1234")
//                .roles("Employee","HR")
//                .build();
//
//
//        return new InMemoryUserDetailsManager(user,user1,user2);
//    }
//
    @Bean

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/api/students/**").hasRole("USER")
                .requestMatchers(HttpMethod.POST, "/api/students").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/students/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/students/**").hasRole("ADMIN")

                .anyRequest().permitAll()
        );
        http.httpBasic(withDefaults());
        http.csrf(csrf->csrf.disable());
        return http.build();
    }


    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }
}
