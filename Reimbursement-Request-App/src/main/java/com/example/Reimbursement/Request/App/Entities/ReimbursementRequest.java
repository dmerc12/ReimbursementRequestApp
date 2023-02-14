package com.example.Reimbursement.Request.App.Entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class ReimbursementRequest {
    @Id
    private int employeeId;
    private int reasonId;
    private LocalDateTime requestCreationDate;
    private float amount;
}
