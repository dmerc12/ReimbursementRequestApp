package com.example.Reimbursement.Request.App.SAL.RequestSAL;

import com.example.Reimbursement.Request.App.DAL.RequestDAL.RequestDALImplementation;
import com.example.Reimbursement.Request.App.Entities.CustomExceptions.GeneralError;
import com.example.Reimbursement.Request.App.Entities.Request;
import com.example.Reimbursement.Request.App.SAL.CategorySAL.CategorySALImplementation;
import com.example.Reimbursement.Request.App.SAL.EmployeeSAL.EmployeeSALImplementation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RequestSALImplementation implements RequestSALInterface{
    private RequestDALImplementation requestDAO;
    private EmployeeSALImplementation employeeSAO;
    private CategorySALImplementation categorySAO;
    public static Logger logger = LogManager.getLogger(RequestSALImplementation.class);
    public RequestSALImplementation(RequestDALImplementation requestDAO, EmployeeSALImplementation employeeSAO,
                                    CategorySALImplementation categorySAO) {
        this.requestDAO = requestDAO;
        this.employeeSAO = employeeSAO;
        this.categorySAO = categorySAO;
    }
    @Override
    public Request addRequest(Request request) {
        logger.info("Beginning SAL method add request with request: " + request);
        if (request.getComment().equals("")) {
            logger.warn("SAL method add request, comment left empty");
            throw new GeneralError("The comment field cannot be left empty, please try again!");
        } else if (request.getComment().length() > 150) {
            logger.warn("SAL method add request, comment too long");
            throw new GeneralError("The comment field cannot exceed 150 characters, please try again!");
        } else if (request.getAmount() < 0.01) {
            logger.warn("SAL method add request, amount negative or below $0.00");
            throw new GeneralError("The amount field cannot be below $0.01, please try again!");
        } else {
            employeeSAO.getEmployeeById(request.getEmployeeId());
            categorySAO.getCategory(request.getCategoryId());
            Request result = requestDAO.addRequest(request);
            logger.info("Finishing SAL method add request with result: " + result);
            return request;
        }
    }

    @Override
    public List<Request> getAllRequests(int employeeId) {
        logger.info("Beginning SAL method get all requests with employee ID: " + employeeId);
        employeeSAO.getEmployeeById(employeeId);
        List<Request> requestList = requestDAO.getAllRequests(employeeId);
        if (requestList.size() <= 1) {
            logger.warn("SAL method get all requests, request list empty");
            throw new GeneralError("No requests found, please try again!");
        } else {
            logger.info("Finishing SAL method get all requests with request list: " + requestList);
            return requestList;
        }
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
