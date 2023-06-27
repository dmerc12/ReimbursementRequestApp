package SAL;

import DAL.EmployeeDAL.EmployeeDALImplementation;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Employee;
import Entities.Requests.Employee.NewEmployeeRequest;
import SAL.EmployeeSAL.EmployeeSALImplementation;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeSALTests {
    int currentEmployeeId = 1;
    EmployeeDALImplementation employeeDAO = new EmployeeDALImplementation();
    EmployeeSALImplementation employeeSAO = new EmployeeSALImplementation(employeeDAO);
    NewEmployeeRequest successEmployee = new NewEmployeeRequest("test", "test",
            "success@email.com", "success", "success",
            "444-557-5621", "123 This St, OKC, OK, 78995");
    Employee updateEmployee = new Employee(currentEmployeeId, "updated", "updated",
            "updated@email.com", "updated", "222-555-3315",
            "321 That St, OKC, OK, 75213");

    @Test
    public void aa_addEmployeeFirstNameEmpty() {
        try {
            NewEmployeeRequest testEmployee = new NewEmployeeRequest("", "test",
                    "test@email.com", "password", "password",
                    "666-555-4444", "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The first name field cannot be left empty, please try " +
                    "again!");
        }
    }

    @Test
    public void ab_addEmployeeFirstNameTooLong() {
        try {
            NewEmployeeRequest testEmployee = new NewEmployeeRequest("this is way too long and so it should " +
                    "fail and bring about the desired error message which in turn will make this test successful and " +
                    "pass", "test", "test@email.com", "password", "password",
                    "666-555-4444", "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The first name field cannot exceed 36 characters, " +
                    "please try again!");
        }
    }

    @Test
    public void ac_addEmployeeLastNameEmpty() {
        try {
            NewEmployeeRequest testEmployee = new NewEmployeeRequest("test", "",
                    "test@email.com", "password", "password",
                    "666-555-4444", "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The last name field cannot be left empty, please try " +
                    "again!");
        }
    }

    @Test
    public void ad_addEmployeeLastNameTooLong() {
        try {
            NewEmployeeRequest testEmployee = new NewEmployeeRequest("test", "this is too long and" +
                    " so it should fail and raise the desired error message which will make it fail but in turn the " +
                    "test will then pass", "test@email.com", "password", "password",
                    "666-555-4444", "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The last name field cannot exceed 36 characters, please " +
                    "try again!");
        }
    }

    @Test
    public void ae_addEmployeeEmailEmpty() {
        try {
            NewEmployeeRequest testEmployee = new NewEmployeeRequest("test", "test", "",
                    "password", "password", "666-555-4444",
                    "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The email field cannot be left empty, please try again!");
        }
    }

    @Test
    public void af_addEmployeeEmailTooLong() {
        try {
            NewEmployeeRequest testEmployee = new NewEmployeeRequest("test", "test",
                    "this is too long and so it should fail and raise the desired error message and then the " +
                            "test will pass", "password", "password",
                    "666-555-4444", "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The email field cannot exceed 60 characters, please " +
                    "try again!");
        }
    }

    @Test
    public void ag_addEmployeePasswordEmpty() {
        try {
            NewEmployeeRequest testEmployee = new NewEmployeeRequest("test", "test",
                    "test@email.com", "", "password", "666-555-4444",
                    "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The password field cannot be left empty, please try again!");
        }
    }

    @Test
    public void ah_addEmployeePasswordTooLong() {
        try {
            NewEmployeeRequest testEmployee = new NewEmployeeRequest("test", "test",
                    "test@email.com", "this is too long and so it should fail and raise the desired " +
                    "error message and then this test will pass", "password",
                    "666-555-4444", "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The password field cannot exceed 60 characters, please " +
                    "try again!");
        }
    }

    @Test
    public void ai_addEmployeeConfirmationPasswordEmpty() {
        try {
            NewEmployeeRequest testEmployee = new NewEmployeeRequest("test", "test",
                    "test@email.com", "password", "", "666-555-4444",
                    "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The confirmation password field cannot be left empty, " +
                    "please try again!");
        }
    }

    @Test
    public void aj_addEmployeeConfirmationPasswordTooLong() {
        try {
            NewEmployeeRequest testEmployee = new NewEmployeeRequest("test", "test",
                    "test@email.com", "password", "this is too long and so it" +
                    " should fail and bring about the desired error message", "666-555-4444",
                    "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The confirmation password field cannot exceed 60 " +
                    "characters, please try again!");
        }
    }

    @Test
    public void ak_addEmployeePasswordsDoNotMatch() {
        try {
            NewEmployeeRequest testEmployee = new NewEmployeeRequest("test", "test",
                    "test@email.com", "password", "wrong",
                    "666-555-4444", "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The passwords do not match, please try again!");
        }
    }

    @Test
    public void al_addEmployeePhoneNumberEmpty() {
        try {
            NewEmployeeRequest testEmployee = new NewEmployeeRequest("test", "test",
                    "test@email.com", "password", "password", "",
                    "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The phone number field cannot be left empty, please " +
                    "try again!");
        }
    }

    @Test
    public void am_addEmployeePhoneNumberTooLong() {
        try {
            NewEmployeeRequest testEmployee = new NewEmployeeRequest("test", "test",
                    "test@email.com", "password", "password", "this is" +
                    " too long and so it should raise the desired error message",
                    "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The phone number field cannot exceed 13 characters, " +
                    "please try again!");
        }
    }

    @Test
    public void an_addEmployeePhoneNumberIncorrectFormat() {
        try {
            NewEmployeeRequest testEmployee = new NewEmployeeRequest("test", "test",
                    "test@email.com", "password", "password",
                    "this is wrong", "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The phone number field cannot vary from the format " +
                    "'xxx-xxx-xxxx', please try again!");
        }
    }

    @Test
    public void ao_addEmployeeAddressEmpty() {
        try {
            NewEmployeeRequest testEmployee = new NewEmployeeRequest("test", "test",
                    "test@email.com", "password", "password",
                    "666-555-4444", "");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The address field cannot be left empty, please try again!");
        }
    }

    @Test
    public void ap_addEmployeeAddressTooLong() {
        try {
            NewEmployeeRequest testEmployee = new NewEmployeeRequest("test", "test",
                    "test@email.com", "password", "password",
                    "666-555-4444", "this is entirely way too long for this input section and so" +
                    " this should fail the desired check and thus raise the desired error message to compare and " +
                    "hopefully pass this test");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The address field cannot exceed 60 characters, please try again!");
        }
    }

    @Test
    public void aq_addEmployeeAlreadyExists() {
        try {
            NewEmployeeRequest testEmployee = new NewEmployeeRequest("test", "test",
                    "test@email.com", "password", "password",
                    "666-555-4444", "312 N Test St, Test, TS, 78652");
            employeeSAO.addEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "An employee with this email already exists, please " +
                    "try again!");
        }
    }

    @Test
    public void ar_addEmployeeSuccess() {
        Employee result = employeeSAO.addEmployee(successEmployee);
        Assert.assertNotEquals(result.getEmployeeId(), 0);
    }

    @Test
    public void ba_getEmployeeByIdNoneFound() {
        try {
            employeeSAO.getEmployeeById(-500000000);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "No employee found, please try again!");
        }
    }

    @Test
    public void bb_getEmployeeByIdSuccess() {
        Employee result = employeeSAO.getEmployeeById(currentEmployeeId);
        Assert.assertNotNull(result);
    }

    @Test
    public void ca_loginEmailEmpty() {
        try {
            employeeSAO.login("", "test");
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The email field cannot be left empty, please try again!");
        }
    }

    @Test
    public void cb_loginPasswordEmpty() {
        try {
            employeeSAO.login("test", "");
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The password field cannot be left empty, please try again!");
        }
    }

    @Test
    public void cc_loginEmailOrPasswordIncorrect() {
        try {
            employeeSAO.login("test", "test");
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Either the email or the password is incorrect, please " +
                    "try again!");
        }
    }

    @Test
    public void cd_loginSuccess() {
        Employee result = employeeSAO.login("test@email.com", "test");
        Assert.assertNotNull(result);
    }

    @Test
    public void da_updateEmployeeFirstNameEmpty() {
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
    public void db_updateEmployeeFirstNameTooLong() {
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
    public void dc_updateEmployeeLastNameEmpty() {
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
    public void dd_updateEmployeeLastNameTooLong() {
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
    public void de_updateEmployeeEmailEmpty() {
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
    public void df_updateEmployeeEmailTooLong() {
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
    public void di_updateEmployeePhoneNumberEmpty() {
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
    public void dj_updateEmployeePhoneNumberTooLong() {
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
    public void dk_updateEmployeePhoneNumberIncorrectFormat() {
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
    public void dl_updateEmployeeAddressEmpty() {
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
    public void dm_updateEmployeeAddressTooLong() {
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
    public void dn_updateEmployeeNothingChanged() {
        try {
            Employee testEmployee = new Employee(-1, "test", "test", "test@email.com",
                    "test", "111-222-3333", "test");
            employeeSAO.updateEmployee(testEmployee);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Nothing has changed, please try again!");
        }
    }

    @Test
    public void do_updateEmployeeNoneFound() {
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
    public void dp_updateEmployeeSuccess() {
        Employee result = employeeSAO.updateEmployee(updateEmployee);
        Assert.assertEquals(updateEmployee.getFirstName(), result.getFirstName());
        Assert.assertEquals(updateEmployee.getLastName(), result.getLastName());
        Assert.assertEquals(updateEmployee.getEmail(), result.getEmail());
        Assert.assertEquals(updateEmployee.getPhoneNumber(), result.getPhoneNumber());
        Assert.assertEquals(updateEmployee.getAddress(), result.getAddress());
    }

    @Test
    public void ea_changePasswordEmpty() {
        try {
            Employee testEmployee = new Employee(currentEmployeeId, "first", "last",
                    "e@mail.com", "", "111-222-3333", "123 Test");
            employeeSAO.changePassword(testEmployee, "test");
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The password field cannot be left empty, please try again!");
        }
    }

    @Test
    public void eb_changePasswordTooLong() {
        try {
            Employee testEmployee = new Employee(currentEmployeeId, "first", "last",
                    "e@mail.com", "this is too long and so it should raise the desired error message " +
                    "and then this test will pass", "111-222-3333", "123 Test");
            employeeSAO.changePassword(testEmployee, "test");
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The password field cannot exceed 60 characters, please " +
                    "try again!");
        }
    }

    @Test
    public void ec_changePasswordConfirmationPasswordEmpty() {
        try {
            Employee testEmployee = new Employee(currentEmployeeId, "first", "last",
                    "e@mail.com", "test", "111-222-3333", "123 Test");
            employeeSAO.changePassword(testEmployee, "");
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The confirmation password field cannot be left empty, " +
                    "please try again!");
        }
    }

    @Test
    public void ed_changePasswordConfirmationPasswordTooLong() {
        try {
            Employee testEmployee = new Employee(currentEmployeeId, "first", "last",
                    "e@mail.com", "test", "111-222-3333", "123 Test");
            employeeSAO.changePassword(testEmployee, "this is too long and so it should fail and" +
                    " then this test will be successful and pass because this input is entirely way to long to be " +
                    "allowed to pass");
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The confirmation password field cannot exceed 60 " +
                    "characters, please try again!");
        }
    }

    @Test
    public void ee_changePasswordPasswordsDoNotMatch() {
        try {
            Employee testEmployee = new Employee(currentEmployeeId, "first", "last",
                    "e@mail.com", "test", "111-222-3333", "123 Test");
            employeeSAO.changePassword(testEmployee, "wrong");
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "The confirmation password field must match the password " +
                    "field, please try again!");
        }
    }

    @Test
    public void ee_changePasswordEmployeeNotFound() {
        try {
            Employee testEmployee = new Employee(-50000000, "first", "last",
                    "e@mail.com", "password", "111-222-3333", "123 Test");
            employeeSAO.changePassword(testEmployee, "password");
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "No employee found, please try again!");
        }
    }

    @Test
    public void ef_changePasswordNothingChanged() {
        try {
            Employee testEmployee = new Employee(currentEmployeeId, "first", "last",
                    "e@mail.com", successEmployee.getPassword(), "111-222-3333", "123 Test");
            employeeSAO.changePassword(testEmployee, successEmployee.getPassword());
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "Nothing has changed, please try again!");
        }
    }

    @Test
    public void eg_changePasswordSuccess() {
        Employee result = employeeSAO.changePassword(updateEmployee, updateEmployee.getPassword());
        Assert.assertEquals(updateEmployee.getPassword(), result.getPassword());
    }

    @Test
    public void fa_deleteEmployeeNotFound() {
        try {
            employeeSAO.deleteEmployee(-500000000);
            Assert.fail();
        } catch (GeneralError error) {
            Assert.assertEquals(error.getMessage(), "No employee found, please try again!");
        }
    }

    @Test
    public void fb_deleteEmployeeSuccess() {
        int result = employeeSAO.deleteEmployee(currentEmployeeId);
        Assert.assertTrue(result != 0);
    }
}
