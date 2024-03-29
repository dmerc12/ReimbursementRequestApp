package Controllers.Category;

import DAL.CategoryDAL.CategoryDALImplementation;
import DAL.EmployeeDAL.EmployeeDALImplementation;
import DAL.SessionDAL.SessionDALImplementation;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Category;
import Entities.Data.Session;
import Entities.Requests.Category.UpdateCategoryRequest;
import SAL.CategorySAL.CategorySALImplementation;
import SAL.EmployeeSAL.EmployeeSALImplementation;
import SAL.SessionSAL.SessionSALImplementation;
import com.google.gson.Gson;
import io.javalin.http.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.HashMap;

public class UpdateCategoryController {
    public static Logger logger = LogManager.getLogger(AddCategoryController.class);
    CategoryDALImplementation categoryDAO = new CategoryDALImplementation();
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    SessionDALImplementation sessionDAO = new SessionDALImplementation();
    SessionSALImplementation sessionSAO = new SessionSALImplementation(sessionDAO, employeeSAO);
    CategorySALImplementation categorySAO = new CategorySALImplementation(categoryDAO, employeeSAO);
    public Handler updateCategory = ctx -> {
        try {
            String requestBody = ctx.body();
            logger.info("Beginning API handler update category with info: " + requestBody);
            Gson gson = new Gson();
            UpdateCategoryRequest updateCategoryInformation = gson.fromJson(requestBody, UpdateCategoryRequest.class);
            Session currentSession = sessionSAO.getSession(updateCategoryInformation.getSessionId());
            Category categoryToBeUpdated = new Category(updateCategoryInformation.getCategoryId(),
                    currentSession.getEmployeeId(), updateCategoryInformation.getCategoryName());
            Category updatedCategory = categorySAO.updateCategory(categoryToBeUpdated);
            Session updatedSessionInfo = new Session(currentSession.getSessionId(), currentSession.getEmployeeId(),
                    LocalDateTime.now().plusMinutes(15));
            sessionSAO.updateSession(updatedSessionInfo);
            String updatedCategoryJSON = gson.toJson(updatedCategory);
            ctx.result(updatedCategoryJSON);
            ctx.status(200);
            logger.info("Finishing API handler update category with result: " + updatedCategoryJSON);
        } catch (GeneralError error) {
            Gson gson = new Gson();
            HashMap<String, String> errorDictionary = new HashMap<>();
            errorDictionary.put("message", error.getMessage());
            String errorJSON = gson.toJson(errorDictionary);
            ctx.result(errorJSON);
            ctx.status(400);
            logger.error("Error with API handler update category with error: " + error.getMessage());
        }
    };
}
