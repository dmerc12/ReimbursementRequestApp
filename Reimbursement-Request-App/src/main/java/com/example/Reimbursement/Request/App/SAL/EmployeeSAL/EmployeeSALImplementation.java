package com.example.Reimbursement.Request.App.SAL.EmployeeSAL;

import com.example.Reimbursement.Request.App.DAL.EmployeeDAL.EmployeeDALImplementation;
import com.example.Reimbursement.Request.App.Entities.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployeeSALImplementation implements EmployeeSALInterface{
    private EmployeeDALImplementation employeeDAO;
    public static Logger logger = LogManager.getLogger(EmployeeSALImplementation.class);
    public EmployeeSALImplementation(EmployeeDALImplementation employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
    @Override
    public Employee addEmployee(Employee employee) {
        return null;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        return null;
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        return null;
    }

    @Override
    public Employee login(String email, String password) {
        return null;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return null;
    }

    @Override
    public int deleteEmployee(int employeeId) {
        return 0;
    }
}
