package com.example.Reimbursement.Request.App.SAL.RequestSAL;

import com.example.Reimbursement.Request.App.DAL.RequestDAL.RequestDALImplementation;
import com.example.Reimbursement.Request.App.Entities.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RequestSALImplementation implements RequestSALInterface{
    private RequestDALImplementation requestDAO;
    public static Logger logger = LogManager.getLogger(RequestSALImplementation.class);
    public RequestSALImplementation(RequestDALImplementation requestDAO) {
        this.requestDAO = requestDAO;
    }
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
    public int deleteRequest(int requestId) {
        return 0;
    }
}
