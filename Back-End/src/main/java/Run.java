import Controllers.Category.*;
import Controllers.Employee.*;
import Controllers.Request.AddRequestController;
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
        ChangePasswordController changePasswordController = new ChangePasswordController();

        app.post("/login/now", loginController.login);
        app.post("/register/now", registerController.register);
        app.patch("/get/employee", getEmployeeController.getEmployee);
        app.put("/update/employee/now", updateEmployeeController.updateEmployee);
        app.patch("/change/password/now", changePasswordController.changePassword);
        app.delete("/delete/employee/now", deleteEmployeeController.deleteEmployee);

        // Category controllers and routes:
        AddCategoryController addCategoryController = new AddCategoryController();
        GetAllCategoriesController getAllCategoriesController = new GetAllCategoriesController();
        GetCategoryController getCategoryController = new GetCategoryController();
        UpdateCategoryController updateCategoryController = new UpdateCategoryController();
        DeleteCategoryController deleteCategoryController = new DeleteCategoryController();

        app.post("/create/category/now", addCategoryController.addCategory);
        app.get("/get/all/categories/:sessionId", getAllCategoriesController.getAllCategories);
        app.get("/get/category/:categoryId/:sessionId", getCategoryController.getCategory);
        app.put("/update/category/now", updateCategoryController.updateCategory);
        app.delete("/delete/category/:categoryId/:sessionId", deleteCategoryController.deleteCategory);

        // Request Controllers and routes:
        AddRequestController addRequestController = new AddRequestController();

        app.post("/create/request/now", addRequestController.addRequest);

        logger.info("Application running...");
        app.start(8080);
    }
}
