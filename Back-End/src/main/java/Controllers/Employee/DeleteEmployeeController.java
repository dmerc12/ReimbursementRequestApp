package Controllers.Employee;

import DAL.CategoryDAL.CategoryDALImplementation;
import DAL.EmployeeDAL.EmployeeDALImplementation;
import DAL.SessionDAL.SessionDALImplementation;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Session;
import Entities.Requests.Session.SessionRequest;
import SAL.CategorySAL.CategorySALImplementation;
import SAL.EmployeeSAL.EmployeeSALImplementation;
import SAL.SessionSAL.SessionSALImplementation;
import com.google.gson.Gson;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class DeleteEmployeeController {
    public static Logger logger = LogManager.getLogger(DeleteEmployeeController.class);
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    SessionDALImplementation sessionDAO = new SessionDALImplementation();
    SessionSALImplementation sessionSAO = new SessionSALImplementation(sessionDAO, employeeSAO);
    CategoryDALImplementation categoryDAO = new CategoryDALImplementation();
    CategorySALImplementation categorySAO = new CategorySALImplementation(categoryDAO, employeeSAO);

    public Handler deleteEmployee = ctx -> {
        try {
            String requestBody = ctx.body();
            logger.info("Beginning API handler delete employee with info: " + requestBody);
            Gson gson = new Gson();
            SessionRequest sessionId = gson.fromJson(requestBody, SessionRequest.class);
            Session currentSession = sessionSAO.getSession(sessionId.getSessionId());
            categorySAO.deleteAllCategories(currentSession.getEmployeeId());
            int result = employeeSAO.deleteEmployee(currentSession.getEmployeeId());
            String resultJSON = gson.toJson(result);
            ctx.result(resultJSON);
            ctx.status(HttpStatus.OK);
            logger.info("Finishing API handler delete employee with result: " + resultJSON);
        } catch (GeneralError error) {
            Gson gson = new Gson();
            HashMap<String, String> errorDictionary = new HashMap<>();
            errorDictionary.put("message", error.getMessage());
            String errorJSON = gson.toJson(errorDictionary);
            ctx.result(errorJSON);
            ctx.status(HttpStatus.BAD_REQUEST);
            logger.error("Error with API handler delete employee with error: " + error.getMessage());
        }
    };
}
