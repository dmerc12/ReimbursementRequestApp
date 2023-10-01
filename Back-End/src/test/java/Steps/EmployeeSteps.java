package Steps;

import Runner.Runner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class EmployeeSteps {
    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        Runner.driver.get("http://localhost:5173/");
    }

    @Given("I am on the home landing page")
    public void i_am_on_the_home_landing_page() {
        Runner.driver.get("http://localhost:5173/home");
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

    @When("I input {string} in the login email input")
    public void i_input_in_the_login_email_input(String email) {
        Runner.employeePOM.setLoginEmailInput(email);
    }
    @When("I input {string} in the login password input")
    public void i_input_in_the_login_password_input(String password) {
        Runner.employeePOM.setLoginPasswordInput(password);
    }
    @When("I click the login button")
    public void i_click_the_login_button() {
        Runner.employeePOM.clickLoginButton();
    }

    @When("I click the logout button")
    public void i_click_the_logout_button() {
        Runner.employeePOM.clickLogoutButton();
    }

    @When("I click the manage information button")
    public void i_click_the_manage_information_button() {
        Runner.employeePOM.clickManageInformationButton();
    }

    @When("I click the update information modal")
    public void i_click_the_update_information_modal() {
        Runner.employeePOM.clickUpdateInformationModal();
    }

    @When("I input {string} in the update first name input")
    public void i_input_in_the_update_first_name_input(String firstName) {
        Runner.employeePOM.setUpdateFirstName(firstName);
    }

    @When("I input {string} in the update last name input")
    public void i_input_in_the_update_last_name_input(String lastName) {
        Runner.employeePOM.setUpdateLastName(lastName);
    }

    @When("I input {string} in the update email input")
    public void i_input_in_the_update_email_input(String email) {
        Runner.employeePOM.setUpdateEmail(email);
    }

    @When("I input {string} in the update phone number input")
    public void i_input_in_the_update_phone_number_input(String phoneNumber) {
        Runner.employeePOM.setUpdatePhoneNumber(phoneNumber);
    }

    @When("I input {string} in the update street address input")
    public void i_input_in_the_update_street_address_input(String streetAddress) {
        Runner.employeePOM.setUpdateStreetAddress(streetAddress);
    }

    @When("I input {string} in the update city input")
    public void i_input_in_the_update_city_input(String city) {
        Runner.employeePOM.setUpdateCity(city);
    }

    @When("I input {string} in the update state input")
    public void i_input_in_the_update_state_input(String state) {
        Runner.employeePOM.setUpdateState(state);
    }

    @When("I input {string} in the update zip code input")
    public void i_input_in_the_update_zip_code_input(String zipCode) {
        Runner.employeePOM.setUpdateZipCode(zipCode);
    }

    @When("I click the update information button")
    public void i_click_the_update_information_button() {
        Runner.employeePOM.clickUpdateInformationButton();
    }

    @Then("I remain on the update information page")
    public void i_remain_on_the_update_information_page() {
        Assert.assertEquals(Runner.driver.getCurrentUrl(), "http://localhost:5173/update-information");
    }

    @Then("I am on the register page")
    public void the_employee_is_on_the_register_page() {
        Assert.assertEquals(Runner.driver.getCurrentUrl(), "http://localhost:5173/register");
    }

    @Then("I remain on the login page")
    public void i_remain_on_the_login_page() {
        Runner.wait.until(ExpectedConditions.urlToBe("http://localhost:5173/"));
        Assert.assertEquals(Runner.driver.getCurrentUrl(), "http://localhost:5173/");
    }

    @Then("I am routed to the home page")
    public void i_am_routed_to_the_home_page() {
        Runner.wait.until(ExpectedConditions.urlToBe("http://localhost:5173/home"));
        Assert.assertEquals(Runner.driver.getCurrentUrl(), "http://localhost:5173/home");
    }

    @Then("I am routed to the login page")
    public void i_am_routed_to_the_login_page() {
        Runner.wait.until(ExpectedConditions.urlToBe("http://localhost:5173/login"));
        Assert.assertEquals(Runner.driver.getCurrentUrl(), "http://localhost:5173/login");
    }
}
