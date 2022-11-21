package com.cydeo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication  //includes @Configuration
public class TicketingProjectMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketingProjectMvcApplication.class, args);
    }

    @Bean
    ModelMapper mapper(){
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        //PasswordEncoder interface includes boolean Matches method (for validation)
        return new BCryptPasswordEncoder();
    }

}
