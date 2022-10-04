package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final RoleService roleService;
    private final UserService userService;

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createUser(Model model){

        model.addAttribute("user",new UserDTO());

        model.addAttribute("roles",roleService.findAll());

        model.addAttribute("users",userService.findAll());

        return "/user/create";
    }




    @PostMapping("/create")
    public String insertUser(@ModelAttribute("user") UserDTO user){

        userService.save(user);

        return "redirect:/user/create";

    }



    @GetMapping("/update/{userName}")
    public String editUser(@PathVariable("userName") String userName, Model model){

        model.addAttribute("user", userService.findById(userName));
        model.addAttribute("roles",roleService.findAll());
        model.addAttribute("users",userService.findAll());

        return "/user/update";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") UserDTO user){

        userService.update(user);

        return "redirect:/user/create";
    }

    @GetMapping("/delete/{userName}")
    public String deleteUser(@PathVariable("userName") String userName){

        userService.deleteById(userName);

        return "redirect:/user/create";
    }



}
