package DAL.SessionDAL;

import Entities.Data.Session;

public interface SessionDALInterface {
    Session addSession(Session session);
    Session getSession(int sessionId);
    Session updateSession(Session session);
    int deleteSession(int sessionId);
    int deleteAllSessions(int employeeId);
}
