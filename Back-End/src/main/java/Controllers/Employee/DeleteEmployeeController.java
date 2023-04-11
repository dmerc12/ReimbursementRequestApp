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

public class DeleteEmployeeController {
    public static Logger logger = LogManager.getLogger(DeleteEmployeeController.class);
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);

    public Handler deleteEmployee = ctx -> {
        try {
            String requestBody = ctx.body();
            logger.info("Beginning API handler delete employee with info: " + requestBody);
            Gson gson = new Gson();
            Employee employeeId = gson.fromJson(requestBody, Employee.class);
            int result = employeeSAO.deleteEmployee(employeeId.getEmployeeId());
            String resultJSON = gson.toJson(result);
            ctx.result(resultJSON);
            ctx.status(HttpStatus.OK);
            logger.info("Finishing API handler delete employee with result: " + resultJSON);
        } catch (GeneralError error) {
            ctx.result(error.getMessage());
            ctx.status(HttpStatus.BAD_REQUEST);
            logger.error("Error with API handler delete employee with error: " + error.getMessage());
        }
    };
}
