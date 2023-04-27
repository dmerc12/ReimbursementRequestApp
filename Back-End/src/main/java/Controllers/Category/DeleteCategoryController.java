package Controllers.Category;

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
import io.javalin.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class DeleteCategoryController {
    public static Logger logger = LogManager.getLogger(GetCategoryController.class);
    CategoryDALImplementation categoryDAO = new CategoryDALImplementation();
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    CategorySALImplementation categorySAO = new CategorySALImplementation(categoryDAO, employeeSAO);
    RequestDALImplementation requestDAO = new RequestDALImplementation();
    RequestSALImplementation requestSAO = new RequestSALImplementation(requestDAO, employeeSAO, categorySAO);
    SessionDALImplementation sessionDAO = new SessionDALImplementation();
    SessionSALImplementation sessionSAO = new SessionSALImplementation(sessionDAO, employeeSAO);
    public Handler deleteCategory = ctx -> {
        try {
            int categoryId = Integer.parseInt(ctx.pathParam("categoryId"));
            int sessionId = Integer.parseInt(ctx.pathParam("sessionId"));
            logger.info("Beginning API handler delete category with info: " + categoryId + ", " + sessionId);
            Gson gson = new Gson();
            Session currentSession = sessionSAO.getSession(sessionId);
            List<Request> allEmployeeRequests = requestSAO.getAllRequests(currentSession.getEmployeeId());
            for (Request request : allEmployeeRequests) {
                if (request.getCategoryId() == categoryId) {
                    throw new GeneralError("This category is being used in a request elsewhere and cannot be " +
                            "deleted, please try again!");
                }
            }
            int result = categorySAO.deleteCategory(categoryId);
            Session updatedSessionInfo = new Session(currentSession.getSessionId(), currentSession.getEmployeeId(),
                    LocalDateTime.now().plusMinutes(15));
            sessionSAO.updateSession(updatedSessionInfo);
            String resultJSON = gson.toJson(result);
            ctx.result(resultJSON);
            ctx.status(HttpStatus.OK);
            logger.info("Finishing API handler delete category");
        } catch (GeneralError error) {
            Gson gson = new Gson();
            HashMap<String, String> errorDictionary = new HashMap<>();
            errorDictionary.put("message", error.getMessage());
            String errorJSON = gson.toJson(errorDictionary);
            ctx.result(errorJSON);
            ctx.status(HttpStatus.BAD_REQUEST);
            logger.error("Error with API handler delete category with error: " + error.getMessage());
        }
    };
}
