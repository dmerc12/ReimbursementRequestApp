package Controllers.Employee;

import DAL.EmployeeDAL.EmployeeDALImplementation;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Employee;
import Entities.Requests.EmployeeRequest;
import SAL.EmployeeSAL.EmployeeSALImplementation;
import com.google.gson.Gson;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterController {
    public static Logger logger = LogManager.getLogger(RegisterController.class);
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    public Handler register = ctx -> {
        try {
            logger.info("Beginning API handler register with info: " + ctx.body());
            String requestBody = ctx.body();
            Gson gson = new Gson();
            EmployeeRequest employeeRequestInformation = gson.fromJson(requestBody, EmployeeRequest.class);
            Employee employeeInformation = new Employee(0, employeeRequestInformation.getFirstName(),
                    employeeRequestInformation.getLastName(), employeeRequestInformation.getEmail(),
                    employeeRequestInformation.getPassword(), employeeRequestInformation.getPhoneNumber(),
                    employeeRequestInformation.getAddress());
            Employee employee = employeeSAO.addEmployee(employeeInformation);
            String createdEmployee = gson.toJson(employee);
            ctx.result(createdEmployee);
            ctx.status(HttpStatus.CREATED);
            logger.info("Finishing API handler register with result: " + createdEmployee);
        } catch (GeneralError error) {
            ctx.result(error.getMessage());
            ctx.status(HttpStatus.BAD_REQUEST);
            logger.error("Error with API handler register with error: " + error.getMessage());
        }
    };
}
