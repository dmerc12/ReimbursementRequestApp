package com.example.Reimbursement.Request.App.Implementation.Employee;

import com.example.Reimbursement.Request.App.Entities.Employee;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/reimbursement-request-app");
    MongoTemplate mongoTemplate = new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongoClient,
            "reimbursement-request-app"));
}
