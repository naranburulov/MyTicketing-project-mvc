package com.cydeo.service;

import com.cydeo.dto.UserDTO;

import java.util.List;

public interface UserService extends CrudService<UserDTO, String>{

    UserDTO save(UserDTO user);

    UserDTO findById(String userName);

    List<UserDTO> findAll();

    void deleteById(String userName);


}
