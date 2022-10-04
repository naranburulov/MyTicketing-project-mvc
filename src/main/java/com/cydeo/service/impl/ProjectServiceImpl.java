package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO, String> implements ProjectService {
    @Override
    public ProjectDTO save(ProjectDTO project) {

        if (project.getProjectStatus()==null){
            project.setProjectStatus(Status.OPEN);
        }


        return super.save(project.getProjectCode(), project);
    }

    @Override
    public ProjectDTO findById(String projectCode) {
        return super.findById(projectCode);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(String projectCode) {
        super.deleteById(projectCode);
    }

    @Override
    public void update(ProjectDTO object) {
        //problem was that, when we save the project, it saved it with no status (meaning status was null)
        if(object.getProjectStatus()==null){
            object.setProjectStatus(findById(object.getProjectCode()).getProjectStatus());
        }   //grabbing the existing status and setting it as the status, instead of null

        super.update(object.getProjectCode(),object);
    }


    @Override
    public void complete(ProjectDTO project) {

        project.setProjectStatus(Status.COMPLETE);

    }
}
