package com.example.Reimbursement.Request.App.DAL.EmployeeDAL;

import com.example.Reimbursement.Request.App.Entities.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployeeImplementation implements EmployeeInterface {

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
