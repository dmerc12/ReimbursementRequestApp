package Controllers.Category;

import DAL.CategoryDAL.CategoryDALImplementation;
import DAL.EmployeeDAL.EmployeeDALImplementation;
import DAL.SessionDAL.SessionDALImplementation;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Category;
import Entities.Data.Session;
import SAL.CategorySAL.CategorySALImplementation;
import SAL.EmployeeSAL.EmployeeSALImplementation;
import SAL.SessionSAL.SessionSALImplementation;
import com.google.gson.Gson;
import io.javalin.http.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.HashMap;

public class GetCategoryController {
    public static Logger logger = LogManager.getLogger(GetCategoryController.class);
    CategoryDALImplementation categoryDAO = new CategoryDALImplementation();
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    CategorySALImplementation categorySAO = new CategorySALImplementation(categoryDAO, employeeSAO);
    SessionDALImplementation sessionDAO = new SessionDALImplementation();
    SessionSALImplementation sessionSAO = new SessionSALImplementation(sessionDAO, employeeSAO);
    public Handler getCategory = ctx -> {
        try {
            int categoryId = Integer.parseInt(ctx.pathParam("categoryId"));
            int sessionId = Integer.parseInt(ctx.pathParam("sessionId"));
            logger.info("Beginning API handler get category with info: " + categoryId + ", " + sessionId);
            Session currentSession = sessionSAO.getSession(sessionId);
            Category category = categorySAO.getCategory(categoryId);
            Session updatedSessionInfo = new Session(currentSession.getSessionId(), currentSession.getEmployeeId(),
                    LocalDateTime.now().plusMinutes(15));
            sessionSAO.updateSession(updatedSessionInfo);
            Gson gson = new Gson();
            String categoryJSON = gson.toJson(category);
            ctx.result(categoryJSON);
            ctx.status(200);
        } catch (GeneralError error) {
            Gson gson = new Gson();
            HashMap<String, String> errorDictionary = new HashMap<>();
            errorDictionary.put("message", error.getMessage());
            String errorJSON = gson.toJson(errorDictionary);
            ctx.result(errorJSON);
            ctx.status(400);
            logger.error("Error with API handler get category with error: " + error.getMessage());
        }
    };
}