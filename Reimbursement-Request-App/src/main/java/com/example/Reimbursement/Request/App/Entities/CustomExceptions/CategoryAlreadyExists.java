package com.example.Reimbursement.Request.App.Entities.CustomExceptions;

public class CategoryAlreadyExists extends RuntimeException{
    public CategoryAlreadyExists(String message) {
        super(message);
    }
}
