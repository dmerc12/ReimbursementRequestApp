package Controllers.Employee;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Employee;
import com.google.gson.Gson;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import DAL.EmployeeDAL.EmployeeDALImplementation;
import SAL.EmployeeSAL.EmployeeSALImplementation;

public class UpdateEmployeeController {
    public static Logger logger = LogManager.getLogger(UpdateEmployeeController.class);
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);

    public Handler updateEmployee = ctx -> {
        try {
            logger.info("Beginning API handler update employee with info: " + ctx.body());
            String requestBody = ctx.body();
            Gson gson = new Gson();
            Employee updatedEmployeeInfo = gson.fromJson(requestBody, Employee.class);
            Employee updatedEmployee = employeeSAO.updateEmployee(updatedEmployeeInfo);
            String updatedEmployeeJSON = gson.toJson(updatedEmployee);
            ctx.result(updatedEmployeeJSON);
            ctx.status(HttpStatus.OK);
            logger.info("Finishing API handler update employee with result: " + updatedEmployeeJSON);
        } catch (GeneralError error) {
            ctx.result(error.getMessage());
            ctx.status(HttpStatus.BAD_REQUEST);
            logger.error("Error with API handler update employee with error: " + error.getMessage());
        }
    };
}
