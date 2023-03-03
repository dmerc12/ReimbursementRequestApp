package com.example.Reimbursement.Request.App.Entities;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class Session {
    private int sessionId;
    private int employeeId;
    private LocalDateTime expiration;
}
