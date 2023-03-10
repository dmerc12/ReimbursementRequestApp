package com.example.Reimbursement.Request.App.SAL;

import com.example.Reimbursement.Request.App.DAL.EmployeeDAL.EmployeeDALImplementation;
import com.example.Reimbursement.Request.App.Entities.CustomExceptions.GeneralError;
import com.example.Reimbursement.Request.App.Entities.Employee;
import com.example.Reimbursement.Request.App.SAL.EmployeeSAL.EmployeeSALImplementation;
import org.junit.Assert;
import org.junit.Test;

public class EmployeeSALTests {
    int currentEmployeeId = 1;
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    Employee successEmployee = new Employee(0, "test", "test", "success@email.com",
            "success", "444-557-5621", "123 This St, OKC, OK, 78995");
    Employee updateEmployee = new Employee(currentEmployeeId, "updated", "updated",
            "updated@email.com", "updated", "222-555-3315",
            "321 That St, OKC, OK, 75213");

    @Test
    public void _addEmployeeFirstNameEmpty() {
        try {
            Employee testEmployee = new Employee(0, "", "test", "test@email.com",
                    "password", "666-555-4444", "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The first name field cannot be left empty, please try " +
                    "again!");
        }
    }

    @Test
    public void _addEmployeeFirstNameTooLong() {
        try {
            Employee testEmployee = new Employee(0, "this tests if it is too long and so it " +
                    "should fail and bring about the desired error message", "test", "test@email.com",
                    "password", "666-555-4444", "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The first name field cannot exceed 36 characters, " +
                    "please try again!");
        }
    }

    @Test
    public void _addEmployeeLastNameEmpty() {
        try {
            Employee testEmployee = new Employee(0, "test", "", "test@email.com",
                    "password", "666-555-4444", "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The last name field cannot be left empty, please try " +
                    "again!");
        }
    }

    @Test
    public void _addEmployeeLastNameTooLong() {
        try {
            Employee testEmployee = new Employee(0, "test", "this tests if it is too " +
                    "long and so it should fail and bring about the desired error message", "test@email.com",
                    "password", "666-555-4444", "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The last name field cannot exceed 36 characters, please " +
                    "try again!");
        }
    }

    @Test
    public void _addEmployeeEmailEmpty() {
        try {
            Employee testEmployee = new Employee(0, "test", "test", "",
                    "password", "666-555-4444", "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The email field cannot be left empty, please try again!");
        }
    }

    @Test
    public void _addEmployeeEmailTooLong() {
        try {
            Employee testEmployee = new Employee(0, "test", "test", "this tests " +
                    "if it is too long and so it should fail and bring about the desired error message",
                    "password", "666-555-4444", "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The email field cannot exceed 60 characters, please " +
                    "try again!");
        }
    }

    @Test
    public void _addEmployeePasswordEmpty() {
        try {
            Employee testEmployee = new Employee(0, "test", "test", "test@email.com",
                    "", "666-555-4444", "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The password field cannot be left empty, please try again!");
        }
    }

    @Test
    public void _addEmployeePasswordTooLong() {
        try {
            Employee testEmployee = new Employee(0, "test", "test", "test@email.com",
                    "this tests if it is too long and so it should fail and bring about the desired error " +
                            "message", "666-555-4444", "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The password field cannot exceed 60 characters, please " +
                    "try again!");
        }
    }

    @Test
    public void _addEmployeePhoneNumberEmpty() {
        try {
            Employee testEmployee = new Employee(0, "test", "test", "test@email.com",
                    "password", "", "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The phone number field cannot be left empty, please " +
                    "try again!");
        }
    }

    @Test
    public void _addEmployeePhoneNumberTooLong() {
        try {
            Employee testEmployee = new Employee(0, "test", "test", "test@email.com",
                    "password", "this tests if it is too long and so it should fail and bring " +
                    "about the desired error message", "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The phone number field cannot exceed 60 characters, " +
                    "please try again!");
        }
    }

    @Test
    public void _addEmployeePhoneNumberIncorrectFormat() {
        try {
            Employee testEmployee = new Employee(0, "test", "test", "test@email.com",
                    "password", "this is wrong", "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The phone number field cannot vary from the format " +
                    "'xxx-xxx-xxxx', please try again!");
        }
    }

    @Test
    public void _addEmployeeAddressEmpty() {
        try {
            Employee testEmployee = new Employee(0, "test", "test", "test@email.com",
                    "password", "666-555-4444", "");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The address field cannot be left empty, please try again!");
        }
    }

    @Test
    public void _addEmployeeAddressTooLong() {
        try {
            Employee testEmployee = new Employee(0, "test", "test", "test@email.com",
                    "password", "666-555-4444", "this tests if it is too long and so it " +
                    "should fail and bring about the desired error message");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The address field cannot exceed 60 characters, please try again!");
        }
    }

    @Test
    public void _addEmployeeAlreadyExists() {
        try {
            Employee testEmployee = new Employee(0, "test", "test", "test",
                    "password", "666-555-4444", "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "An employee with this email already exists, please " +
                    "try again!");
        }
    }

    @Test
    public void _addEmployeeSuccess() {
        Employee result = employeeSAO.addEmployee(successEmployee);
        Assert.assertNotEquals(result.getEmployeeId(), 0);
    }

    @Test
    public void _getEmployeeByIdNoneFound() {
        try {
            employeeSAO.getEmployeeById(-500000000);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "No employee found, please try again!");
        }
    }

    @Test
    public void _getEmployeeByIdSuccess() {
        Employee result = employeeSAO.getEmployeeById(currentEmployeeId);
        Assert.assertNotNull(result);
    }

    @Test
    public void _getEmployeeByEmailEmpty() {
        try {
            employeeSAO.getEmployeeByEmail("");
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The email field cannot be left empty, please try again!");
        }
    }

    @Test
    public void _getEmployeeByEmailSuccess() {
        Employee result = employeeSAO.getEmployeeByEmail("test@email.com");
        Assert.assertNotNull(result);
    }

    @Test
    public void _loginEmailEmpty() {
        try {
            employeeSAO.login("", "test");
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The email field cannot be left empty, please try again!");
        }
    }

    @Test
    public void _loginPasswordEmpty() {
        try {
            employeeSAO.login("test", "");
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The password field cannot be left empty, please try again!");
        }
    }

    @Test
    public void _loginEmailOrPasswordIncorrect() {
        try {
            employeeSAO.login("test", "test");
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Either the email or the password is incorrect, please " +
                    "try again!");
        }
    }

    @Test
    public void _loginSuccess() {
        Employee result = employeeSAO.login("test@email.com", "test");
        Assert.assertNotNull(result);
    }

    @Test
    public void _updateEmployeeFirstNameEmpty() {
        try {
            Employee testEmployee = new Employee(0, "", "test", "test@email.com",
                    "password", "666-555-4444", "312 N Test St, Test, TS, 78652");
            employeeSAO.updateEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The first name field cannot be left empty, please " +
                    "try again!");
        }
    }

    @Test
    public void _updateEmployeeFirstNameTooLong() {
        try {
            Employee testEmployee = new Employee(0, "this tests if it is too long and so it " +
                    "should fail and bring about the desired error message", "test", "test@email.com",
                    "password", "666-555-4444", "312 N Test St, Test, TS, 78652");
            employeeSAO.updateEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The first name field cannot exceed 36 characters, please" +
                    " try again!");
        }
    }

    @Test
    public void _updateEmployeeLastNameEmpty() {
        try {
            Employee testEmployee = new Employee(0, "test", "", "test@email.com",
                    "password", "666-555-4444", "312 N Test St, Test, TS, 78652");
            employeeSAO.updateEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The last name field cannot be left empty, please try " +
                    "again!");
        }
    }

    @Test
    public void _updateEmployeeLastNameTooLong() {
        try {
            Employee testEmployee = new Employee(0, "test", "this tests if it is too " +
                    "long and so it should fail and bring about the desired error message", "test@email.com",
                    "password", "666-555-4444", "312 N Test St, Test, TS, 78652");
            employeeSAO.updateEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The last name field cannot exceed 36 characters, please" +
                     " try again!");
        }
    }

    @Test
    public void _updateEmployeeEmailEmpty() {
        try {
            Employee testEmployee = new Employee(0, "test", "test", "",
                    "password", "666-555-4444", "312 N Test St, Test, TS, 78652");
            employeeSAO.updateEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The email field cannot be left empty, please try again!");
        }
    }

    @Test
    public void _updateEmployeeEmailTooLong() {
        try {
            Employee testEmployee = new Employee(0, "test", "test", "this tests" +
                    " if it is too long and so it should fail and bring about the desired error message",
                    "password", "666-555-4444", "312 N Test St, Test, TS, 78652");
            employeeSAO.updateEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The email field cannot exceed 60 characters, please " +
                    "try again!");
        }
    }

    @Test
    public void _updateEmployeePasswordEmpty() {
        try {
            Employee testEmployee = new Employee(0, "test", "test", "test@email.com",
                    "", "666-555-4444", "312 N Test St, Test, TS, 78652");
            employeeSAO.updateEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The password field cannot be left empty, please try again!");
        }
    }

    @Test
    public void _updateEmployeePasswordTooLong() {
        try {
            Employee testEmployee = new Employee(0, "test", "test", "test@email.com",
                    "this tests if it is too long and so it should fail and bring about the desired error " +
                            "message", "666-555-4444", "312 N Test St, Test, TS, 78652");
            employeeSAO.updateEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The password field cannot exceed 60 characters, please " +
                    "try again!");
        }
    }

    @Test
    public void _updateEmployeePhoneNumberEmpty() {
        try {
            Employee testEmployee = new Employee(0, "test", "test", "test@email.com",
                    "password", "", "312 N Test St, Test, TS, 78652");
            employeeSAO.updateEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The phone number field cannot be left empty, please try again!");
        }
    }

    @Test
    public void _updateEmployeePhoneNumberTooLong() {
        try {
            Employee testEmployee = new Employee(0, "test", "test", "test@email.com",
                    "password", "this tests if it is too long and so it should fail and bring " +
                    "about the desired error message", "312 N Test St, Test, TS, 78652");
            employeeSAO.updateEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The phone number field cannot exceed 13 characters, " +
                    "please try again!");
        }
    }

    @Test
    public void _updateEmployeePhoneNumberIncorrectFormat() {
        try {
            Employee testEmployee = new Employee(0, "test", "test", "test@email.com",
                    "password", "this is wrong", "312 N Test St, Test, TS, 78652");
            employeeSAO.updateEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The phone number field cannot vary from the format " +
                    "'xxx-xxx-xxxx', please try again!");
        }
    }

    @Test
    public void _updateEmployeeAddressEmpty() {
        try {
            Employee testEmployee = new Employee(0, "test", "test", "test@email.com",
                    "password", "666-555-4444", "");
            employeeSAO.updateEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The address field cannot be left empty, please try again!");
        }
    }

    @Test
    public void _updateEmployeeAddressTooLong() {
        try {
            Employee testEmployee = new Employee(0, "test", "test", "test@email.com",
                    "password", "666-555-4444", "this tests if it is too long and so " +
                    "it should fail and bring about the desired error message");
            employeeSAO.updateEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The address field cannot exceed 60 characters, please " +
                    "try again!");
        }
    }

    @Test
    public void _updateEmployeeNothingChanged() {
        try {
            Employee testEmployee = new Employee(0, "test", "test", "test@email.com",
                    "test", "111-222-3333", "test");
            employeeSAO.updateEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Nothing has changed, please try again!");
        }
    }

    @Test
    public void _updateEmployeeNoneFound() {
        try {
            Employee testEmployee = new Employee(-500000000, "test", "test",
                    "test@email.com", "password", "666-555-4444",
                    "312 N Test St, Test, TS, 78652");
            employeeSAO.updateEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "No employee found, please try again!");
        }
    }

    @Test
    public void _updateEmployeeSuccess() {
        Employee result = employeeSAO.updateEmployee(updateEmployee);
        Assert.assertEquals(updateEmployee.getFirstName(), result.getFirstName());
        Assert.assertEquals(updateEmployee.getLastName(), result.getLastName());
        Assert.assertEquals(updateEmployee.getEmail(), result.getEmail());
        Assert.assertEquals(updateEmployee.getPhoneNumber(), result.getPhoneNumber());
        Assert.assertEquals(updateEmployee.getAddress(), result.getAddress());
    }

    @Test
    public void _deleteEmployeeNotFound() {
        try {
            employeeSAO.deleteEmployee(-500000000);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "No employee found, please try again!");
        }
    }

    @Test
    public void _deleteEmployeeSuccess() {
        int result = employeeSAO.deleteEmployee(currentEmployeeId);
        Assert.assertTrue(result != 0);
    }
}
