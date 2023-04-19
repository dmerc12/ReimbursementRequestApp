package Controllers.Employee;

import DAL.EmployeeDAL.EmployeeDALImplementation;
import DAL.SessionDAL.SessionDALImplementation;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Employee;
import Entities.Data.Session;
import Entities.Requests.Employee.LoginRequest;
import SAL.EmployeeSAL.EmployeeSALImplementation;
import SAL.SessionSAL.SessionSALImplementation;
import com.google.gson.Gson;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.HashMap;

public class LoginController {
    public static Logger logger = LogManager.getLogger(LoginController.class);
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    SessionDALImplementation sessionDAO = new SessionDALImplementation();
    SessionSALImplementation sessionSAO = new SessionSALImplementation(sessionDAO, employeeSAO);
    public Handler login = ctx -> {
        try {
            String requestBody = ctx.body();
            logger.info("Beginning API handler login with info: " + requestBody);
            Gson gson = new Gson();
            LoginRequest employeeInfo = gson.fromJson(requestBody, LoginRequest.class);
            Employee employee = employeeSAO.login(employeeInfo.getEmail(), employeeInfo.getPassword());
            Session newSession = new Session(0, employee.getEmployeeId(), LocalDateTime.now().plusMinutes(1));
            Session createdSession = sessionSAO.addSession(newSession);
            HashMap<String, Integer> sessionIdDictionary = new HashMap<>();
            sessionIdDictionary.put("sessionId", createdSession.getSessionId());
            String sessionJSON = gson.toJson(sessionIdDictionary);
            ctx.result(sessionJSON);
            ctx.status(HttpStatus.OK);
            logger.info("Finishing API handler login with result: " + sessionJSON);
        } catch (GeneralError error) {
            Gson gson = new Gson();
            HashMap<String, String> errorDictionary = new HashMap<>();
            errorDictionary.put("message", error.getMessage());
            String errorJSON = gson.toJson(errorDictionary);
            ctx.result(errorJSON);
            ctx.status(HttpStatus.BAD_REQUEST);
            logger.error("Error with API handler login with error: " + error.getMessage());
        }
    };
}
