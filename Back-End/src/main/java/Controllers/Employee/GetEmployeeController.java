package Controllers.Employee;

import DAL.EmployeeDAL.EmployeeDALImplementation;
import DAL.SessionDAL.SessionDALImplementation;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Employee;
import Entities.Data.Session;
import Entities.Requests.Session.SessionRequest;
import SAL.EmployeeSAL.EmployeeSALImplementation;
import SAL.SessionSAL.SessionSALImplementation;
import com.google.gson.Gson;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.HashMap;

public class GetEmployeeController {
    public static Logger logger = LogManager.getLogger(GetEmployeeController.class);
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    SessionDALImplementation sessionDAO = new SessionDALImplementation();
    SessionSALImplementation sessionSAO = new SessionSALImplementation(sessionDAO, employeeSAO);
    public Handler getEmployee = ctx -> {
        try {
            String requestBody = ctx.body();
            logger.info("Beginning API handler get employee with info: " + requestBody);
            Gson gson = new Gson();
            SessionRequest session = gson.fromJson(requestBody, SessionRequest.class);
            Session currentSession = sessionSAO.getSession(session.getSessionId());
            Employee employee = employeeSAO.getEmployeeById(currentSession.getEmployeeId());
            Session updatedSessionInfo = new Session(currentSession.getSessionId(), currentSession.getEmployeeId(),
                    LocalDateTime.now().plusMinutes(15));
            sessionSAO.updateSession(updatedSessionInfo);
            String employeeJson = gson.toJson(employee);
            ctx.result(employeeJson);
            ctx.status(HttpStatus.OK);
            logger.info("Finishing API handler get employee with result: " + employeeJson);
        } catch (GeneralError error) {
            Gson gson = new Gson();
            HashMap<String, String> errorDictionary = new HashMap<>();
            errorDictionary.put("message", error.getMessage());
            String errorJSON = gson.toJson(errorDictionary);
            ctx.result(errorJSON);
            ctx.status(HttpStatus.BAD_REQUEST);
            logger.error("Error with API handler get employee with error: " + error.getMessage());
        }
    };
}
