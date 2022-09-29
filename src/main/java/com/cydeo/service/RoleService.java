package com.cydeo.service;

import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Role;

import java.util.List;

public interface RoleService extends CrudService <RoleDTO, Long>{

    //services (business logics) are dynamic, we can always come back here, add/remove/change things here

    RoleDTO save(RoleDTO user);

    RoleDTO findById(Long id);

    List<RoleDTO> findAll();

    void deleteById(Long id);

}
