package com.example.Reimbursement.Request.App.SAL;

import com.example.Reimbursement.Request.App.DAL.EmployeeDAL.EmployeeDALImplementation;
import com.example.Reimbursement.Request.App.Entities.Employee;
import com.example.Reimbursement.Request.App.SAL.EmployeeSAL.EmployeeSALImplementation;
import org.junit.Test;

public class EmployeeSALTests {
    int currentEmployeeId = 1;
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    Employee successEmployee = new Employee(0, "success@email.com", "success",
            "test", "test", "444-557-5621", "123 This St, OKC, OK, 78995");
    Employee updateEmployee = new Employee(currentEmployeeId, "updated@email.com", "updated",
            "updated", "updated", "222-555-3315", "321 That St, OKC, OK, 75213");

    @Test
    public void _addEmployeeFirstNameEmpty() {

    }

    @Test
    public void _addEmployeeFirstNameTooLong() {

    }

    @Test
    public void _addEmployeeLastNameEmpty() {

    }

    @Test
    public void _addEmployeeLastNameTooLong() {

    }

    @Test
    public void _addEmployeeEmailEmpty() {

    }

    @Test
    public void _addEmployeeEmailTooLong() {

    }

    @Test
    public void _addEmployeePasswordEmpty() {

    }

    @Test
    public void _addEmployeePasswordTooLong() {

    }

    @Test
    public void _addEmployeePhoneNumberEmpty() {

    }

    @Test
    public void _addEmployeePhoneNumberTooLong() {

    }

    @Test
    public void _addEmployeePhoneNumberIncorrectFormat() {

    }

    @Test
    public void _addEmployeeAddressEmpty() {

    }

    @Test
    public void _addEmployeeAddressTooLong() {

    }

    @Test
    public void _addEmployeeAlreadyExists() {

    }

    @Test
    public void _addEmployeeSuccess() {

    }

    @Test
    public void _getEmployeeByIdNoneFound() {

    }

    @Test
    public void _getEmployeeByIdSuccess() {

    }

    @Test
    public void _getEmployeeByEmailEmpty() {

    }

    @Test
    public void _getEmployeeByEmailSuccess() {

    }

    @Test
    public void _loginEmailEmpty() {

    }

    @Test
    public void _loginPasswordEmpty() {

    }

    @Test
    public void _loginEmailOrPasswordIncorrect() {

    }

    @Test
    public void _loginSuccess() {

    }

    @Test
    public void _updateEmployeeFirstNameEmpty() {

    }

    @Test
    public void _updateEmployeeFirstNameTooLong() {

    }

    @Test
    public void _updateEmployeeLastNameEmpty() {

    }

    @Test
    public void _updateEmployeeLastNameTooLong() {

    }

    @Test
    public void _updateEmployeeEmailEmpty() {

    }

    @Test
    public void _updateEmployeeEmailTooLong() {

    }

    @Test
    public void _updateEmployeePasswordEmpty() {

    }

    @Test
    public void _updateEmployeePasswordTooLong() {

    }

    @Test
    public void _updateEmployeePhoneNumberEmpty() {

    }

    @Test
    public void _updateEmployeePhoneNumberTooLong() {

    }

    @Test
    public void _updateEmployeePhoneNumberIncorrectFormat() {

    }

    @Test
    public void _updateEmployeeAddressEmpty() {

    }

    @Test
    public void _updateEmployeeAddressTooLong() {

    }

    @Test
    public void _updateEmployeeNothingChanged() {

    }

    @Test
    public void _updateEmployeeNoneFound() {

    }

    @Test
    public void _updateEmployeeSuccess() {

    }

    @Test
    public void _deleteEmployeeNotFound() {

    }

    @Test
    public void _deleteEmployeeSuccess() {
        
    }
}
