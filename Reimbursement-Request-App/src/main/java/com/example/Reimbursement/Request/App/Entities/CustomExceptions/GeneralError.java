package com.example.Reimbursement.Request.App.Entities.CustomExceptions;

public class GeneralError extends RuntimeException{
    public GeneralError(String message) {
        super(message);
    }
}
