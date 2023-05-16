package Controllers.Employee;

import DAL.EmployeeDAL.EmployeeDALImplementation;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Employee;
import SAL.EmployeeSAL.EmployeeSALImplementation;
import com.google.gson.Gson;
import io.javalin.http.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class RegisterController {
    public static Logger logger = LogManager.getLogger(RegisterController.class);
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    public Handler register = ctx -> {
        try {
            String requestBody = ctx.body();
            logger.info("Beginning API handler register with info: " + requestBody);
            Gson gson = new Gson();
            Employee employeeRequestInformation = gson.fromJson(requestBody, Employee.class);
            Employee employeeInformation = new Employee(0, employeeRequestInformation.getFirstName(),
                    employeeRequestInformation.getLastName(), employeeRequestInformation.getEmail(),
                    employeeRequestInformation.getPassword(), employeeRequestInformation.getPhoneNumber(),
                    employeeRequestInformation.getAddress());
            Employee employee = employeeSAO.addEmployee(employeeInformation);
            String createdEmployee = gson.toJson(employee);
            ctx.result(createdEmployee);
            ctx.status(201);
            logger.info("Finishing API handler register with result: " + createdEmployee);
        } catch (GeneralError error) {
            Gson gson = new Gson();
            HashMap<String, String> errorDictionary = new HashMap<>();
            errorDictionary.put("message", error.getMessage());
            String errorJSON = gson.toJson(errorDictionary);
            ctx.result(errorJSON);
            ctx.status(400);
            logger.error("Error with API handler register with error: " + error.getMessage());
        }
    };
}
