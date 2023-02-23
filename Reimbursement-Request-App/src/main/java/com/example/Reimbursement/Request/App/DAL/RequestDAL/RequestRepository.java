package com.example.Reimbursement.Request.App.DAL.RequestDAL;

import com.example.Reimbursement.Request.App.Entities.Request;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends MongoRepository<Request, String> {
    MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/reimbursement-request-app");
    MongoTemplate mongoTemplate = new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongoClient,
            "reimbursement-request-app"));
}
