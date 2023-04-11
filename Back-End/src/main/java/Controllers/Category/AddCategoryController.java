package Controllers.Category;

import DAL.CategoryDAL.CategoryDALImplementation;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Category;
import SAL.CategorySAL.CategorySALImplementation;
import com.google.gson.Gson;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddCategoryController {
    public static Logger logger = LogManager.getLogger(AddCategoryController.class);
    CategoryDALImplementation categoryDAO = new CategoryDALImplementation();
    CategorySALImplementation categorySAO = new CategorySALImplementation(categoryDAO);
    public Handler addCategory = ctx -> {
        try {
            String requestBody = ctx.body();
            logger.info("Beginning API handler add category with info: " + requestBody);
            Gson gson = new Gson();
            Category categoryName = gson.fromJson(requestBody, Category.class);
            Category categoryInformation = new Category(0, categoryName.getCategoryName());
            Category createdCategory = categorySAO.addCategory(categoryInformation);
            String category = gson.toJson(createdCategory);
            ctx.result(category);
            ctx.status(HttpStatus.CREATED);
            logger.info("Finishing API handler add category with result: " + category);
        } catch (GeneralError error) {
            ctx.result(error.getMessage());
            ctx.status(HttpStatus.BAD_REQUEST);
            logger.error("Error with API handler add category with error: " + error.getMessage());
        }
    };
}
