package com.example.Reimbursement.Request.App.SAL;

import com.example.Reimbursement.Request.App.DAL.EmployeeDAL.EmployeeDALImplementation;
import com.example.Reimbursement.Request.App.DAL.SessionDAL.SessionDALImplementation;
import com.example.Reimbursement.Request.App.Entities.CustomExceptions.GeneralError;
import com.example.Reimbursement.Request.App.Entities.Session;
import com.example.Reimbursement.Request.App.SAL.EmployeeSAL.EmployeeSALImplementation;
import com.example.Reimbursement.Request.App.SAL.SessionSAL.SessionSALImplementation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SessionSALTests {
    private final int currentSessionId = 1;
    private final Session successSession = new Session(0, -1, LocalDateTime.now().plusMinutes(15));
    private final Session updateSession = new Session(currentSessionId, successSession.getEmployeeId(),
            successSession.getExpiration().plusMinutes(15));
    private final SessionDALImplementation sessionDAO = new SessionDALImplementation();
    private final EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    private final EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    private final SessionSALImplementation sessionSAO = new SessionSALImplementation(sessionDAO, employeeSAO);
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
    public void ab_addSessionExpirationExpired() {
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
    public void bb_getSessionExpired() {
        try {
            sessionSAO.getSession(-2);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Session has expired, please log in!");
        }
    }

    @Test
    public void bc_getSessionSuccess() {
        Session result = sessionSAO.getSession(-1);
        Assert.assertNotNull(result);
    }

    @Test
    public void ca_updateSessionNotFound() {
        try {
            Session testSession = new Session(-500000000, -1, LocalDateTime.now().plusMinutes(15));
            sessionSAO.updateSession(testSession);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "No session found, please try again!");
        }
    }

    @Test
    public void cb_updateSessionExpired() {
        try {
            Session testSession = new Session(-1, -1, LocalDateTime.now().minusMinutes(30));
            sessionSAO.updateSession(testSession);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Session has expired, please log in!");
        }
    }

    @Test
    public void cc_updateSessionSuccess() {
        Session result = sessionSAO.updateSession(updateSession);
        Assert.assertEquals(result.getExpiration(), updateSession.getExpiration());
    }

    @Test
    public void da_deleteSessionNotFound() {
        try {
            sessionSAO.deleteSession(-500000000);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "No session found, please try again!");
        }
    }

    @Test
    public void db_deleteSessionExpired() {
        try {
            sessionSAO.deleteSession(-1);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Session has expired, please log in!");
        }
    }

    @Test
    public void dc_deleteSessionSuccess() {
        int result = sessionSAO.deleteSession(currentSessionId);
        Assert.assertTrue(result != 0);
    }
}
