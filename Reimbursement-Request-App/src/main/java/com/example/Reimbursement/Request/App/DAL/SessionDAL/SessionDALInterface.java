package com.example.Reimbursement.Request.App.DAL.SessionDAL;

import com.example.Reimbursement.Request.App.Entities.Session;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessionDALInterface extends MongoRepository<Session, String> {
    Session addSession(Session session);
    Session getSession(int sessionId);
    Session updateSession(Session session);
    boolean deleteSession(int sessionId);
}
