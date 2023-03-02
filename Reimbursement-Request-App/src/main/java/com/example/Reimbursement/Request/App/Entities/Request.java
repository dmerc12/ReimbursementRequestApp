package com.example.Reimbursement.Request.App.Entities;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Request {
    private int requestId;
    private int employeeId;
    private int categoryId;
    private String comment;
    private float amount;
}
