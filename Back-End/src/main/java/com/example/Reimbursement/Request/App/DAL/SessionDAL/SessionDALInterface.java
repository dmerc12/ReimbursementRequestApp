package com.example.Reimbursement.Request.App.DAL.SessionDAL;

import com.example.Reimbursement.Request.App.Entities.Data.Session;

public interface SessionDALInterface {
    Session addSession(Session session);
    Session getSession(int sessionId);
    Session updateSession(Session session);
    int deleteSession(int sessionId);
}
