package Controllers.Request;

import DAL.CategoryDAL.CategoryDALImplementation;
import DAL.EmployeeDAL.EmployeeDALImplementation;
import DAL.RequestDAL.RequestDALImplementation;
import DAL.SessionDAL.SessionDALImplementation;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Request;
import Entities.Data.Session;
import SAL.CategorySAL.CategorySALImplementation;
import SAL.EmployeeSAL.EmployeeSALImplementation;
import SAL.RequestSAL.RequestSALImplementation;
import SAL.SessionSAL.SessionSALImplementation;
import com.google.gson.Gson;
import io.javalin.http.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.HashMap;

public class DeleteRequestController {
    public static Logger logger = LogManager.getLogger(DeleteRequestController.class);
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    CategoryDALImplementation categoryDAO = new CategoryDALImplementation();
    CategorySALImplementation categorySAO = new CategorySALImplementation(categoryDAO, employeeSAO);
    SessionDALImplementation sessionDAO = new SessionDALImplementation();
    SessionSALImplementation sessionSAO = new SessionSALImplementation(sessionDAO, employeeSAO);
    RequestDALImplementation requestDAO = new RequestDALImplementation();
    RequestSALImplementation requestSAO = new RequestSALImplementation(requestDAO, employeeSAO, categorySAO);
    public Handler deleteRequest = ctx -> {
        try {
            int requestId = Integer.parseInt(ctx.pathParam("requestId"));
            int sessionId = Integer.parseInt(ctx.pathParam("sessionId"));
            logger.info("Beginning API handler delete request with info: " + requestId + ", " + sessionId);
            Gson gson = new Gson();
            Session currentSession = sessionSAO.getSession(sessionId);
            int result = requestSAO.deleteRequest(requestId);
            Session updatedSessionInfo = new Session(currentSession.getSessionId(), currentSession.getEmployeeId(),
                    LocalDateTime.now().plusMinutes(15));
            sessionSAO.updateSession(updatedSessionInfo);
            String resultJSON = gson.toJson(result);
            ctx.result(resultJSON);
            ctx.status(200);
            logger.info("Finishing API handler delete request");

        } catch (GeneralError error) {
            Gson gson = new Gson();
            HashMap<String, String> errorDictionary = new HashMap<>();
            errorDictionary.put("message", error.getMessage());
            String errorJSON = gson.toJson(errorDictionary);
            ctx.result(errorJSON);
            ctx.status(400);
            logger.error("Error with API handler delete request with error: " + error.getMessage());
        }
    };
}
