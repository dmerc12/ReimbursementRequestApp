package com.example.Reimbursement.Request.App.DAL.EmployeeDAL;

import com.example.Reimbursement.Request.App.Entities.Employee;
import org.junit.Assert;
import org.junit.Test;

public class EmployeeDALTests {

    int currentEmployeeId = 1;
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    Employee successfulEmployee = new Employee(0, "e@mail.com", "password", "first",
            "last", "444-555-6666", "123 S Second St, OKC, OK, 78995");
    Employee updateEmployee = new Employee(currentEmployeeId,"new@email.com","new password",
            "updated", "updated", "555-666-4444", "312 N Second St, OKC, OK, 79588");

    @Test
    public void addEmployeeSuccess() {
        Employee result = employeeDAO.addEmployee(successfulEmployee);
        Assert.assertNotEquals(result.getEmployeeId(), 0);
    }

    @Test
    public void getEmployeeByIdSuccess() {
        Employee result = employeeDAO.getEmployeeById(currentEmployeeId);
        Assert.assertNotNull(result);
    }

    @Test
    public void getEmployeeByEmailSuccess() {
        Employee result = employeeDAO.getEmployeeByEmail(successfulEmployee.getEmail());
        Assert.assertNotNull(result);
    }

    @Test
    public void loginSuccess() {
        Employee result = employeeDAO.login(successfulEmployee.getEmail(), successfulEmployee.getPassword());
        Assert.assertNotNull(result);
    }

    @Test
    public void updateEmployeeSuccess() {
        Employee result = employeeDAO.updateEmployee(updateEmployee);
        Assert.assertEquals(updateEmployee.getEmail(), result.getEmail());
        Assert.assertEquals(updateEmployee.getAddress(), result.getAddress());
        Assert.assertEquals(updateEmployee.getPhoneNumber(), result.getPhoneNumber());
        Assert.assertEquals(updateEmployee.getFirstName(), result.getFirstName());
        Assert.assertEquals(updateEmployee.getLastName(), result.getLastName());
    }

    @Test
    public void deleteEmployeeSuccess() {
        int result = employeeDAO.deleteEmployee(currentEmployeeId);
        Assert.assertTrue(result != 0);
    }
}
