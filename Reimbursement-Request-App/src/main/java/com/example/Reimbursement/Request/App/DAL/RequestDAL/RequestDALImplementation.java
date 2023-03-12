package com.example.Reimbursement.Request.App.DAL.RequestDAL;

import com.example.Reimbursement.Request.App.Entities.Request;
import com.example.Reimbursement.Request.App.Utilities.DatabaseConnection;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
@NoArgsConstructor
public class RequestDALImplementation implements RequestDALInterface {

    public static Logger logger = LogManager.getLogger(RequestDALImplementation.class);

    public void accessRequestTable(String sql) {
        logger.info("Beginning DAL method access request table with sql statement: " + sql);
        try (Connection connection = DatabaseConnection.createConnection()) {
            PreparedStatement ps = null;
            if (connection != null) {
                ps = connection.prepareStatement(sql);
                ps.execute();
            }
            logger.info("Finishing DAL method access request table");
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method access request table with error: " + error.getMessage());
        }
    }

    @Override
    public Request addRequest(Request request) {
        return null;
    }

    @Override
    public List<Request> getAllRequests(int employeeId) {
        return null;
    }

    @Override
    public Request getRequest(int requestId) {
        return null;
    }

    @Override
    public Request updateRequest(Request request) {
        return null;
    }

    @Override
    public boolean deleteRequest(int requestId) {
        return false;
    }
}
