package Controllers.Employee;
import DAL.SessionDAL.SessionDALImplementation;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Employee;
import Entities.Data.Session;
import Entities.Requests.Employee.UpdateEmployeeRequest;
import SAL.SessionSAL.SessionSALImplementation;
import com.google.gson.Gson;
import io.javalin.http.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import DAL.EmployeeDAL.EmployeeDALImplementation;
import SAL.EmployeeSAL.EmployeeSALImplementation;

import java.time.LocalDateTime;
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
            Session updatedSessionInfo = new Session(currentSession.getSessionId(), currentSession.getEmployeeId(),
                    LocalDateTime.now().plusMinutes(15));
            sessionSAO.updateSession(updatedSessionInfo);
            String updatedEmployeeJSON = gson.toJson(updatedEmployee);
            ctx.result(updatedEmployeeJSON);
            ctx.status(200);
            logger.info("Finishing API handler update employee with result: " + updatedEmployeeJSON);
        } catch (GeneralError error) {
            Gson gson = new Gson();
            HashMap<String, String> errorDictionary = new HashMap<>();
            errorDictionary.put("message", error.getMessage());
            String errorJSON = gson.toJson(errorDictionary);
            ctx.result(errorJSON);
            ctx.status(400);
            logger.error("Error with API handler update employee with error: " + error.getMessage());
        }
    };
}
