package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Project;
import com.cydeo.entity.Task;
import com.cydeo.entity.User;
import com.cydeo.enums.Status;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.TaskRepository;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final MapperUtil mapperUtil;
    private final UserService userService;


    public TaskServiceImpl(TaskRepository taskRepository, MapperUtil mapperUtil, UserService userService) {
        this.taskRepository = taskRepository;
        this.mapperUtil = mapperUtil;
        this.userService = userService;
    }


    @Override
    public List<TaskDTO> listAllTasks() {
        return taskRepository.findAll().stream().map(task -> mapperUtil.convert(task, TaskDTO.class)).collect(Collectors.toList());
    }


    @Override
    public void save(TaskDTO dto) {
        dto.setTaskStatus(Status.OPEN);
        dto.setAssignedDate(LocalDate.now());

        taskRepository.save(mapperUtil.convert(dto,Task.class));
    }

    @Override
    public void update(TaskDTO dto) {

        Optional<Task> task = taskRepository.findById(dto.getId());
        Task convertedTask = mapperUtil.convert(dto, Task.class);

        if (task.isPresent()){
            convertedTask.setTaskStatus(dto.getTaskStatus() == null ? task.get().getTaskStatus() : dto.getTaskStatus());
            convertedTask.setAssignedDate(task.get().getAssignedDate());
            taskRepository.save(convertedTask);
        }


    }

    @Override
    public void delete(Long id) {

        Optional<Task> foundTask = taskRepository.findById(id);

        if (foundTask.isPresent()){
            foundTask.get().setIsDeleted(true);
            taskRepository.save(foundTask.get());
        }



    }

    @Override
    public TaskDTO findById(Long id) {

        Optional<Task> task = taskRepository.findById(id);

        if (task.isPresent()){
            return mapperUtil.convert(task,TaskDTO.class);
        }

        return null;
    }

    @Override
    public int totalNonCompletedTask(String projectCode) {
        return taskRepository.totalNonCompletedTasks(projectCode);
    }

    @Override
    public int totalCompletedTask(String projectCode) {
        return taskRepository.totalCompletedTasks(projectCode);
    }

    @Override
    public void deleteByProject(ProjectDTO convertToDto) {
        Project project = mapperUtil.convert(convertToDto,Project.class);
        List<Task> tasks = taskRepository.findAllByProject(project);
        tasks.forEach(task -> delete(task.getId()));
    }

    @Override
    public void completeByProject(ProjectDTO convertToDto) {
        Project project = mapperUtil.convert(convertToDto,Project.class);
        List<Task> tasks = taskRepository.findAllByProject(project);
        tasks.stream().map(task -> mapperUtil.convert(task,TaskDTO.class)).forEach(taskDTO -> {
            taskDTO.setTaskStatus(Status.COMPLETE);
            update(taskDTO);
        });
    }

    @Override
    public List<TaskDTO> listAllTasksByStatusIsNot(Status status) {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        UserDTO loggedInUser = userService.findByUserName(userName);
        List<Task> tasks = taskRepository.findAllByTaskStatusIsNotAndAssignedEmployee(status, mapperUtil.convert(loggedInUser, User.class));

        return tasks.stream().map(task -> mapperUtil.convert(task, TaskDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> listAllTasksByStatus(Status status) {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        UserDTO loggedInUser = userService.findByUserName(userName);
        List<Task> tasks = taskRepository.findAllByTaskStatusAndAssignedEmployee(status, mapperUtil.convert(loggedInUser, User.class));

        return tasks.stream().map(task -> mapperUtil.convert(task, TaskDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> listAllNonCompletedByAssignedEmployee(UserDTO assignedEmployee) {
        List<Task> tasks = taskRepository
                .findAllByTaskStatusIsNotAndAssignedEmployee(Status.COMPLETE, mapperUtil.convert(assignedEmployee, User.class));
        return tasks.stream().map(task -> mapperUtil.convert(task, TaskDTO.class)).collect(Collectors.toList());
    }
}
