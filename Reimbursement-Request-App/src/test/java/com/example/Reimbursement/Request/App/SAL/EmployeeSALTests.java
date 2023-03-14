package com.example.Reimbursement.Request.App.SAL;

import com.example.Reimbursement.Request.App.DAL.EmployeeDAL.EmployeeDALImplementation;
import com.example.Reimbursement.Request.App.SAL.EmployeeSAL.EmployeeSALImplementation;

public class EmployeeSALTests {
    int currentEmployeeId = 1;
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
}
