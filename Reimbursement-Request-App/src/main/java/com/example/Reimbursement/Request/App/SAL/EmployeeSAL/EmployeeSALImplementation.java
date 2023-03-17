package com.example.Reimbursement.Request.App.SAL.EmployeeSAL;

import com.example.Reimbursement.Request.App.DAL.EmployeeDAL.EmployeeDALImplementation;
import com.example.Reimbursement.Request.App.Entities.CustomExceptions.GeneralError;
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
        logger.info("Beginning SAL method add employee with employee: " + employee);
        if (employee.getFirstName().equals("")) {
            logger.warn("SAL method add employee, first name left empty");
            throw new GeneralError("The first name field cannot be left empty, please try again!");
        } else if (employee.getFirstName().length() > 36) {
            logger.warn("SAL method add employee, first name too long");
            throw new GeneralError("The first name field cannot exceed 36 characters, please try again!");
        } else if (employee.getLastName().equals("")) {
            logger.warn("SAL method add employee, last name left empty");
            throw new GeneralError("The last name field cannot be left empty, please try again!");
        } else if (employee.getLastName().length() > 36) {
            logger.warn("SAL method add employee, last name too long");
            throw new GeneralError("The last name field cannot exceed 36 characters, please try again!");
        } else if (employee.getEmail().equals("")) {
            logger.warn("SAL method add employee, email left empty");
            throw new GeneralError("The email field cannot be left empty, please try again!");
        } else if (employee.getEmail().length() > 60) {
            logger.warn("SAL method add employee, email too long");
            throw new GeneralError("The email field cannot exceed 60 characters, please try again!");
        } else if (employee.getPassword().equals("")) {
            logger.warn("SAL method add employee, password left empty");
            throw new GeneralError("The password field cannot be left empty, please try again!");
        } else if (employee.getPassword().length() > 60) {
            logger.warn("SAL method add employee, password too long");
            throw new GeneralError("The password field cannot exceed 60 characters, please try again!");
        } else if (employee.getPhoneNumber().equals("")) {
            logger.warn("SAL method add employee, phone number left empty");
            throw new GeneralError("The phone number field cannot be left empty, please try again!");
        } else if (employee.getPhoneNumber().length() > 13) {
            logger.warn("SAL method add employee, phone number too long");
            throw new GeneralError("The phone number field cannot exceed 13 characters, please try again!");
        } else if (!employee.getPhoneNumber().matches("\\d{3}-\\d{3}-\\d{4}")) {
            logger.warn("SAL method add employee, phone number in incorrect format");
            throw new GeneralError("The phone number field cannot vary from the format 'xxx-xxx-xxxx', " +
                    "please try again!");
        } else if (employee.getAddress().equals("")) {
            logger.warn("SAL method add employee, address left empty");
            throw new GeneralError("The address field cannot be left empty, please try again!");
        } else if (employee.getAddress().length() > 60) {
            logger.warn("SAL method add employee, address too long");
            throw new GeneralError("The address field cannot exceed 60 characters, please try again!");
        } else {
            Employee existingEmployee = employeeDAO.getEmployeeByEmail(employee.getEmail());
            if (existingEmployee != null) {
                logger.warn("SAL method add employee, employee already exists");
                throw new GeneralError("An employee with this email already exists, please try again!");
            } else {
                Employee result = employeeDAO.addEmployee(employee);
                logger.info("Finishing SAL method add employee with result: " + result);
                return result;
            }
        }
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        logger.info("Beginning SAL method get employee by ID with employee ID: " + employeeId);
        Employee employee = employeeDAO.getEmployeeById(employeeId);
        if (employee == null) {
            logger.warn("SAL method get employee by ID, not found");
            throw new GeneralError("No employee found, please try again!");
        } else {
            logger.info("Finishing SAL method get employee by ID with employee: " + employee);
            return employee;
        }
     }

    @Override
    public Employee login(String email, String password) {
        logger.info("Beginning SAL method login with email: " + email + ", and password: " + password);
        if (email.equals("")) {
            logger.warn("SAL method login, email left empty");
            throw new GeneralError("The email field cannot be left empty, please try again!");
        } else if (password.equals("")) {
            logger.warn("SAL method login, password left empty");
            throw new GeneralError("The password field cannot be left empty, please try again!");
        } else {
            Employee employee = employeeDAO.login(email, password);
            if (employee == null) {
                logger.warn("SAL method login, not existing credentials");
                throw new GeneralError("Either the email or the password is incorrect, please try again!");
            } else {
                logger.info("Finishing SAL method login with employee: " + employee);
                return employee;
            }
        }
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
