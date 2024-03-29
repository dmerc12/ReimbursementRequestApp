package Controllers.Category;

import DAL.CategoryDAL.CategoryDALImplementation;
import DAL.EmployeeDAL.EmployeeDALImplementation;
import DAL.SessionDAL.SessionDALImplementation;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Category;
import Entities.Data.Session;
import Entities.Requests.Category.AddCategoryRequest;
import SAL.CategorySAL.CategorySALImplementation;
import SAL.EmployeeSAL.EmployeeSALImplementation;
import SAL.SessionSAL.SessionSALImplementation;
import com.google.gson.Gson;
import io.javalin.http.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.HashMap;

public class AddCategoryController {
    public static Logger logger = LogManager.getLogger(AddCategoryController.class);
    CategoryDALImplementation categoryDAO = new CategoryDALImplementation();
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    SessionDALImplementation sessionDAO = new SessionDALImplementation();
    SessionSALImplementation sessionSAO = new SessionSALImplementation(sessionDAO, employeeSAO);
    CategorySALImplementation categorySAO = new CategorySALImplementation(categoryDAO, employeeSAO);
    public Handler addCategory = ctx -> {
        try {
            String requestBody = ctx.body();
            logger.info("Beginning API handler add category with info: " + requestBody);
            Gson gson = new Gson();
            AddCategoryRequest requestedCategoryInformation = gson.fromJson(requestBody, AddCategoryRequest.class);
            Session currentSession = sessionSAO.getSession(requestedCategoryInformation.getSessionId());
            Category categoryInformation = new Category(0, currentSession.getEmployeeId(),
                    requestedCategoryInformation.getCategoryName());
            Category createdCategory = categorySAO.addCategory(categoryInformation);
            Session updatedSessionInfo = new Session(currentSession.getSessionId(), currentSession.getEmployeeId(),
                    LocalDateTime.now().plusMinutes(15));
            sessionSAO.updateSession(updatedSessionInfo);
            String categoryJSON = gson.toJson(createdCategory);
            ctx.result(categoryJSON);
            ctx.status(201);
            logger.info("Finishing API handler add category with result: " + categoryJSON);
        } catch (GeneralError error) {
            Gson gson = new Gson();
            HashMap<String, String> errorDictionary = new HashMap<>();
            errorDictionary.put("message", error.getMessage());
            String errorJSON = gson.toJson(errorDictionary);
            ctx.result(errorJSON);
            ctx.status(400);
            logger.error("Error with API handler add category with error: " + error.getMessage());
        }
    };
}
