package com.example.Reimbursement.Request.App.SAL;

import com.example.Reimbursement.Request.App.DAL.EmployeeDAL.EmployeeDALImplementation;
import com.example.Reimbursement.Request.App.DAL.SessionDAL.SessionDALImplementation;
import com.example.Reimbursement.Request.App.Entities.CustomExceptions.GeneralError;
import com.example.Reimbursement.Request.App.Entities.Session;
import com.example.Reimbursement.Request.App.SAL.EmployeeSAL.EmployeeSALImplementation;
import com.example.Reimbursement.Request.App.SAL.SessionSAL.SessionSALImplementation;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SessionSALTests {
    int currentSessionId = 1;
    SessionDALImplementation sessionDAO = new SessionDALImplementation();
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    SessionSALImplementation sessionSAO = new SessionSALImplementation(sessionDAO, employeeSAO);
    Session successSession = new Session(0, -1, LocalDateTime.now().plusMinutes(15));
    Session updateSession = new Session(currentSessionId, successSession.getEmployeeId(),
            successSession.getExpiration().plusMinutes(15));

    @Test
    public void aa_addSessionEmployeeNotFound() {
        try {
            Session testSession = new Session(0, -500000000, LocalDateTime.now().plusMinutes(15));
            sessionSAO.addSession(testSession);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "No employee found, please try again!");
        }
    }

    @Test
    public void ab_addSessionExpirationAlreadyExpired() {
        try {
            Session testSession = new Session(0, -1, LocalDateTime.now().minusMinutes(15));
            sessionSAO.addSession(testSession);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The expiration field cannot be expired, please try again!");
        }
    }

    @Test
    public void ac_addSessionSuccess() {
        Session result = sessionSAO.addSession(successSession);
        Assert.assertNotEquals(result.getSessionId(), 0);
    }

    @Test
    public void ba_getSessionNotFound() {
        try {
            sessionSAO.getSession(-500000000);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "No session found, please try again!");
        }
    }

    @Test
    public void bb_getSessionSuccess() {
        Session result = sessionSAO.getSession(-1);
        Assert.assertNotNull(result);
    }

    @Test
    public void c_updateSessionSuccess() {
        Session result = sessionSAO.updateSession(updateSession);
        Assert.assertEquals(result.getExpiration(), updateSession.getExpiration());
    }

    @Test
    public void d_deleteSessionSuccess() {
        int result = sessionSAO.deleteSession(currentSessionId);
        Assert.assertTrue(result != 0);
    }
}
