package com.example.Reimbursement.Request.App.Entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collation = "Employees")
public class Employee {
    @Id
    private String employeeId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDateTime created;
    private LocalDateTime lastModified;
}
