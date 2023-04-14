package SAL;

import DAL.EmployeeDAL.EmployeeDALImplementation;
import DAL.SessionDAL.SessionDALImplementation;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Session;
import SAL.EmployeeSAL.EmployeeSALImplementation;
import SAL.SessionSAL.SessionSALImplementation;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

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
            Session testSession = new Session(-2, -1, LocalDateTime.now().minusMinutes(30));
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
            sessionSAO.deleteSession(-2);
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

    @Test
    public void ea_deleteAllSessionsEmployeeNotFound() {
        try {
            sessionSAO.deleteAllSessions(-500000000);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "No employee found, please try again!");
        }
    }
    @Test
    public void eb_deleteAllSessionsSuccess() {
        Session session = new Session(0, -2, LocalDateTime.now().plusMinutes(15));
        Session newSession = sessionDAO.addSession(session);
        int result = sessionSAO.deleteAllSessions(newSession.getEmployeeId());
        Assert.assertTrue(result != 0);
    }
}
