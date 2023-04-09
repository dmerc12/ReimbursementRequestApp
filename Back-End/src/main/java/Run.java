import Controllers.Employee.*;
import io.javalin.Javalin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Run {
    public static Logger logger = LogManager.getLogger(Run.class);
    public static void main(String[] args) {
        logger.info("Application starting...");
        Javalin app = Javalin.create();

        LoginController loginController = new LoginController();
        RegisterController registerController = new RegisterController();
        GetEmployeeController getEmployeeController = new GetEmployeeController();
        UpdateEmployeeController updateEmployeeController = new UpdateEmployeeController();
        DeleteEmployeeController deleteEmployeeController = new DeleteEmployeeController();

        app.post("/login/now", loginController.login);
        app.post("/register/now", registerController.register);
        app.get("/get/employee/{employeeId}", getEmployeeController.getEmployee);
        app.post("/update/employee/now", updateEmployeeController.updateEmployee);
        app.delete("/delete/employee/now", deleteEmployeeController.deleteEmployee);

        app.start();
    }
}
