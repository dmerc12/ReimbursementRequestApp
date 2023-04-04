import Controllers.Employee.RegisterController;
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

        app.post("/login/now", loginController.login);
        app.post("/register/now", registerController.register);

        app.start();
    }
}
