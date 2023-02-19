package com.example.Reimbursement.Request.App.Implementation.Employee;

import com.example.Reimbursement.Request.App.Entities.Employee;

public interface EmployeeInterface {
    Employee addEmployee(Employee employee);
    Employee getEmployeeById(int employeeId);
    Employee getEmployeeByEmail(String email);
    Employee updateEmployee(Employee employee);
    boolean deleteEmployee(int employeeId);
}
