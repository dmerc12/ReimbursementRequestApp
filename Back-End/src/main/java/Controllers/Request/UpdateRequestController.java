package Controllers.Request;

import DAL.CategoryDAL.CategoryDALImplementation;
import DAL.EmployeeDAL.EmployeeDALImplementation;
import DAL.RequestDAL.RequestDALImplementation;
import DAL.SessionDAL.SessionDALImplementation;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Request;
import Entities.Data.Session;
import Entities.Requests.Request.UpdateRequest;
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

public class UpdateRequestController {
    public static Logger logger = LogManager.getLogger(UpdateRequestController.class);
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    CategoryDALImplementation categoryDAO = new CategoryDALImplementation();
    CategorySALImplementation categorySAO = new CategorySALImplementation(categoryDAO, employeeSAO);
    SessionDALImplementation sessionDAO = new SessionDALImplementation();
    SessionSALImplementation sessionSAO = new SessionSALImplementation(sessionDAO, employeeSAO);
    RequestDALImplementation requestDAO = new RequestDALImplementation();
    RequestSALImplementation requestSAO = new RequestSALImplementation(requestDAO, employeeSAO, categorySAO);
    public Handler updateRequest = ctx -> {
        try {
            String requestBody = ctx.body();
            logger.info("Beginning API handler update request with info: " + requestBody);
            Gson gson = new Gson();
            UpdateRequest updateRequestInformation = gson.fromJson(requestBody, UpdateRequest.class);
            Session currentSession = sessionSAO.getSession(updateRequestInformation.getSessionId());
            Request currentRequest = requestSAO.getRequest(updateRequestInformation.getRequestId());
            Request requestToBeUpdated = new Request(currentRequest.getRequestId(), currentSession.getEmployeeId(),
                    updateRequestInformation.getCategoryId(), updateRequestInformation.getComment(),
                    updateRequestInformation.getAmount());
            Request updatedRequest = requestSAO.updateRequest(requestToBeUpdated);
            Session updatedSessionInfo = new Session(currentSession.getSessionId(), currentSession.getEmployeeId(),
                    LocalDateTime.now().plusMinutes(15));
            sessionSAO.updateSession(updatedSessionInfo);
            String updatedRequestJSON = gson.toJson(updatedRequest);
            ctx.result(updatedRequestJSON);
            ctx.status(200);
            logger.info("Finishing API handler update request with result: " + updatedRequestJSON);
        } catch (GeneralError error) {
            Gson gson = new Gson();
            HashMap<String, String> errorDictionary = new HashMap<>();
            errorDictionary.put("message", error.getMessage());
            String errorJSON = gson.toJson(errorDictionary);
            ctx.result(errorJSON);
            ctx.status(400);
            logger.error("Error with API handler update request with error: " + error.getMessage());
        }
    };
}
