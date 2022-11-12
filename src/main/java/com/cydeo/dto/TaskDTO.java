package com.cydeo.dto;

import com.cydeo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO{

    private Long id;            //creating something unique for TaskCreate

    @NotNull
    private ProjectDTO project;
    @NotNull
    private UserDTO assignedEmployee;
    @NotBlank
    private String taskSubject;
    @NotBlank
    private String taskDetail;

    private Status taskStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate assignedDate;



}
