package com.cydeo.enums;

public enum Gender {

    MALE("Male"), FEMALE ("Female");

    private final String value;

    public String getValue() {
        return value;
    }

    Gender(String value) {
        this.value = value;
    }
}
