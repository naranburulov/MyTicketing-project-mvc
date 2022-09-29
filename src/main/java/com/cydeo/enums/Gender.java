package com.cydeo.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public enum Gender {

    MALE("Male"), FEMALE ("Female");

    private String value;

//    Gender(String value) {
//        this.value = value;
//    }
//
//    public String getValue() {
//        return value;
//    }
}
