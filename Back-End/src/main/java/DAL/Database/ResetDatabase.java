package DAL.Database;

import DAL.CategoryDAL.CategoryDALImplementation;
import DAL.EmployeeDAL.EmployeeDALImplementation;
import DAL.RequestDAL.RequestDALImplementation;
import DAL.SessionDAL.SessionDALImplementation;
import Utilities.CustomHashing;
import Utilities.DatabaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ResetDatabase {
    private static final Logger logger = LogManager.getLogger(DropDatabase.class);
    private static final CategoryDALImplementation categoryDAO = new CategoryDALImplementation();
    private static final EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    private static final RequestDALImplementation requestDAO = new RequestDALImplementation();
    private static final SessionDALImplementation sessionDAO = new SessionDALImplementation();

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.createConnection()) {
            logger.info("Beginning Database Reset Script");

            // dropping tables
            String dropSessionTable = "drop table if exists reimbursement_request_app.sessions;";
            String dropRequestTable = "drop table if exists reimbursement_request_app.requests;";
            String dropCategoryTable = "drop table if exists reimbursement_request_app.categories;";
            String dropEmployeeTable = "drop table if exists reimbursement_request_app.employees;";
            requestDAO.accessRequestTable(dropRequestTable);
            sessionDAO.accessSessionTable(dropSessionTable);
            categoryDAO.accessCategoryTable(dropCategoryTable);
            employeeDAO.accessEmployeeTable(dropEmployeeTable);

            // creating employee table and inserting test data
            String employeeTable = "create table reimbursement_request_app.employees(employee_id int auto_increment " +
                    "primary key, first_name varchar(36), last_name varchar(36), email varchar(60), " +
                    "passwrd varchar(120), phone_number varchar(13), address varchar(60));";
            String testEmployee1 = String.format("insert into reimbursement_request_app.employees values(-1, 'test', " +
                    "'test', 'test@email.com', '%s', '111-222-3333', '123 Test St, Testing, OK 73002');", CustomHashing.hash("test"));
            String testEmployee2 = String.format("insert into reimbursement_request_app.employees values(-2, 'no requests', 'or categories', " +
                    "'no@requestsorcategories.test', '%s', '222-333-4444', '123 Test St, Testing, OK 73002');", CustomHashing.hash("test"));
            employeeDAO.accessEmployeeTable(employeeTable);
            employeeDAO.accessEmployeeTable(testEmployee1);
            employeeDAO.accessEmployeeTable(testEmployee2);

            // creating category table and inserting test data
            String categoryTable = "create table reimbursement_request_app.categories(category_id int auto_increment " +
                    "primary key, employee_id int, category_name varchar(60), constraint employeecategoryfk foreign" +
                    " key (employee_id) references reimbursement_request_app.employees(employee_id))";
            String testCategory = "insert into reimbursement_request_app.categories values(-1, -1, 'test');";
            categoryDAO.accessCategoryTable(categoryTable);
            categoryDAO.accessCategoryTable(testCategory);

            // creating request table and inserting test data
            String requestTable = "create table reimbursement_request_app.requests(request_id int auto_increment " +
                    "primary key, employee_id int, category_id int, comment varchar(150), amount float, constraint " +
                    "employeefk foreign key(employee_id) references " +
                    "reimbursement_request_app.employees(employee_id), constraint categoryfk foreign key " +
                    "(category_id) references reimbursement_request_app.categories(category_id));";
            String testRequest = "insert into reimbursement_request_app.requests values (-1, -1, -1, 'test', 25.00);";
            requestDAO.accessRequestTable(requestTable);
            requestDAO.accessRequestTable(testRequest);

            // creating session table and inserting test data
            String sessionTable = "create table reimbursement_request_app.sessions(session_id int auto_increment " +
                    "primary key, employee_id int, expiration DateTime, constraint sessionfk foreign key " +
                    "(employee_id) references reimbursement_request_app.employees(employee_id));";
            String testSession1 = String.format("insert into reimbursement_request_app.sessions values(-1, -1, '%s');",
                    LocalDateTime.now().plusHours(1));
            String testSession2 = String.format("insert into reimbursement_request_app.sessions values(-2, -1, '%s');",
                    LocalDateTime.now().minusHours(2));
            sessionDAO.accessSessionTable(sessionTable);
            sessionDAO.accessSessionTable(testSession1);
            sessionDAO.accessSessionTable(testSession2);

            logger.info("Finishing Database Reset Script");
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with Database Reset Script with error: " + error.getMessage());
        }
    }
}
