package com.example.Reimbursement.Request.App.Entities.Data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Request {
    private int requestId;
    private int employeeId;
    private int categoryId;
    private String comment;
    private double amount;
}
