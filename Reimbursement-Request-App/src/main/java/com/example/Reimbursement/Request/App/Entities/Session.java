package com.example.Reimbursement.Request.App.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Session {
    private int sessionId;
    private int employeeId;
    private LocalDateTime expiration;
}
