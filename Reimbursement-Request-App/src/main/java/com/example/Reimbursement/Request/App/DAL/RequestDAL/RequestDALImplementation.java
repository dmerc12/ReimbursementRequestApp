package com.example.Reimbursement.Request.App.DAL.RequestDAL;

import com.example.Reimbursement.Request.App.Entities.Request;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class RequestDALImplementation implements RequestDALInterface {

    public static Logger logger = LogManager.getLogger(RequestDALImplementation.class);

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