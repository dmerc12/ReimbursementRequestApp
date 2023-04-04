package Controllers.Main;

import DAL.EmployeeDAL.EmployeeDALImplementation;
import DAL.SessionDAL.SessionDALImplementation;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Employee;
import Entities.Data.Session;
import Entities.Requests.LoginRequest;
import SAL.EmployeeSAL.EmployeeSALImplementation;
import SAL.SessionSAL.SessionSALImplementation;
import com.google.gson.Gson;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

public class LoginController {
    public static Logger logger = LogManager.getLogger(LoginController.class);
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    SessionDALImplementation sessionDAO = new SessionDALImplementation();
    SessionSALImplementation sessionSAO = new SessionSALImplementation(sessionDAO, employeeSAO);
    public Handler login = ctx -> {
        try {
            logger.info("Beginning API handler login with info: " + ctx);
            String requestBody = ctx.body();
            Gson gson = new Gson();
            LoginRequest employeeInfo = gson.fromJson(requestBody, LoginRequest.class);
            Employee employee = employeeSAO.login(employeeInfo.getEmail(), employeeInfo.getPassword());
            Session newSession = new Session(0, employee.getEmployeeId(), LocalDateTime.now().plusMinutes(15));
            Session createdNewSession = sessionSAO.addSession(newSession);
            String sessionId = gson.toJson(createdNewSession.getSessionId());
            ctx.result(sessionId);
            ctx.status(HttpStatus.OK);
            logger.info("Finishing API handler login with result: " + sessionId);
        } catch (GeneralError error) {
            ctx.result(error.getMessage());
            ctx.status(HttpStatus.BAD_REQUEST);
            logger.error("Error with API handler login with error: " + error.getMessage());
        }
    };
}
