package com.cydeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project")
public class ProjectController {
//
//    private final UserService userService;
//    private final ProjectService projectService;
//
//    public ProjectController(UserService userService, ProjectService projectService) {
//        this.userService = userService;
//        this.projectService = projectService;
//    }
//
//    @GetMapping("/create")
//    public String createProject(Model model){
//
//        model.addAttribute("project",new ProjectDTO());
//
//        model.addAttribute("managers",userService.findManagers());
//
//        model.addAttribute("projects",projectService.findAll());
//
//        return "/project/create";
//    }
//
//    @PostMapping("/create")
//    public String insertProject(@ModelAttribute("project") ProjectDTO project){
//
//
//        projectService.save(project);
//
//        return "redirect:/project/create";
//    }
//
//    @GetMapping("/delete/{projectCode}")
//    public String deleteProject(@PathVariable("projectCode") String projectCode){
//
//        projectService.deleteById(projectCode);
//
//        return "redirect:/project/create";
//
//    }
//
//    @GetMapping("/complete/{projectCode}")
//    public String completeProject(@PathVariable("projectCode") String projectCode) {
//
//        projectService.complete(projectService.findById(projectCode));
//
//        return "redirect:/project/create";
//    }
//
//    @GetMapping("/manager/complete/{projectCode}")
//    public String managerCompleteProject(@PathVariable String projectCode) {
//
//        projectService.complete(projectService.findById(projectCode));
//
//        return "redirect:/project/manager/project-status";
//    }
//
//    @GetMapping("/update/{projectCode}")
//    public String editProject(@PathVariable("projectCode") String projectCode, Model model){
//
//        model.addAttribute("project", projectService.findById(projectCode));
//        model.addAttribute("managers",userService.findAll());
//        model.addAttribute("projects",projectService.findAll());
//
//        return "/project/update";
//    }
//
//    @PostMapping("/update")
//    public String updateProject(@ModelAttribute("project") ProjectDTO project){
//
//        projectService.update(project);
//
//        return "redirect:/project/create";
//    }
//
//    @GetMapping("/manager/project-status")
//    public String getProjectByManager(Model model){
//
//        UserDTO manager = userService.findById("john@cydeo.com");
//        //now I don't have the security yet, that is why it is hardcoded
//        //later it will come from login info
//
//
//        List<ProjectDTO> projects = projectService.getCountedListOfProjectDTO(manager);
//
//        model.addAttribute("projects", projects);
//
//        return "/manager/project-status";
//    }
//
//
//
//
//
//

}
