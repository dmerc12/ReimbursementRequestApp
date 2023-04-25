package DAL.EmployeeDAL;

import Entities.Data.Employee;
import Utilities.DatabaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class EmployeeDALImplementation implements EmployeeDALInterface{

    public static Logger logger = LogManager.getLogger(EmployeeDALImplementation.class);

    public void accessEmployeeTable(String sql) {
        logger.info("Beginning DAL method access employee table with sql statement: " + sql);
        try (Connection connection = DatabaseConnection.createConnection()) {
            PreparedStatement ps = null;
            if (connection != null) {
                ps = connection.prepareStatement(sql);
                ps.execute();
            }
            logger.info("Finishing DAL method access employee table");
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method access employee table with error: " + error.getMessage());
        }
    }


    @Override
    public Employee addEmployee(Employee employee) {
        logger.info("Beginning DAL method add employee with employeeId: " + employee.getEmployeeId() +
                ", firstName; " + employee.getFirstName() + ", lastName: " + employee.getLastName() +
                ", email: " + employee.getEmail() + ", password: " + employee.getPassword(),", phoneNumber: " +
                employee.getPhoneNumber() + ", address: " + employee.getAddress());
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "insert into reimbursement_request_app.employees values (0, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = null;
            if (connection != null) {
                ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, employee.getFirstName());
                ps.setString(2, employee.getLastName());
                ps.setString(3, employee.getEmail());
                ps.setString(4, employee.getPassword());
                ps.setString(5, employee.getPhoneNumber());
                ps.setString(6, employee.getAddress());
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                employee.setEmployeeId(rs.getInt(1));
            }
            String employeeString = String.format("employeeId: %s, firstName: %s, lastName: %s, email: %s, " +
                            "password: %s, phoneNumber: %s, address: %s", employee.getEmployeeId(),
                    employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPassword(),
                    employee.getPhoneNumber(), employee.getAddress());
            logger.info("Finishing DAL method add employee with result: " + employeeString);
            return employee;
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method add employee with error: " + error.getMessage());
            return null;
        }
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        logger.info("Beginning DAL method get employee by ID with employee ID: " + employeeId);
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "select * from reimbursement_request_app.employees where employee_id=?;";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("passwrd"),
                        rs.getString("phone_number"),
                        rs.getString("address")
                );
                String employeeString = String.format("employeeId: %s, firstName: %s, lastName: %s, email: %s, " +
                                "password: %s, phoneNumber: %s, address: %s", employee.getEmployeeId(),
                        employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPassword(),
                        employee.getPhoneNumber(), employee.getAddress());
                logger.info("Finishing DAL method get employee by ID with result: " + employeeString);
                return employee;
            } else {
                logger.info("Finishing DAL method get employee by ID with nothing found");
                return null;
            }
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method get employee by ID with error: " + error.getMessage());
            return null;
        }
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        logger.info("Beginning DAL method get employee by email with email: " + email);
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "select * from reimbursement_request_app.employees where email=?;";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("passwrd"),
                        rs.getString("phone_number"),
                        rs.getString("address")
                );
                String employeeString = String.format("employeeId: %s, firstName: %s, lastName: %s, email: %s, " +
                                "password: %s, phoneNumber: %s, address: %s", employee.getEmployeeId(),
                        employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPassword(),
                        employee.getPhoneNumber(), employee.getAddress());
                logger.info("Finishing DAL method get employee by email with result: " + employeeString);
                return employee;
            } else {
                logger.info("Finishing DAL method get employee by email, none found within the database");
                return null;
            }
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method get employee by email with error: " + error.getMessage());
            return null;
        }
    }

    @Override
    public Employee login(String email, String password) {
        logger.info("Beginning DAL method login with email: " + email + ", and password: " + password);
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "select * from reimbursement_request_app.employees where email=? and passwrd=?;";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("passwrd"),
                        rs.getString("phone_number"),
                        rs.getString("address")
                );
                String employeeString = String.format("employeeId: %s, firstName: %s, lastName: %s, email: %s, " +
                                "password: %s, phoneNumber: %s, address: %s", employee.getEmployeeId(),
                        employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPassword(),
                        employee.getPhoneNumber(), employee.getAddress());
                logger.info("Finishing DAL method login with result: " + employeeString);
                return employee;
            } else {
                logger.info("Finishing DAL method login with result: null");
                return null;
            }
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method login with error: " + error.getMessage());
            return null;
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        logger.info("Beginning DAL method update employee with employeeId: " + employee.getEmployeeId() +
                ", firstName: " + employee.getFirstName() + ", lastName: " + employee.getLastName() +
                ", email: " + employee.getEmail() + ", password: " + employee.getPassword(),", phoneNumber: " +
                employee.getPhoneNumber() + ", address: " + employee.getAddress());
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "update reimbursement_request_app.employees set email=?, address=?, phone_number=?, " +
                    "first_name=?, last_name=? where employee_id=?;";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, employee.getEmail());
            ps.setString(2, employee.getAddress());
            ps.setString(3, employee.getPhoneNumber());
            ps.setString(4, employee.getFirstName());
            ps.setString(5, employee.getLastName());
            ps.setInt(6, employee.getEmployeeId());
            ps.executeUpdate();
            String employeeString = String.format("employeeId: %s, firstName: %s, lastName: %s, email: %s, " +
                            "password: %s, phoneNumber: %s, address: %s", employee.getEmployeeId(),
                    employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPassword(),
                    employee.getPhoneNumber(), employee.getAddress());
            logger.info("Finishing DAL method update employee with result: " + employeeString);
            return employee;
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method update employee with error: " + error.getMessage());
            return null;
        }
    }

    @Override
    public Employee changePassword(Employee employee) {
        logger.info("Beginning DAL method change password with employeeId: " + employee.getEmployeeId() +
                ", firstName: " + employee.getFirstName() + ", lastName: " + employee.getLastName() + ", email: " +
                employee.getEmail() + ", password: " + employee.getPassword() + ", phoneNumber: " +
                employee.getPhoneNumber() + ", address: " + employee.getAddress());
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "update reimbursement_request_app.employees set passwrd=? where employee_id=?;";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, employee.getPassword());
            ps.setInt(2, employee.getEmployeeId());
            ps.executeUpdate();
            String employeeString = String.format("employeeId: %s, firstName: %s, lastName: %s, email: %s, " +
                    "password: %s, phoneNumber: %s, address: %s", employee.getEmployeeId(), employee.getFirstName(),
                    employee.getLastName(), employee.getEmail(), employee.getPassword(), employee.getPhoneNumber(),
                    employee.getAddress());
            logger.info("Finishing DAL method change password with result: " + employeeString);
            return employee;
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method change password with error: " + error.getMessage());
            return null;
        }
    }

    @Override
    public int deleteEmployee(int employeeId) {
        logger.info("Beginning DAL method delete employee with employee ID: " + employeeId);
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
