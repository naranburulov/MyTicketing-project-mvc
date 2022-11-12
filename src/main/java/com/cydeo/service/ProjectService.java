package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {

    List<ProjectDTO> listAllProjects();

    List<ProjectDTO> listAllProjectDetails();

    void save(ProjectDTO project);
}
