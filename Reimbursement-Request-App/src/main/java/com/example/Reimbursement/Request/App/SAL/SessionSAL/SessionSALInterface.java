package com.example.Reimbursement.Request.App.SAL.SessionSAL;

import com.example.Reimbursement.Request.App.Entities.Session;

public interface SessionSALInterface {
    Session serviceAddSession(Session session);
    Session serviceGetSession(int sessionId);
    Session serviceUpdateSession(Session session);
    boolean serviceDeleteSession(int sessionId);
}
