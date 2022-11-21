package com.cydeo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {

    //    hard-coded way: OVERRIDES Spring created object :
//    @Bean         //manually create a User bean that will be used to Authenticate, and give authorization to a user.
//    public UserDetailsService userDetailsService(PasswordEncoder encoder){
//
//              //manually create a list of users  in Security Config and add each user to the list.
//        List<UserDetails> userList = new ArrayList<>();
//        userList.add(
//                //to encode our password when passing the User password
//                new User("mike", encoder.encode("password"),
//                        Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")))
//        );
//        userList.add(
//                new User("ozzy", encoder.encode("password"),
//                        Arrays.asList(new SimpleGrantedAuthority("ROLE_MANAGER")))
//        );
//
//        return new InMemoryUserDetailsManager(userList);
//        //This saves to memory rather than DB
//    }





}
