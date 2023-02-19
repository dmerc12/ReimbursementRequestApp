package com.example.Reimbursement.Request.App.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
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

    public Request(String employeeId, int requestNumber, String reasonId, String comment, float amount,
                   LocalDateTime requestCreated, LocalDateTime requestLastModified) {
        this.employeeId = employeeId;
        this.requestNumber = requestNumber;
        this.reasonId = reasonId;
        this.comment = comment;
        this.amount = amount;
        this.requestCreated = requestCreated;
        this.requestLastModified = requestLastModified;
    }
}
