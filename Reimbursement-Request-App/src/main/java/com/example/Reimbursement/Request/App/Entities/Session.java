package com.example.Reimbursement.Request.App.Entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collation = "Sessions")
public class Session {
    @Id
    private String sessionId;
    private String employeeId;
    private LocalDateTime expiration;

    public Session(String employeeId, LocalDateTime expiration) {
        this.employeeId = employeeId;
        this.expiration = expiration;
    }
}
