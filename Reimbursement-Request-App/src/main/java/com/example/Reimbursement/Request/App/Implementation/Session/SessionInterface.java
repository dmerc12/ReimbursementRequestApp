package com.example.Reimbursement.Request.App.Implementation.Session;

import com.example.Reimbursement.Request.App.Entities.Session;

public interface SessionInterface {    Session addSession(Session session);
    Session getSession(int sessionId);
    Session updateSession(Session session);
    boolean deleteSession(int sessionId);
}
