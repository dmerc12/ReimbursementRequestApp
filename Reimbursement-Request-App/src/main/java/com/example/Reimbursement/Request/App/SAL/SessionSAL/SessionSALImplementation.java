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
        return null;
    }

    @Override
    public Session updateSession(Session session) {
        return null;
    }

    @Override
    public int deleteSession(int sessionId) {
        return 0;
    }
}
