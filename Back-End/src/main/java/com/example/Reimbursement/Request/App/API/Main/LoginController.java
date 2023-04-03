package com.example.Reimbursement.Request.App.API.Main;

import com.example.Reimbursement.Request.App.DAL.EmployeeDAL.EmployeeDALImplementation;
import com.example.Reimbursement.Request.App.DAL.SessionDAL.SessionDALImplementation;
import com.example.Reimbursement.Request.App.Entities.CustomExceptions.GeneralError;
import com.example.Reimbursement.Request.App.Entities.Requests.LoginRequest;
import com.example.Reimbursement.Request.App.Entities.Data.Session;
import com.example.Reimbursement.Request.App.SAL.EmployeeSAL.EmployeeSALImplementation;
import com.example.Reimbursement.Request.App.SAL.SessionSAL.SessionSALImplementation;
import com.example.Reimbursement.Request.App.Entities.Data.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    public static Logger logger = LogManager.getLogger(LoginController.class);

    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    SessionDALImplementation sessionDAO = new SessionDALImplementation();
    SessionSALImplementation sessionSAO = new SessionSALImplementation(sessionDAO, employeeSAO);

    @PostMapping("/login/now")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            logger.info("Beginning API method login with information: " + loginRequest);
            Employee employee = employeeSAO.login(loginRequest.getEmail(), loginRequest.getPassword());
            Session newSession = new Session(0, employee.getEmployeeId(), LocalDateTime.now().plusMinutes(15));
            Session createdNewSession = sessionSAO.addSession(newSession);
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("sessionId", createdNewSession.getSessionId());
            logger.info("Finishing API method login with result: " + responseBody);
            return ResponseEntity.ok(responseBody);
        } catch (GeneralError error) {
            ResponseEntity<String> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
            logger.error("Error with API method login with error response: " + response);
            return response;
        }
    }
}
