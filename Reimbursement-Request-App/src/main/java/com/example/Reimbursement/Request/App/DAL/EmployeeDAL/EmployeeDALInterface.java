package com.example.Reimbursement.Request.App.DAL.EmployeeDAL;

import com.example.Reimbursement.Request.App.Entities.Employee;

public interface EmployeeDALInterface {
    Employee addEmployee(Employee employee);
    Employee getEmployeeById(int employeeId);
    Employee getEmployeeByEmail(String email);
    Employee updateEmployee(Employee employee);
    boolean deleteEmployee(int employeeId);
}