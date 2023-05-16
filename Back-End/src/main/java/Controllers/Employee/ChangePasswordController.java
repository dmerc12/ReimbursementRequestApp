package Controllers.Employee;

import DAL.EmployeeDAL.EmployeeDALImplementation;
import DAL.SessionDAL.SessionDALImplementation;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Employee;
import Entities.Data.Session;
import Entities.Requests.Employee.PasswordChangeRequest;
import SAL.EmployeeSAL.EmployeeSALImplementation;
import SAL.SessionSAL.SessionSALImplementation;
import com.google.gson.Gson;
import io.javalin.http.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.HashMap;

public class ChangePasswordController {
    public static Logger logger = LogManager.getLogger(ChangePasswordController.class);
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    SessionDALImplementation sessionDAO = new SessionDALImplementation();
    SessionSALImplementation sessionSAO = new SessionSALImplementation(sessionDAO, employeeSAO);
    public Handler changePassword = ctx -> {
        try {
            String requestBody = ctx.body();
            logger.info("Beginning API handler change password with info: " + requestBody);
            Gson gson = new Gson();
            PasswordChangeRequest passwordChangeRequest = gson.fromJson(requestBody, PasswordChangeRequest.class);
            Session currentSession = sessionSAO.getSession(passwordChangeRequest.getSessionId());
            Employee currentEmployee = employeeSAO.getEmployeeById(currentSession.getEmployeeId());
            Employee updatedEmployeeInformationWithNewPassword = new Employee(currentEmployee.getEmployeeId(),
                    currentEmployee.getFirstName(), currentEmployee.getLastName(), currentEmployee.getEmail(),
                    passwordChangeRequest.getPassword(), currentEmployee.getPhoneNumber(),
                    currentEmployee.getAddress());
            Employee updatedEmployee = employeeSAO.changePassword(updatedEmployeeInformationWithNewPassword);
            Session updatedSessionInfo = new Session(currentSession.getSessionId(), currentSession.getEmployeeId(),
                    LocalDateTime.now().plusMinutes(15));
            sessionSAO.updateSession(updatedSessionInfo);
            String updatedEmployeeJSON = gson.toJson(updatedEmployee);
            ctx.result(updatedEmployeeJSON);
            ctx.status(200);
            logger.info("Finishing API handler change password with result: " + updatedEmployeeJSON);
        } catch (GeneralError error) {
            Gson gson = new Gson();
            HashMap<String, String> errorDictionary = new HashMap<>();
            errorDictionary.put("message", error.getMessage());
            String errorJSON = gson.toJson(errorDictionary);
            ctx.result(errorJSON);
            ctx.status(400);
            logger.error("Error with API handler change password with error: " + error.getMessage());
        }
    };
}
