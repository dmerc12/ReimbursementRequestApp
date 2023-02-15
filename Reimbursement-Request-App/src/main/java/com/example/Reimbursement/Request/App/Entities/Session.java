package com.example.Reimbursement.Request.App.Entities;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collation = "Sessions")
public class Session {
    @Id
    private String sessionId;
    private String employeeId;
    private LocalDateTime expiration;
}
