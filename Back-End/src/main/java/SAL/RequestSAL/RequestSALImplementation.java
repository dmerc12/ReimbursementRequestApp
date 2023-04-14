package SAL.RequestSAL;

import DAL.RequestDAL.RequestDALImplementation;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Request;
import SAL.CategorySAL.CategorySALImplementation;
import SAL.EmployeeSAL.EmployeeSALImplementation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RequestSALImplementation implements RequestSALInterface{
    private final RequestDALImplementation requestDAO;
    private final EmployeeSALImplementation employeeSAO;
    private final CategorySALImplementation categorySAO;
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
            logger.warn("SAL method add request, amount negative or $0.00");
            throw new GeneralError("The amount field cannot be $0.01, please try again!");
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
        logger.info("Beginning SAL method get request with request ID: " + requestId);
        Request request = requestDAO.getRequest(requestId);
        if (request == null) {
            logger.warn("SAL method get request, no request found");
            throw new GeneralError("No request found, please try again!");
        } else {
            logger.info("Finishing SAL method get request with request: " + request);
            return request;
        }
    }

    @Override
    public Request updateRequest(Request request) {
        logger.info("Beginning SAL method update request with request: " + request);
        getRequest(request.getRequestId());
        categorySAO.getCategory(request.getCategoryId());
        if (request.getComment().equals("")) {
            logger.warn("SAL method update request, comment left empty");
            throw new GeneralError("The comment field cannot be left empty, please try again!");
        } else if (request.getComment().length() > 150) {
            logger.warn("SAL method update request, comment too long");
            throw new GeneralError("The comment field cannot exceed 150 characters, please try again!");
        } else if (request.getAmount() < 0.01) {
            logger.warn("SAL method update request, amount negative or $0.00");
            throw new GeneralError("The amount field cannot be $0.01, please try again!");
        } else {
            Request updatedRequest = requestDAO.updateRequest(request);
            logger.info("Finishing SAL method update request with request: " + updatedRequest);
            return updatedRequest;
        }
    }

    @Override
    public int deleteRequest(int requestId) {
        logger.info("Beginning SAL method delete request with request ID: " + requestId);
        getRequest(requestId);
        int result = requestDAO.deleteRequest(requestId);
        logger.info("Finishing SAL method delete request");
        return result;
    }

    @Override
    public int deleteAllRequests(int employeeId) {
        logger.info("Beginning SAL method delete all requests with employee ID: " + employeeId);
        employeeSAO.getEmployeeById(employeeId);
        int result = requestDAO.deleteAllRequests(employeeId);
        logger.info("Finishing SAL method delete all requests");
        return result;
    }
}
