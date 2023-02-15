package com.example.Reimbursement.Request.App.SAL.SessionSAL;

import com.example.Reimbursement.Request.App.DAL.SessionDAL.SessionDALImplementation;
import com.example.Reimbursement.Request.App.Entities.Session;

public class SessionSALImplementation implements SessionSALInterface{
    private SessionDALImplementation sessionDAO;

    @Override
    public Session serviceAddSession(Session session) {
        return null;
    }

    @Override
    public Session serviceGetSession(int sessionId) {
        return null;
    }

    @Override
    public Session serviceUpdateSession(Session session) {
        return null;
    }

    @Override
    public boolean serviceDeleteSession(int sessionId) {
        return false;
    }
}
