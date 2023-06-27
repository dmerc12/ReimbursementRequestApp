package DAL;


import DAL.EmployeeDAL.EmployeeDALImplementation;
import Entities.Data.Employee;
import Utilities.CustomHashing;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDALTests {
    int currentEmployeeId = 1;
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    Employee successfulEmployee = new Employee(0, "first", "last", "e@mail.com",
            "password", "444-555-6666", "123 S Second St, OKC, OK, 78995");
    Employee updateEmployee = new Employee(currentEmployeeId,"updated","updated",
            "new@email.com", "new password", "555-666-4444",
            "312 N Second St, OKC, OK, 79588");

    @Test
    public void a_addEmployeeSuccess() {
        Employee result = employeeDAO.addEmployee(successfulEmployee);
        Assert.assertNotEquals(result.getEmployeeId(), 0);
    }

    @Test
    public void b_getEmployeeByIdSuccess() {
        Employee result = employeeDAO.getEmployeeById(-1);
        Assert.assertNotNull(result);
    }

    @Test
    public void c_getEmployeeByEmailSuccess() {
        Employee result = employeeDAO.getEmployeeByEmail("test@email.com");
        Assert.assertNotNull(result);
    }

    @Test
    public void d_loginSuccess() {
        Employee result = employeeDAO.login("test@email.com", CustomHashing.hash("test"));
        Assert.assertNotNull(result);
    }

    @Test
    public void e_updateEmployeeSuccess() {
        Employee result = employeeDAO.updateEmployee(updateEmployee);
        Assert.assertEquals(updateEmployee.getEmail(), result.getEmail());
        Assert.assertEquals(updateEmployee.getAddress(), result.getAddress());
        Assert.assertEquals(updateEmployee.getPhoneNumber(), result.getPhoneNumber());
        Assert.assertEquals(updateEmployee.getFirstName(), result.getFirstName());
        Assert.assertEquals(updateEmployee.getLastName(), result.getLastName());
    }

    @Test
    public void f_changePasswordSuccess() {
        Employee result = employeeDAO.changePassword(updateEmployee);
        Assert.assertEquals(updateEmployee.getPassword(), result.getPassword());
    }

    @Test
    public void g_deleteEmployeeSuccess() {
        int result = employeeDAO.deleteEmployee(currentEmployeeId);
        Assert.assertTrue(result != 0);
    }
}
