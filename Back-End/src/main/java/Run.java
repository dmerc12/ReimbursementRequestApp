import Controllers.Category.*;
import Controllers.Employee.*;
import Controllers.Request.AddRequestController;
import Controllers.Request.DeleteRequestController;
import Controllers.Request.GetAllRequestsController;
import Controllers.Request.UpdateRequestController;
import io.javalin.Javalin;
import io.javalin.plugin.bundled.CorsPluginConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Run {
    public static Logger logger = LogManager.getLogger(Run.class);
    public static void main(String[] args) {
        logger.info("Application starting up...");
        try (Javalin app = Javalin.create(config -> {
            //config.plugins.enableDevLogging();
            config.plugins.enableCors(cors -> {
                cors.add(CorsPluginConfig::anyHost);
            });
        })) {

            // Employee controllers and routes:
            LoginController loginController = new LoginController();
            RegisterController registerController = new RegisterController();
            GetEmployeeController getEmployeeController = new GetEmployeeController();
            UpdateEmployeeController updateEmployeeController = new UpdateEmployeeController();
            DeleteEmployeeController deleteEmployeeController = new DeleteEmployeeController();
            ChangePasswordController changePasswordController = new ChangePasswordController();

            app.post("/api/login", loginController.login);
            app.post("/api/register", registerController.register);
            app.patch("/api/get/employee", getEmployeeController.getEmployee);
            app.put("/api/update/employee", updateEmployeeController.updateEmployee);
            app.patch("/api/change/password", changePasswordController.changePassword);
            app.delete("/api/delete/employee", deleteEmployeeController.deleteEmployee);

            // Category controllers and routes:
            AddCategoryController addCategoryController = new AddCategoryController();
            GetAllCategoriesController getAllCategoriesController = new GetAllCategoriesController();
            UpdateCategoryController updateCategoryController = new UpdateCategoryController();
            DeleteCategoryController deleteCategoryController = new DeleteCategoryController();

            app.post("/api/create/category", addCategoryController.addCategory);
            app.get("/api/get/all/categories/{sessionId}", getAllCategoriesController.getAllCategories);
            app.put("/api/update/category", updateCategoryController.updateCategory);
            app.delete("/api/delete/category/{categoryId}/{sessionId}", deleteCategoryController.deleteCategory);

            // Request Controllers and routes:
            AddRequestController addRequestController = new AddRequestController();
            GetAllRequestsController getAllRequestsController = new GetAllRequestsController();
            UpdateRequestController updateRequestController = new UpdateRequestController();
            DeleteRequestController deleteRequestController = new DeleteRequestController();

            app.post("/api/create/request", addRequestController.addRequest);
            app.get("/api/get/all/requests/{sessionId}", getAllRequestsController.getAllRequests);
            app.put("/api/update/request", updateRequestController.updateRequest);
            app.delete("/api/delete/request/{requestId}/{sessionId}", deleteRequestController.deleteRequest);

            logger.info("Application running...");
            app.start(8080);
        }
    }
}
