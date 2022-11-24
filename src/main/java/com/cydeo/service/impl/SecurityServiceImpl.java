package com.cydeo.service.impl;

import com.cydeo.entity.User;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.SecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final UserRepository userRepository;

    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //get the user from DB,
        // and convert to user spring understands by using UserPrincipal

        User user =userRepository.findByUserNameAndIsDeleted(username, false);

        if (user==null){
            throw new UsernameNotFoundException(username);
        }



        return new UserPrincipal(user);
    }
}
