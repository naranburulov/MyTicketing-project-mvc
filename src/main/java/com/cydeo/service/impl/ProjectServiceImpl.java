package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Project;
import com.cydeo.entity.User;
import com.cydeo.enums.Status;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.ProjectRepository;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final MapperUtil mapperUtil;
    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;

    public ProjectServiceImpl(MapperUtil mapperUtil, ProjectRepository projectRepository, UserService userService, ProjectService projectService, TaskService taskService) {
        this.mapperUtil = mapperUtil;
        this.projectRepository = projectRepository;
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
    }


    @Override
    public ProjectDTO getByProjectCode(String code) {
        Project project = projectRepository.findByProjectCode(code);

        return mapperUtil.convert(project,ProjectDTO.class);
    }

    @Override
    public List<ProjectDTO> listAllProjects() {
        List<Project> projectList = projectRepository.findAll(Sort.by("projectCode"));
        return projectList.stream().map(project -> mapperUtil.convert(project, ProjectDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> listAllProjectDetails() {
        //get all the projects from DB,
        // assigned to the certain manager(user) who logged in the system

        //so far it is hard-coded, until we do the security to make it soft-coded
        UserDTO currentUserDto = userService.findByUserName("harold@manager.com");
        User user = mapperUtil.convert(currentUserDto,User.class);


        List<Project> list = projectRepository.findAllByAssignedManager(user);
        //now, since this list object is entity, but we need to return the dto-list,
        // we are missing to fields

        //so what we'll do is: we convert the list to dto, and manually
        // set those missing fields
        return list.stream().map(project -> {

            ProjectDTO obj = mapperUtil.convert(project,ProjectDTO.class);

            obj.setUnfinishedTaskCounts(taskService.totalNonCompletedTask(project.getProjectCode()));
            obj.setCompleteTaskCounts(taskService.totalCompletedTask(project.getProjectCode()));
            return obj;

        }).collect(Collectors.toList());
    }

    @Override
    public void save(ProjectDTO dto) {

        dto.setProjectStatus(Status.OPEN);

        projectRepository.save(mapperUtil.convert(dto,Project.class));

    }

    @Override
    public void update(ProjectDTO dto) {

        Project project = projectRepository.findByProjectCode(dto.getProjectCode());
        Project convertedProject = mapperUtil.convert(dto, Project.class);
        convertedProject.setId(project.getId());
        convertedProject.setProjectStatus(project.getProjectStatus());
        projectRepository.save(convertedProject);

    }

    @Override
    public void delete(String code) {
        Project project = projectRepository.findByProjectCode(code);
        project.setProjectStatus(Status.COMPLETE);
        project.setIsDeleted(true);     //since we don't really delete the obj from DB, but only UI


        //to avoid the certain bug @Column (unique=true)
        // in project entity's unique id, which is projectCode
        project.setProjectCode((project.getProjectCode()) + "-" + project.getId()); //SP03-4


        // and save the project object
        projectRepository.save(project);


        taskService.deleteByProject(mapperUtil.convert(project,ProjectDTO.class));


    }

    @Override
    public void complete(String projectCode) {

        Project project = projectRepository.findByProjectCode(projectCode);
        project.setProjectStatus(Status.COMPLETE);
        projectRepository.save(project);

        //fixing the bug with completing tasks of the completing project
        taskService.completeByProject(mapperUtil.convert(project,ProjectDTO.class));


    }

    @Override
    public List<ProjectDTO> listAllNonCompletedByAssignedManager(UserDTO assignedManager) {
        List<Project> projects = projectRepository
                .findAllByProjectStatusIsNotAndAssignedManager(Status.COMPLETE,mapperUtil.convert(assignedManager,User.class));
        return projects.stream().map(project -> mapperUtil.convert(project,ProjectDTO.class)).collect(Collectors.toList());
    }
}
