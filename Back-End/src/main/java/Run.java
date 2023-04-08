import Controllers.Employee.GetEmployeeController;
import Controllers.Employee.RegisterController;
import Controllers.Employee.UpdateEmployeeController;
import Controllers.Main.LoginController;
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

        app.post("/login/now", loginController.login);
        app.post("/register/now", registerController.register);
        app.get("/get/employee/{employeeId}", getEmployeeController.getEmployee);
        app.post("/update/employee/now", updateEmployeeController.updateEmployee);

        app.start();
    }
}
