package com.example.Reimbursement.Request.App.SAL.EmployeeSAL;

import com.example.Reimbursement.Request.App.Entities.Employee;

public interface EmployeeSALInterface {
    Employee serviceAddEmployee(Employee employee);
    Employee serviceGetEmployeeById(int employeeId);
    Employee serviceGetEmployeeByEmail(String email);
    Employee serviceUpdateEmployee(Employee employee);
    boolean serviceDeleteEmployee(int employeeId);
}
