package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }


    @Override
    public List<UserDTO> listAllUsers() {
        return userRepository.findAll().stream().map(userMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String userName) {

        User theUser = userRepository.findByUserName(userName);

        return userMapper.convertToDTO(theUser);

    }

    @Override
    public void save(UserDTO userDTO) {

        User user = userRepository.save(userMapper.convertToEntity(userDTO));

    }

    @Override
    public UserDTO update(UserDTO userDTO) {

        //find the certain User from DB:
        User theUser = userRepository.findByUserName(userDTO.getUserName());
        //convert userDTO to entity:
        User convertedUser = userMapper.convertToEntity(userDTO);
        //set TheUser's id to the convertedUser object:
        convertedUser.setId(theUser.getId());
        //save the updated user in the DB:
        userRepository.save(convertedUser);

        return findByUserName(userDTO.getUserName());



    }

    //temporary delete implementation
    @Override
    public void delete(String userName) {
        userRepository.deleteByUserName(userName);
    }


}
