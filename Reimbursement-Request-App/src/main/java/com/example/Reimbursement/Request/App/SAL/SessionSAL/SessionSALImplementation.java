package com.example.Reimbursement.Request.App.SAL.SessionSAL;

import com.example.Reimbursement.Request.App.DAL.SessionDAL.SessionDALImplementation;
import com.example.Reimbursement.Request.App.Entities.CustomExceptions.GeneralError;
import com.example.Reimbursement.Request.App.Entities.Session;
import com.example.Reimbursement.Request.App.SAL.EmployeeSAL.EmployeeSALImplementation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

public class SessionSALImplementation implements SessionSALInterface{
    private SessionDALImplementation sessionDAO;
    private EmployeeSALImplementation employeeSAO;
    public static Logger logger = LogManager.getLogger(SessionSALImplementation.class);
    public SessionSALImplementation(SessionDALImplementation sessionDAO, EmployeeSALImplementation employeeSAO) {
        this.sessionDAO = sessionDAO;
        this.employeeSAO = employeeSAO;
    }

    @Override
    public Session addSession(Session session) {
        logger.info("Beginning SAL method add session with session: " + session);
        employeeSAO.getEmployeeById(session.getEmployeeId());
        if (session.getExpiration().isBefore(LocalDateTime.now())) {
            logger.warn("SAL method add session, session expired");
            throw new GeneralError("The expiration field cannot be expired, please try again!");
        } else {
            Session addedSession = sessionDAO.addSession(session);
            logger.info("Finishing SAL method add session with new session: " + addedSession);
            return addedSession;
        }
    }

    @Override
    public Session getSession(int sessionId) {
        logger.info("Beginning SAL method get session with session ID: " + sessionId);
        Session session = sessionDAO.getSession(sessionId);
        if (session == null) {
            logger.warn("SAL method get session, no session found");
            throw new GeneralError("No session found, please try again!");
        } else if (session.getExpiration().isBefore(LocalDateTime.now()) |
                session.getExpiration().equals(LocalDateTime.now())) {
            logger.warn("SAL method get session, session expired");
            throw new GeneralError("Session has expired, please log in!");
        } else {
            logger.info("Finishing SAL method get session with session: " + session);
            return session;
        }
    }

    @Override
    public Session updateSession(Session session) {
        logger.info("Beginning SAL method update session with session: " + session);
        getSession(session.getSessionId());
        Session updatedSession = sessionDAO.updateSession(session);
        logger.info("Finishing SAL method update session with updated session: " + session);
        return updatedSession;
    }

    @Override
    public int deleteSession(int sessionId) {
        logger.info("Beginning SAL method delete session with session ID: " + sessionId);
        getSession(sessionId);
        int result = sessionDAO.deleteSession(sessionId);
        logger.info("Finishing SAL method delete session with result: " + result);
        return result;
    }
}
