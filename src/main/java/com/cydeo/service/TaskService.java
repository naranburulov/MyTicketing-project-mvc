package com.cydeo.service;

public interface TaskService {

    int totalNonCompletedTask(String projectCode);
    int totalCompletedTask(String projectCode);
}
