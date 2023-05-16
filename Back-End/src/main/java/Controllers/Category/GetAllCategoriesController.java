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
import java.util.List;

public class GetAllCategoriesController {
    public static Logger logger = LogManager.getLogger(GetAllCategoriesController.class);
    CategoryDALImplementation categoryDAO = new CategoryDALImplementation();
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    CategorySALImplementation categorySAO = new CategorySALImplementation(categoryDAO, employeeSAO);
    SessionDALImplementation sessionDAO = new SessionDALImplementation();
    SessionSALImplementation sessionSAO = new SessionSALImplementation(sessionDAO, employeeSAO);
    public Handler getAllCategories = ctx -> {
        try {
            int sessionId = Integer.parseInt(ctx.pathParam("sessionId"));
            logger.info("Beginning API handler get all categories with info: " + sessionId);
            Session currentSession = sessionSAO.getSession(sessionId);
            List<Category> categoryList = categorySAO.getAllCategories(currentSession.getEmployeeId());
            Session updatedSessionInfo = new Session(currentSession.getSessionId(), currentSession.getEmployeeId(),
                    LocalDateTime.now().plusMinutes(15));
            sessionSAO.updateSession(updatedSessionInfo);
            Gson gson = new Gson();
            String categoryListJSON = gson.toJson(categoryList);
            ctx.result(categoryListJSON);
            ctx.status(200);
            logger.info("Finishing API handler get all categories with result: " + categoryListJSON);
        } catch (GeneralError error) {
            Gson gson = new Gson();
            HashMap<String, String> errorDictionary = new HashMap<>();
            errorDictionary.put("message", error.getMessage());
            String errorJSON = gson.toJson(errorDictionary);
            ctx.result(errorJSON);
            ctx.status(400);
            logger.error("Error with API handler get all categories with error: " + error.getMessage());
        }
    };
}
