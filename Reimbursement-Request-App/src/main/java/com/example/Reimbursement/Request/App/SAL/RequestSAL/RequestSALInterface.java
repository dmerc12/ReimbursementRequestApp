package com.example.Reimbursement.Request.App.SAL.RequestSAL;

import com.example.Reimbursement.Request.App.Entities.Data.Request;

import java.util.List;

public interface RequestSALInterface {
    Request addRequest(Request request);
    List<Request> getAllRequests(int employeeId);
    Request getRequest(int requestId);
    Request updateRequest(Request request);
    int deleteRequest(int requestId);
 }
