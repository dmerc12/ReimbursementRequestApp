package Controllers.Employee;

import DAL.EmployeeDAL.EmployeeDALImplementation;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Employee;
import SAL.EmployeeSAL.EmployeeSALImplementation;
import com.google.gson.Gson;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetEmployeeController {
    public static Logger logger = LogManager.getLogger(GetEmployeeController.class);
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    public Handler getEmployee = ctx -> {
        try {
            logger.info("Beginning API handler get employee with info: " + ctx);
            int employeeId = Integer.parseInt(ctx.pathParam("employeeId"));
            Employee employee = employeeSAO.getEmployeeById(employeeId);
            Gson gson = new Gson();
            String employeeJson = gson.toJson(employee);
            ctx.result(employeeJson);
            ctx.status(HttpStatus.OK);
            logger.info("Finishing API handler get employee with result: " + employeeJson);
        } catch (GeneralError error) {
            ctx.result(error.getMessage());
            ctx.status(HttpStatus.BAD_REQUEST);
            logger.error("Error with API handler get employee with error: " + error.getMessage());
        }
    };
}
