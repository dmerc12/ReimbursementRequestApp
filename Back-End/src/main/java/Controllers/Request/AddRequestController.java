package Controllers.Request;

import DAL.CategoryDAL.CategoryDALImplementation;
import DAL.EmployeeDAL.EmployeeDALImplementation;
import DAL.RequestDAL.RequestDALImplementation;
import DAL.SessionDAL.SessionDALImplementation;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Request;
import Entities.Data.Session;
import Entities.Requests.Request.RequestToAddRequest;
import SAL.CategorySAL.CategorySALImplementation;
import SAL.EmployeeSAL.EmployeeSALImplementation;
import SAL.RequestSAL.RequestSALImplementation;
import SAL.SessionSAL.SessionSALImplementation;
import com.google.gson.Gson;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.HashMap;

public class AddRequestController {
    public static Logger logger = LogManager.getLogger(AddRequestController.class);
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    CategoryDALImplementation categoryDAO = new CategoryDALImplementation();
    CategorySALImplementation categorySAO = new CategorySALImplementation(categoryDAO, employeeSAO);
    SessionDALImplementation sessionDAO = new SessionDALImplementation();
    SessionSALImplementation sessionSAO = new SessionSALImplementation(sessionDAO, employeeSAO);
    RequestDALImplementation requestDAO = new RequestDALImplementation();
    RequestSALImplementation requestSAO = new RequestSALImplementation(requestDAO, employeeSAO, categorySAO);
    public Handler addRequest = ctx -> {
        try {
            String requestBody = ctx.body();
            logger.info("Beginning API handler add request with info: " + requestBody);
            Gson gson = new Gson();
            RequestToAddRequest requestedRequestInformation = gson.fromJson(requestBody, RequestToAddRequest.class);
            Session currentSession = sessionSAO.getSession(requestedRequestInformation.getSessionId());
            Request requestInformation = new Request(0, currentSession.getEmployeeId(),
                    requestedRequestInformation.getCategoryId(), requestedRequestInformation.getComment(),
                    requestedRequestInformation.getAmount());
            Request createdRequest = requestSAO.addRequest(requestInformation);
            Session updatedSessionInfo = new Session(currentSession.getSessionId(), currentSession.getEmployeeId(),
                    LocalDateTime.now().plusMinutes(15));
            sessionSAO.updateSession(updatedSessionInfo);
            String requestJSON = gson.toJson(createdRequest);
            ctx.result(requestJSON);
            ctx.status(HttpStatus.CREATED);
            logger.info("Finishing API handler add request with result: " + requestJSON);
        } catch (GeneralError error) {
            Gson gson = new Gson();
            HashMap<String, String> errorDictionary = new HashMap<>();
            errorDictionary.put("message", error.getMessage());
            String errorJSON = gson.toJson(errorDictionary);
            ctx.result(errorJSON);
            ctx.status(HttpStatus.BAD_REQUEST);
            logger.error("Error with API handler add request with error: " + error.getMessage());
        }
    };
}
