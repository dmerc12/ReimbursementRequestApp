package com.example.Reimbursement.Request.App.DAL.EmployeeDAL;

import com.example.Reimbursement.Request.App.Entities.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeDALInterface extends MongoRepository<Employee, Integer> {
    Employee addEmployee(Employee employee);
    Employee getEmployee(int employeeId);
    Employee updateEmployee(Employee employee);
    boolean deleteEmployee(int employeeId);
}
