import Controllers.Category.AddCategoryController;
import Controllers.Category.GetAllCategoriesController;
import Controllers.Category.GetCategoryController;
import Controllers.Category.UpdateCategoryController;
import Controllers.Employee.*;
import io.javalin.Javalin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Run {
    public static Logger logger = LogManager.getLogger(Run.class);
    public static void main(String[] args) {
        logger.info("Application starting up...");
        Javalin app = Javalin.create();

        // Employee controllers and routes:
        LoginController loginController = new LoginController();
        RegisterController registerController = new RegisterController();
        GetEmployeeController getEmployeeController = new GetEmployeeController();
        UpdateEmployeeController updateEmployeeController = new UpdateEmployeeController();
        DeleteEmployeeController deleteEmployeeController = new DeleteEmployeeController();

        app.post("/login/now", loginController.login);
        app.post("/register/now", registerController.register);
        app.get("/get/employee/{sessionId}", getEmployeeController.getEmployee);
        app.put("/update/employee/now", updateEmployeeController.updateEmployee);
        app.delete("/delete/employee/now", deleteEmployeeController.deleteEmployee);

        // Category controllers and routes:
        AddCategoryController addCategoryController = new AddCategoryController();
        GetAllCategoriesController getAllCategoriesController = new GetAllCategoriesController();
        GetCategoryController getCategoryController = new GetCategoryController();
        UpdateCategoryController updateCategoryController = new UpdateCategoryController();

        app.post("/create/category/now", addCategoryController.addCategory);
        app.get("/get/all/categories/{sessionId}", getAllCategoriesController.getAllCategories);
        app.get("/get/category/{categoryId}/{sessionId}", getCategoryController.getCategory);
        app.put("/update/category/now", updateCategoryController.updateCategory);

        // Request Controllers and routes:

        logger.info("Application running...");
        app.start();
    }
}
