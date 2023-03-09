package com.example.Reimbursement.Request.App.DAL.EmployeeDAL;

import com.example.Reimbursement.Request.App.Entities.Employee;
import com.example.Reimbursement.Request.App.Utilities.DatabaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;


public class EmployeeDALImplementation implements EmployeeDALInterface {

    public static Logger logger = LogManager.getLogger(EmployeeDALImplementation.class);

    @Override
    public Employee addEmployee(Employee employee) {
        logger.info("Beginning DAL method add employee with data: " + employee);
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "insert into reimbursement_request_app.employees values (0, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = null;
            if (connection != null) {
                ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, employee.getEmail());
                ps.setString(2, employee.getPassword());
                ps.setString(3, employee.getFirstName());
                ps.setString(4, employee.getLastName());
                ps.setString(5, employee.getPhoneNumber());
                ps.setString(6, employee.getAddress());
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                employee.setEmployeeId(rs.getInt(1));
            }
            logger.info("Finishing DAL method add employee with result: " + employee);
            return employee;
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method add employee with error: " + error.getMessage());
            return null;
        }
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        return null;
    }

    @Override
    public Employee getEmployeeByEmail(String email) {return null; }

    @Override
    public Employee login(String email, String password) { return null; }

    @Override
    public Employee updateEmployee(Employee employee) {
        return null;
    }

    @Override
    public int deleteEmployee(int employeeId) {
        logger.info("Beginning DAL method delete employee with data: " + employeeId);
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "delete from reimbursement_request_app.employees where employee_id=?;";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, employeeId);
            int result = ps.executeUpdate();
            logger.info("Finishing DAL method delete employee");
            return result;
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method delete employee with error: " + error.getMessage());
            return 0;
        }
    }
}
