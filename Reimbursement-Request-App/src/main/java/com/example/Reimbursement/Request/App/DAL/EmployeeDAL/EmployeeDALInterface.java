package com.example.Reimbursement.Request.App.DAL.EmployeeDAL;

import com.example.Reimbursement.Request.App.Entities.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeDALInterface extends MongoRepository<Employee, String> {
    Employee addEmployee(Employee employee);
    Employee getEmployeeById(int employeeId);
    Employee getEmployeeByEmail(String email);
    Employee updateEmployee(Employee employee);
    boolean deleteEmployee(int employeeId);
}
