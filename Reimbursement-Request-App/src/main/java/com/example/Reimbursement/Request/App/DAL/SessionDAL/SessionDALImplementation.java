package com.example.Reimbursement.Request.App.DAL.SessionDAL;

import com.example.Reimbursement.Request.App.Entities.Session;
import com.example.Reimbursement.Request.App.Utilities.DatabaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;

public class SessionDALImplementation implements SessionDALInterface {

    public static Logger logger = LogManager.getLogger(SessionDALImplementation.class);

    public void accessSessionTable(String sql) {
        logger.info("Beginning DAL method access session table with sql statement: " + sql);
        try (Connection connection = DatabaseConnection.createConnection()) {
            PreparedStatement ps = null;
            if (connection != null) {
                ps = connection.prepareStatement(sql);
                ps.execute();
            }
            logger.info("Finishing DAL method access session table");
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method access session table with error: " + error.getMessage());
        }
    }

    @Override
    public Session addSession(Session session) {
        logger.info("Beginning DAL method add session with session: " + session);
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "insert into reimbursement_request_app.sessions values (0, ?, ?);";
            PreparedStatement ps = null;
            if (connection != null) {
                ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, session.getEmployeeId());
                ps.setObject(2, session.getExpiration());
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                session.setSessionId(rs.getInt(1));
            }
            logger.info("Finishing DAL method add session with result: " + session);
            return session;
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method add session with error: " + error.getMessage());
            return null;
        }
    }

    @Override
    public Session getSession(int sessionId) {
        logger.info("Beginning DAL method get session with session ID: " + sessionId);
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "select * from reimbursement_request_app.sessions where session_id=?;";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, sessionId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            LocalDateTime expiration = rs.getTimestamp("expiration").toLocalDateTime();
            Session session = new Session(
                    rs.getInt("session_id"),
                    rs.getInt("employee_id"),
                    expiration
            );
            logger.info("Finishing DAL method get session with result: " + session);
            return session;
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method get session with error: " + error.getMessage());
            return null;
        }
    }

    @Override
    public Session updateSession(Session session) {
        logger.info("Beginning DAL method update session with session: " + session);
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "update reimbursement_request_app.sessions set expiration=? where session_id=?;";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, session.getExpiration());
            ps.setInt(2, session.getSessionId());
            ps.executeUpdate();
            logger.info("Finishing DAL method update session with result: " + session);
            return session;
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method update session with error: " + error.getMessage());
            return null;
        }
    }

    @Override
    public int deleteSession(int sessionId) {
        logger.info("Beginning DAL method delete session with session ID: " + sessionId);
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "delete from reimbursement_request_app.sessions where session_id=?;";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, sessionId);
            int result = ps.executeUpdate();
            logger.info("Finishing DAL method delete session");
            return result;
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method delete session with error: " + error.getMessage());
            return 0;
        }
    }
}
