package com.example.Reimbursement.Request.App.Implementation.Session;

import com.example.Reimbursement.Request.App.Entities.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SessionImplementation implements SessionInterface {

    private SessionRepository sessionRepository;

    public static Logger logger = LogManager.getLogger(SessionImplementation.class);

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
