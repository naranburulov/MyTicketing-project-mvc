package com.cydeo.mapper;

import com.cydeo.dto.RoleDTO;
import com.cydeo.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    //converts DTO to Entity and vice versa
    // (ex: for Roles: DTO-Long vs Entity-String)

    private ModelMapper modelMapper;

    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Role convertToEntity(RoleDTO dto){
        return modelMapper.map(dto,Role.class);
    }

    //show Roles (scroll) in the UI form:
    public RoleDTO convertToDto(Role role){
        return modelMapper.map(role,RoleDTO.class);
    }


}
