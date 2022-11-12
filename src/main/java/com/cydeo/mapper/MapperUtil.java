package com.cydeo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {

    private final ModelMapper modelMapper;

    public MapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

//    public <T> T convert(Object objectToBeConverted, T convertedObject){
//        return modelMapper.map(objectToBeConverted, (Type) convertedObject.getClass());
//    }         //the same thing as the one below


        public <T> T convert(Object objectToBeConverted, Class<T> convertedObject){
            return modelMapper.map(objectToBeConverted, convertedObject);
        }



    }
