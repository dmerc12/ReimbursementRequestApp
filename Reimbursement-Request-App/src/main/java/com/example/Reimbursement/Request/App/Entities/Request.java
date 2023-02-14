package com.example.Reimbursement.Request.App.Entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Request {
    @Id
    private int requestId;
    private int employeeId;
    private int reasonId;
    private LocalDateTime requestCreated;
    private LocalDateTime requestLastModified;
    private float amount;
}
