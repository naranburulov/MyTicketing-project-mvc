package com.cydeo.converter;

import com.cydeo.dto.RoleDTO;
import com.cydeo.service.RoleService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/*Converter is an interface describing a Java class that can perform
Object-to-String and String-to-Object conversions between model data objects
and a String representation of those objects that is suitable for rendering.
*/

@Component
@ConfigurationPropertiesBinding
public class RoleDtoConverter implements Converter<String,RoleDTO>{

    RoleService roleService;

    public RoleDtoConverter(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public RoleDTO convert(String source) {


        if (source == null || source.equals("")) {  //  Select  -> ""
            return null;
        }

        return roleService.findById(Long.parseLong(source));
    }
}

/*
Before, I was getting this error in the browser,4
when I was attempting to save my objects.
(problem with selecting roles as value (1/2/3) and converting into String,
to save into saved rows)

Error(type=Bad Request, status=400):
Cannot convert value of type 'java.lang.String'
to required type 'com.cydeo.dto.RoleDTO' for property
 'role': no matching editors or conversion strategy found
 */




