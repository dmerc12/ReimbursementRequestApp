package Steps;

import Runner.Runner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmployeeSteps {
    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        Runner.edgeDriver.get("http://localhost:5173/");
    }
    @When("I click the register tab in the nav bar")
    public void i_click_the_register_tab_in_the_nav_bar() {
        Runner.employeePOM.clickRegisterTab();
    }
    @When("I input {string} in the register first name input")
    public void i_input_first_name_in_the_register_first_name_input(String firstName) {
        Runner.employeePOM.setRegisterFirstName(firstName);
    }
    @When("I input {string} in the register last name input")
    public void i_input_last_name_in_the_register_last_name_input(String lastName) {
        Runner.employeePOM.setRegisterLastName(lastName);
    }
    @When("I input {string} in the register email input")
    public void i_input_email_in_the_register_email_input(String email) {
        Runner.employeePOM.setRegisterEmail(email);
    }
    @When("I input {string} in the register password input")
    public void i_input_password_in_the_register_password_input(String password) {
        Runner.employeePOM.setRegisterPassword(password);
    }
    @When("I input {string} in the register confirmation password input")
    public void i_input_confirmation_password_in_the_register_confirmation_password_input(String confirmationPassword) {
        Runner.employeePOM.setRegisterConfirmationPassword(confirmationPassword);
    }
    @When("I input {string} in the register phone number input")
    public void i_input_phone_number_in_the_register_phone_number_input(String phoneNumber) {
        Runner.employeePOM.setRegisterPhoneNumber(phoneNumber);
    }
    @When("I input {string} in the register street address input")
    public void i_input_street_address_in_the_register_street_address_input(String streetAddress) {
        Runner.employeePOM.setRegisterStreetAddress(streetAddress);
    }
    @When("I input {string} in the register city input")
    public void i_input_city_in_the_register_city_input(String city) {
        Runner.employeePOM.setRegisterCity(city);
    }
    @When("I input {string} in the register state input")
    public void i_input_ok_in_the_register_state_input(String state) {
        Runner.employeePOM.setRegisterState(state);
    }
    @When("I input {string} in the register zip code input")
    public void i_input_in_the_register_zip_code_input(String zipCode) {
        Runner.employeePOM.setRegisterZipCode(zipCode);
    }
    @When("I click the register button")
    public void i_click_the_register_button() {
        Runner.employeePOM.clickRegisterButton();
    }
    @Then("I am on the register page")
    public void the_employee_is_on_the_register_page() {
        Assert.assertEquals(Runner.edgeDriver.getCurrentUrl(), "http://localhost:5173/register");
    }

    @Then("I am back on the login page")
    public void the_employee_is_on_the_login_page() {
        Runner.wait.until(ExpectedConditions.urlToBe("http://localhost:5173/login"));
        Assert.assertEquals(Runner.edgeDriver.getCurrentUrl(), "http://localhost:5173/login");
    }

}
