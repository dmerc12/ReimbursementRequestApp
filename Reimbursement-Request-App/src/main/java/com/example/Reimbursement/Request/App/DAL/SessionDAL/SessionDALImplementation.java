package com.example.Reimbursement.Request.App.DAL.SessionDAL;

import com.example.Reimbursement.Request.App.Entities.Session;
import com.example.Reimbursement.Request.App.Utilities.DatabaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    public boolean deleteSession(int sessionId) {
        return false;
    }
}
