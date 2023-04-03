package com.example.Reimbursement.Request.App.API.Employee;
import com.example.Reimbursement.Request.App.DAL.EmployeeDAL.EmployeeDALImplementation;
import com.example.Reimbursement.Request.App.Entities.CustomExceptions.GeneralError;
import com.example.Reimbursement.Request.App.Entities.Data.Employee;
import com.example.Reimbursement.Request.App.Entities.Requests.EmployeeRequest;
import com.example.Reimbursement.Request.App.SAL.EmployeeSAL.EmployeeSALImplementation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    public static Logger logger = LogManager.getLogger(RegisterController.class);
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);

    @PostMapping("/register/now")
    public ResponseEntity<?> register(@RequestBody EmployeeRequest employeeInformation) {
        try {
            logger.info("Beginning API method register with information: " + employeeInformation);
            Employee employee = new Employee(0, employeeInformation.getFirstName(),
                    employeeInformation.getLastName(), employeeInformation.getEmail(),
                    employeeInformation.getPassword(), employeeInformation.getPhoneNumber(),
                    employeeInformation.getAddress());
            Employee registeredEmployee = employeeSAO.addEmployee(employee);
            logger.info("Finishing API method register with result: " + registeredEmployee);
            return ResponseEntity.ok(registeredEmployee);
        } catch (GeneralError error) {
            ResponseEntity<String> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
            logger.error("Error with API method register with error response: " + response);
            return response;
        }
    }
}
