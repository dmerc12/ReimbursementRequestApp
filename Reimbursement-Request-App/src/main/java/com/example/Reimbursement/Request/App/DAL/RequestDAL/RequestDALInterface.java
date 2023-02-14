package com.example.Reimbursement.Request.App.DAL.RequestDAL;

import com.example.Reimbursement.Request.App.Entities.Request;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RequestDALInterface extends MongoRepository<Request, Integer> {
    Request addRequest(Request request);
    List<Request> getAllRequests(int employeeId);
    Request getRequest(int requestId);
    Request updateRequest(Request request);
    boolean deleteRequest(int requestId);
}
