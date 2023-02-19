package com.example.Reimbursement.Request.App.Entities;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
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

    public Employee() {

    }

    public Employee(String email, String password, String firstName, String lastName, LocalDateTime created,
                    LocalDateTime lastModified) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.created = created;
        this.lastModified = lastModified;
    }
}
