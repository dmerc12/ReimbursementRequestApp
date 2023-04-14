package DAL.RequestDAL;

import Entities.Data.Request;

import java.util.List;

public interface RequestDALInterface {
    Request addRequest(Request request);
    List<Request> getAllRequests(int employeeId);
    Request getRequest(int requestId);
    Request updateRequest(Request request);
    int deleteRequest(int requestId);
    int deleteAllRequests(int employeeId);
}
