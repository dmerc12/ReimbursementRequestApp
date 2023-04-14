package DAL;

import DAL.SessionDAL.SessionDALImplementation;
import Entities.Data.Session;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.time.LocalDateTime;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SessionDALTests {
    int currentSessionId = 1;
    SessionDALImplementation sessionDAO = new SessionDALImplementation();
    Session successfulSession = new Session(0, -1, LocalDateTime.now());
    Session updateSession = new Session(currentSessionId, successfulSession.getEmployeeId(),
            LocalDateTime.now().plusMinutes(30));

    @Test
    public void a_addSessionSuccess() {
        Session result = sessionDAO.addSession(successfulSession);
        Assert.assertNotEquals(result.getSessionId(), 0);
    }

    @Test
    public void b_getSessionSuccess() {
        Session result = sessionDAO.getSession(-1);
        Assert.assertNotNull(result);
    }

    @Test
    public void c_updateSessionSuccess() {
        Session result = sessionDAO.updateSession(updateSession);
        Assert.assertEquals(updateSession.getExpiration(), result.getExpiration());
        Assert.assertEquals(updateSession.getSessionId(), result.getSessionId());
        Assert.assertEquals(updateSession.getEmployeeId(), result.getEmployeeId());
    }

    @Test
    public void d_deleteSessionSuccess() {
        int result = sessionDAO.deleteSession(currentSessionId);
        Assert.assertTrue(result != 0);
    }

    @Test
    public void e_deleteAllSessionsSuccess() {
        Session session = new Session(0, -2, LocalDateTime.now().plusMinutes(15));
        Session newSession = sessionDAO.addSession(session);
        int result = sessionDAO.deleteAllSessions(newSession.getEmployeeId());
        Assert.assertTrue(result != 0);
    }
}
