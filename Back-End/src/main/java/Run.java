import Controllers.Category.*;
import Controllers.Employee.*;
import Controllers.Request.AddRequestController;
import Controllers.Request.GetAllRequestsController;
import Controllers.Request.UpdateRequestController;
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
        UpdateCategoryController updateCategoryController = new UpdateCategoryController();
        DeleteCategoryController deleteCategoryController = new DeleteCategoryController();

        app.post("/create/category/now", addCategoryController.addCategory);
        app.get("/get/all/categories/:sessionId", getAllCategoriesController.getAllCategories);
        app.put("/update/category/now", updateCategoryController.updateCategory);
        app.delete("/delete/category/:categoryId/:sessionId", deleteCategoryController.deleteCategory);

        // Request Controllers and routes:
        AddRequestController addRequestController = new AddRequestController();
        GetAllRequestsController getAllRequestsController = new GetAllRequestsController();
        UpdateRequestController updateRequestController = new UpdateRequestController();

        app.post("/create/request/now", addRequestController.addRequest);
        app.get("/get/all/requests/:sessionId", getAllRequestsController.getAllRequests);
        app.put("/update/request/now", updateRequestController.updateRequest);

        logger.info("Application running...");
        app.start(8080);
    }
}
