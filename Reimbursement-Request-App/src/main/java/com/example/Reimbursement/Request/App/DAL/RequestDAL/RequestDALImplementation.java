package com.example.Reimbursement.Request.App.DAL.RequestDAL;

import com.example.Reimbursement.Request.App.Entities.Request;

import java.util.List;

public abstract class RequestDALImplementation implements RequestDALInterface{
    @Override
    public Request addRequest(Request request) {
        return null;
    }

    @Override
    public List<Request> getAllRequests(int employeeId) {
        return null;
    }

    @Override
    public Request getRequest(int requestId) {
        return null;
    }

    @Override
    public Request updateRequest(Request request) {
        return null;
    }

    @Override
    public boolean deleteRequest(int requestId) {
        return false;
    }
}
