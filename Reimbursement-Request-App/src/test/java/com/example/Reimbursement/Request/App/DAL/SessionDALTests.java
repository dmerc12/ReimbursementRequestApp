package com.example.Reimbursement.Request.App.DAL;

import com.example.Reimbursement.Request.App.DAL.SessionDAL.SessionDALImplementation;
import com.example.Reimbursement.Request.App.Entities.Session;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class SessionDALTests {
    int currentSessionId = 1;
    SessionDALImplementation sessionDAO = new SessionDALImplementation();
    Session successfulSession = new Session(0, -1, LocalDateTime.now());
    Session updateSession = new Session(currentSessionId, successfulSession.getEmployeeId(),
            LocalDateTime.now().plusMinutes(30));

    @Test
    public void addSessionSuccess() {
        Session result = sessionDAO.addSession(successfulSession);
        Assert.assertNotEquals(result.getSessionId(), 0);
    }

    @Test
    public void getSessionSuccess() {
        Session result = sessionDAO.getSession(-1);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateSessionSuccess() {
        Session result = sessionDAO.updateSession(updateSession);
        Assert.assertEquals(updateSession.getExpiration(), result.getExpiration());
        Assert.assertEquals(updateSession.getSessionId(), result.getSessionId());
        Assert.assertEquals(updateSession.getEmployeeId(), result.getEmployeeId());
    }

    @Test
    public void deleteSessionSuccess() {
        int result = sessionDAO.deleteSession(currentSessionId);
        Assert.assertTrue(result != 0);
    }
}
