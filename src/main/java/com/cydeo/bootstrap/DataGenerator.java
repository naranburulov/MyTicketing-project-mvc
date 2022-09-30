package com.cydeo.bootstrap;

import com.cydeo.dto.RoleDTO;
import org.springframework.boot.CommandLineRunner;


public class DataGenerator implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

        //this run method (provided by Spring) will be executed first, when we run the application
        //it will execute anything we put inside

        //create some roles and put in the DB (in our case - map):

        RoleDTO adminRole = new RoleDTO(1L, "Admin");
        RoleDTO managerRole = new RoleDTO(2L, "Manager");
        RoleDTO employeeRole = new RoleDTO(3L, "Employee");



    }
}
