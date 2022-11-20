package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        model.addAttribute("roles",roleService.listAllRoles());
        model.addAttribute("users",userService.listAllUsers());

        return "/user/create";
    }



    @PostMapping("/create")
    public String insertUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {

            model.addAttribute("roles", roleService.listAllRoles());
            model.addAttribute("users", userService.listAllUsers());

            return "user/create";
        }

        userService.save(user);

        return "redirect:/user/create";
    }



    //select the User to update
    @GetMapping("/update/{userName}")
    public String editUser(@PathVariable("userName") String userName, Model model){

        model.addAttribute("user", userService.findByUserName(userName));
        model.addAttribute("roles",roleService.listAllRoles());
        model.addAttribute("users",userService.listAllUsers());

        return "/user/update";
    }

    //update, by saving the changes on the User
    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("roles",roleService.listAllRoles());
            model.addAttribute("users",userService.listAllUsers());
            return "/user/update";
        }


        userService.update(user);

        return "redirect:/user/create";
    }

    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String userName){

        userService.delete(userName);

        return "redirect:/user/create";
    }



}
