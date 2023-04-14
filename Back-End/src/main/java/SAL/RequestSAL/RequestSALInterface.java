package SAL.RequestSAL;

import Entities.Data.Request;

import java.util.List;

public interface RequestSALInterface {
    Request addRequest(Request request);
    List<Request> getAllRequests(int employeeId);
    Request getRequest(int requestId);
    Request updateRequest(Request request);
    int deleteRequest(int requestId);
    int deleteAllRequests(int employeeId);
}
