package com.example.Reimbursement.Request.App.DAL.EmployeeDAL;

import com.example.Reimbursement.Request.App.Entities.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployeeDALImplementation implements EmployeeDALInterface {

    public static Logger logger = LogManager.getLogger(EmployeeDALImplementation.class);

    @Override
    public Employee addEmployee(Employee employee) {
        return null;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        return null;
    }

    public Employee getEmployeeByEmail(String email) {return null; }

    public Employee login(String email, String password) { return null; }

    @Override
    public Employee updateEmployee(Employee employee) {
        return null;
    }

    @Override
    public int deleteEmployee(int employeeId) {
        return 0;
    }
}
