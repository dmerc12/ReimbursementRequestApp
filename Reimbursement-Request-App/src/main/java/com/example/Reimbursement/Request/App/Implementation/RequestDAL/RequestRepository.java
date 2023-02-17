package com.example.Reimbursement.Request.App.Implementation.RequestDAL;

import com.example.Reimbursement.Request.App.Entities.Request;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RequestRepository extends MongoRepository<Request, String> {
    Request addRequest(Request request);
    List<Request> getAllRequests(int employeeId);
    Request getRequest(int requestId);
    Request updateRequest(Request request);
    boolean deleteRequest(int requestId);
}
