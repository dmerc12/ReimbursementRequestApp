package com.example.Reimbursement.Request.App.SAL.EmployeeSAL;

import com.example.Reimbursement.Request.App.DAL.EmployeeDAL.EmployeeDALImplementation;
import com.example.Reimbursement.Request.App.Entities.Employee;

public class EmployeeSALImplementation implements EmployeeSALInterface{
    private EmployeeDALImplementation employeeDAO;

    @Override
    public Employee serviceAddEmployee(Employee employee) {
        return null;
    }

    @Override
    public Employee serviceGetEmployeeById(int employeeId) {
        return null;
    }

    @Override
    public Employee serviceGetEmployeeByEmail(String email) {
        return null;
    }

    @Override
    public Employee serviceUpdateEmployee(Employee employee) {
        return null;
    }

    @Override
    public boolean serviceDeleteEmployee(int employeeId) {
        return false;
    }
}
