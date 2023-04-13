package Controllers.Employee;
import DAL.SessionDAL.SessionDALImplementation;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Employee;
import Entities.Data.Session;
import Entities.Requests.UpdateEmployeeRequest;
import SAL.SessionSAL.SessionSALImplementation;
import com.google.gson.Gson;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import DAL.EmployeeDAL.EmployeeDALImplementation;
import SAL.EmployeeSAL.EmployeeSALImplementation;

import java.util.HashMap;

public class UpdateEmployeeController {
    public static Logger logger = LogManager.getLogger(UpdateEmployeeController.class);
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    SessionDALImplementation sessionDAO = new SessionDALImplementation();
    SessionSALImplementation sessionSAO = new SessionSALImplementation(sessionDAO, employeeSAO);

    public Handler updateEmployee = ctx -> {
        try {
            String requestBody = ctx.body();
            logger.info("Beginning API handler update employee with info: " + requestBody);
            Gson gson = new Gson();
            UpdateEmployeeRequest updatedEmployeeInfoRequest = gson.fromJson(requestBody, UpdateEmployeeRequest.class);
            Session currentSession = sessionSAO.getSession(updatedEmployeeInfoRequest.getSessionId());
            Employee employee = employeeSAO.getEmployeeById(currentSession.getEmployeeId());
            Employee updateEmployeeInfo = new Employee(employee.getEmployeeId(),
                    updatedEmployeeInfoRequest.getFirstName(), updatedEmployeeInfoRequest.getLastName(),
                    updatedEmployeeInfoRequest.getEmail(), employee.getPassword(),
                    updatedEmployeeInfoRequest.getPhoneNumber(), updatedEmployeeInfoRequest.getAddress());
            Employee updatedEmployee = employeeSAO.updateEmployee(updateEmployeeInfo);
            String updatedEmployeeJSON = gson.toJson(updatedEmployee);
            ctx.result(updatedEmployeeJSON);
            ctx.status(HttpStatus.OK);
            logger.info("Finishing API handler update employee with result: " + updatedEmployeeJSON);
        } catch (GeneralError error) {
            Gson gson = new Gson();
            HashMap<String, String> errorDictionary = new HashMap<>();
            errorDictionary.put("message", error.getMessage());
            String errorJSON = gson.toJson(errorDictionary);
            ctx.result(errorJSON);
            ctx.status(HttpStatus.BAD_REQUEST);
            logger.error("Error with API handler update employee with error: " + error.getMessage());
        }
    };
}
