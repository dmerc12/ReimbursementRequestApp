package Controllers.Employee;

import DAL.EmployeeDAL.EmployeeDALImplementation;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Employee;
import Entities.Requests.LoginRequest;
import SAL.EmployeeSAL.EmployeeSALImplementation;
import com.google.gson.Gson;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class LoginController {
    public static Logger logger = LogManager.getLogger(LoginController.class);
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    public Handler login = ctx -> {
        try {
            String requestBody = ctx.body();
            logger.info("Beginning API handler login with info: " + requestBody);
            Gson gson = new Gson();
            LoginRequest employeeInfo = gson.fromJson(requestBody, LoginRequest.class);
            Employee employee = employeeSAO.login(employeeInfo.getEmail(), employeeInfo.getPassword());
            String employeeJSON = gson.toJson(employee);
            ctx.result(employeeJSON);
            ctx.status(200);
            logger.info("Finishing API handler login with result: " + employeeJSON);
        } catch (GeneralError error) {
            Gson gson = new Gson();
            HashMap<String, String> errorDictionary = new HashMap<>();
            errorDictionary.put("errorMessage", error.getMessage());
            String errorJSON = gson.toJson(errorDictionary);
            ctx.result(errorJSON);
            ctx.status(400);
            logger.error("Error with API handler login with error: " + error.getMessage());
        }
    };
}
