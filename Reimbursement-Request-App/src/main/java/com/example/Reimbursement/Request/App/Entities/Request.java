package com.example.Reimbursement.Request.App.Entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collation = "Requests")
public class Request {
    @Id
    private String requestId;
    private String employeeId;
    private int requestNumber;
    private String reasonId;
    private String comment;
    private float amount;
    private LocalDateTime requestCreated;
    private LocalDateTime requestLastModified;
}
