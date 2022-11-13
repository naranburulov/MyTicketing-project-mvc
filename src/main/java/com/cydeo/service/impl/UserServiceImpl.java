package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ProjectService projectService;
    private final TaskService taskService;

    //@Lazy was used inside the constructor to get rid of en error,
    // where circular bean dependencies had been created
    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, @Lazy ProjectService projectService, @Lazy TaskService taskService) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.projectService = projectService;
        this.taskService = taskService;
    }


    @Override
    public List<UserDTO> listAllUsers() {

        List<User> userList = userRepository.findAllByIsDeletedOrderByFirstNameDesc(false);
        return userList.stream().map(userMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String userName) {

        User theUser = userRepository.findByUserNameAndIsDeleted(userName, false);

        return userMapper.convertToDto(theUser);

    }

    @Override
    public void save(UserDTO user) {

        userRepository.save(userMapper.convertToEntity(user));

    }

    @Override
    public UserDTO update(UserDTO user) {

        //find the certain User from DB:
        User theUser = userRepository.findByUserNameAndIsDeleted(user.getUserName(), false);    //has id
        //convert userDTO to entity:
        User convertedUser = userMapper.convertToEntity(user);  //no id yet
        //set TheUser's id to the convertedUser object:
        convertedUser.setId(theUser.getId());
        //save the updated user in the DB:
        userRepository.save(convertedUser);

        return findByUserName(user.getUserName());
    }


    @Override
    public void delete(String userName) {
        User user = userRepository.findByUserNameAndIsDeleted(userName, false);

        //condition purpose is added to fix the possible bug
        // by using some local private method
        //issue could be with deleting a user, who still has uncompleted tasks
        if (checkIfUserCanBeDeleted(user)) {

            //change the isDeleted to =true,
            user.setIsDeleted(true);
            //another possible bug to take care of, in case the deleted user would come back
            // to be recreated again with the same email:
            user.setUserName(user.getUserName() + "-" + user.getId());          //outcome ex: "harold@manager.com-1"

            // and save the object
            userRepository.save(user);
        }


    }

    @Override
    public List<UserDTO> listAllByRole(String role) {

        //go to DB and bring the users with those roles
        List<User> users = userRepository.findByRoleDescriptionIgnoreCaseAndIsDeleted(role, false);

        return users.stream().map(userMapper::convertToDto).collect(Collectors.toList());
    }



    //just a local method, which is not used in any controller,
    // therefore it can be private, and pass the User entity directly,
    // and not necessarily UserDto (since it is not connected to UI)
    private boolean checkIfUserCanBeDeleted(User user) {

        switch (user.getRole().getDescription()) {
            case "Manager":
                List<ProjectDTO> projectDTOList = projectService.listAllNonCompletedByAssignedManager(userMapper.convertToDto(user));
                return projectDTOList.size() == 0;
            case "Employee":
                List<TaskDTO> taskDTOList = taskService.listAllNonCompletedByAssignedEmployee(userMapper.convertToDto(user));
                return taskDTOList.size() == 0;
            default:
                return true;
        }

    }


}