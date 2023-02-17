package com.example.Reimbursement.Request.App.Implementation.SessionDAL;

import com.example.Reimbursement.Request.App.Entities.Session;

public abstract class SessionImplementation implements SessionRepository {
    @Override
    public Session addSession(Session session) {
        return null;
    }

    @Override
    public Session getSession(int sessionId) {
        return null;
    }

    @Override
    public Session updateSession(Session session) {
        return null;
    }

    @Override
    public boolean deleteSession(int sessionId) {
        return false;
    }
}
