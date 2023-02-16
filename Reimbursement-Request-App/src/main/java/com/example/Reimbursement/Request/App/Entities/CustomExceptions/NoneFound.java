package com.example.Reimbursement.Request.App.Entities.CustomExceptions;

public class NoneFound extends RuntimeException{
    public NoneFound(String message) {
        super(message);
    }
}
