package DAL.Database;

import DAL.CategoryDAL.CategoryDALImplementation;
import DAL.EmployeeDAL.EmployeeDALImplementation;
import DAL.RequestDAL.RequestDALImplementation;
import DAL.SessionDAL.SessionDALImplementation;
import Utilities.DatabaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class DropDatabase {
    private static final Logger logger = LogManager.getLogger(DropDatabase.class);
    private static final CategoryDALImplementation categoryDAO = new CategoryDALImplementation();
    private static final EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    private static final RequestDALImplementation requestDAO = new RequestDALImplementation();
    private static final SessionDALImplementation sessionDAO = new SessionDALImplementation();

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.createConnection()) {
            logger.info("Beginning Drop Database Script");
            // dropping session table
            String dropSessionTable = "drop table reimbursement_request_app.sessions;";
            sessionDAO.accessSessionTable(dropSessionTable);

            // dropping request table
            String dropRequestTable = "drop table reimbursement_request_app.requests;";
            requestDAO.accessRequestTable(dropRequestTable);

            // dropping category table
            String dropCategoryTable = "drop table reimbursement_request_app.categories;";
            categoryDAO.accessCategoryTable(dropCategoryTable);

            // dropping employee table
            String dropEmployeeTable = "drop table reimbursement_request_app.employees;";
            employeeDAO.accessEmployeeTable(dropEmployeeTable);

            // dropping schema
            String dropSchema = "drop schema reimbursement_request_app";
            employeeDAO.accessEmployeeTable(dropSchema);

            logger.info("Finishing Drop Database Script");
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with Drop Database Script with error: " + error.getMessage());
        }
    }
}
