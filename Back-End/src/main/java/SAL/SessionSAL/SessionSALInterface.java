package SAL.SessionSAL;

import Entities.Data.Session;

public interface SessionSALInterface {
    Session addSession(Session session);
    Session getSession(int sessionId);
    Session updateSession(Session session);
    int deleteSession(int sessionId);
}
