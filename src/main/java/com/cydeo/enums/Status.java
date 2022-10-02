package com.cydeo.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {

    OPEN("Open"), IN_PROGRESS("In Progress"), COMPLETE("Completed");


    private final String value;





}
