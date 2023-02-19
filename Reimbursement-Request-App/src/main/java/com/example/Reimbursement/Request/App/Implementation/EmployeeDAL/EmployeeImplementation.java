package com.example.Reimbursement.Request.App.Implementation.EmployeeDAL;

import com.example.Reimbursement.Request.App.Entities.Employee;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;


@Service
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeImplementation implements EmployeeInterface {
    private MongoTemplate mongoTemplate;

    public static Logger logger = LogManager.getLogger(EmployeeImplementation.class);

    @Override
    public Employee addEmployee(Employee employee) {
        return null;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        return null;
    }

    public Employee getEmployeeByEmail(String email) {return null; }

    @Override
    public Employee updateEmployee(Employee employee) {
        return null;
    }

    @Override
    public boolean deleteEmployee(int employeeId) {
        return false;
    }
}
