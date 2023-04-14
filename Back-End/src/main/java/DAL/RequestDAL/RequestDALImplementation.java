package DAL.RequestDAL;

import Entities.Data.Request;
import Utilities.DatabaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDALImplementation implements RequestDALInterface{
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
        logger.info("Beginning DAL method add request with request: " + request);
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "insert into reimbursement_request_app.requests values (0, ?, ?, ?, ?);";
            PreparedStatement ps = null;
            if (connection != null) {
                ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, request.getEmployeeId());
                ps.setInt(2, request.getCategoryId());
                ps.setString(3, request.getComment());
                ps.setDouble(4, request.getAmount());
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                request.setRequestId(rs.getInt(1));
            }
            logger.info("Finishing DAL method add request with result: " + request);
            return request;
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method add request with error: " + error.getMessage());
            return null;
        }
    }

    @Override
    public List<Request> getAllRequests(int employeeId) {
        logger.info("Beginning DAL method get all requests with employee ID: " + employeeId);
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "select * from reimbursement_request_app.requests where employee_id=?;";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            List<Request> requestList = new ArrayList<>();
            while (rs.next()) {
                Request request = new Request(
                        rs.getInt("request_id"),
                        rs.getInt("employee_id"),
                        rs.getInt("category_id"),
                        rs.getString("comment"),
                        rs.getDouble("amount")
                );
                requestList.add(request);
            }
            logger.info("Finishing DAL method get all requests with result: " + requestList);
            return requestList;
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method get all requests with error: " + error.getMessage());
            return null;
        }
    }

    @Override
    public Request getRequest(int requestId) {
        logger.info("Beginning DAL method get request with request ID: " + requestId);
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "select * from reimbursement_request_app.requests where request_id=?;";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, requestId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Request request = new Request(
                    rs.getInt("request_id"),
                    rs.getInt("employee_id"),
                    rs.getInt("category_id"),
                    rs.getString("comment"),
                    rs.getDouble("amount")
            );
            logger.info("Finishing DAL method get request with result: " + request);
            return request;
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method get request with error: " + error.getMessage());
            return null;
        }
    }

    @Override
    public Request updateRequest(Request request) {
        logger.info("Beginning DAL method update request with request: " + request);
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "update reimbursement_request_app.requests set category_id=?, comment=?, amount=? where " +
                    "request_id=?;";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, request.getCategoryId());
            ps.setString(2, request.getComment());
            ps.setDouble(3, request.getAmount());
            ps.setInt(4, request.getRequestId());
            ps.executeUpdate();
            logger.info("Finishing DAL method update request with result: " + request);
            return request;
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method update request with error: " + error.getMessage());
            return null;
        }
    }

    @Override
    public int deleteRequest(int requestId) {
        logger.info("Beginning DAL method delete request with request ID: " + requestId);
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "delete from reimbursement_request_app.requests where request_id=?;";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, requestId);
            int result = ps.executeUpdate();
            logger.info("Finishing DAL method delete request");
            return result;
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method delete request with error: " + error.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteAllRequests(int employeeId) {
        logger.info("Beginning DAL method delete all requests with employee ID: " + employeeId);
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "delete from reimbursement_request_app.requests where employee_id=?;";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, employeeId);
            int result = ps.executeUpdate();
            logger.info("Finishing DAL method delete all requests");
            return result;
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method delete all requests with error: " + error.getMessage());
            return 0;
        }
    }
}
