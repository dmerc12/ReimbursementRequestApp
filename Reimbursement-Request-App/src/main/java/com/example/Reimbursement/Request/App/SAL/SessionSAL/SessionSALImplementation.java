package com.example.Reimbursement.Request.App.SAL.SessionSAL;

import com.example.Reimbursement.Request.App.DAL.SessionDAL.SessionDALImplementation;
import com.example.Reimbursement.Request.App.Entities.Session;
import com.example.Reimbursement.Request.App.SAL.EmployeeSAL.EmployeeSALImplementation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    public int deleteSession(int sessionId) {
        return 0;
    }
}
