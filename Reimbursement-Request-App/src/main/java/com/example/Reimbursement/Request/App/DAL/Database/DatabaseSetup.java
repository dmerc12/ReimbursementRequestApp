package com.example.Reimbursement.Request.App.DAL.Database;

import com.example.Reimbursement.Request.App.DAL.CategoryDAL.CategoryDALImplementation;
import com.example.Reimbursement.Request.App.DAL.EmployeeDAL.EmployeeDALImplementation;
import com.example.Reimbursement.Request.App.DAL.RequestDAL.RequestDALImplementation;
import com.example.Reimbursement.Request.App.DAL.SessionDAL.SessionDALImplementation;
import com.example.Reimbursement.Request.App.Utilities.DatabaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DatabaseSetup {
    private static final Logger logger = LogManager.getLogger(DatabaseSetup.class);
    private static final CategoryDALImplementation categoryDAO = new CategoryDALImplementation();
    private static final EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    private static final RequestDALImplementation requestDAO = new RequestDALImplementation();
    private static final SessionDALImplementation sessionDAO = new SessionDALImplementation();

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.createConnection()) {
            logger.info("Beginning Database Setup Script");
            // employee table setup
            String employeeTable = "create table reimbursement_request_app.employees(employee_id int auto_increment " +
                    "primary key, first_name varchar(36), last_name varchar(36), email varchar(60), " +
                    "passwrd varchar(60), phone_number varchar(13), address varchar(60));";
            String testEmployee = "insert into reimbursement_request_app.employees values(-1, 'test@email.com', " +
                    "'test', 'test', 'test', 'test', 'test');";
            employeeDAO.accessEmployeeTable(employeeTable);
            employeeDAO.accessEmployeeTable(testEmployee);

            // category table setup
            String categoryTable = "create table reimbursement_request_app.categories(category_id int auto_increment " +
                    "primary key, category_name varchar(60))";
            String testCategory = "insert into reimbursement_request_app.categories values(-1, 'test');";
            categoryDAO.accessCategoryTable(categoryTable);
            categoryDAO.accessCategoryTable(testCategory);

            // request table setup
            String requestTable = "create table reimbursement_request_app.requests(request_id int auto_increment " +
                    "primary key, employee_id int, category_id int, comment varchar(150), amount float, constraint " +
                    "employeefk foreign key(employee_id) references " +
                    "reimbursement_request_app.employees(employee_id), constraint categoryfk foreign key " +
                    "(category_id) references reimbursement_request_app.categories(category_id));";
            String testRequest = "insert into reimbursement_request_app.requests values (-1, -1, -1, 'test', 25.00);";
            requestDAO.accessRequestTable(requestTable);
            requestDAO.accessRequestTable(testRequest);

            // session table setup
            String sessionTable = "create table reimbursement_request_app.sessions(session_id int auto_increment " +
                    "primary key, employee_id int, expiration DateTime, constraint sessionfk foreign key " +
                    "(employee_id) references reimbursement_request_app.employees(employee_id));";
            String testSession = String.format("insert into reimbursement_request_app.sessions values(-1, -1, '%s');",
                    LocalDateTime.now());
            sessionDAO.accessSessionTable(sessionTable);
            sessionDAO.accessSessionTable(testSession);

            logger.info("Finishing Database Setup Script");
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with Database Setup Script with error: " + error.getMessage());
        }
    }
}
