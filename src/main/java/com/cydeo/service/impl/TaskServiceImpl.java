package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.service.TaskService;

import java.util.List;

public class TaskServiceImpl extends AbstractMapService<TaskDTO,Long> implements TaskService {

    @Override
    public TaskDTO save(TaskDTO user) {
        return null;
    }

    @Override
    public TaskDTO findById(Long userName) {
        return null;
    }

    @Override
    public List<TaskDTO> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(TaskDTO object) {

    }
}
