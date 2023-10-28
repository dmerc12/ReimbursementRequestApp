package Steps;

import Runner.Runner;
import io.cucumber.java.en.When;


public class EmployeeSteps {
    @When("I click the register tab in the nav bar")
    public void i_click_the_register_tab_in_the_nav_bar() {
        Runner.employeePOM.clickRegisterTab();
    }

    @When("I click the login tab in the nav bar")
    public void i_click_the_login_tab_in_the_nav_bar() {
        Runner.employeePOM.clickLoginTab();
    }

    @When("I click the manage information tab in the nav bar")
    public void i_click_the_manage_information_tab_in_the_nav_bar() {
        Runner.employeePOM.clickManageInformationTab();
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

    @When("I click the change password modal")
    public void i_click_the_change_password_modal() {
        Runner.employeePOM.clickChangePasswordModal();
    }

    @When("I input {string} in the change password input")
    public void i_input_in_the_change_password_input(String password) {
        Runner.employeePOM.setNewPassword(password);
    }

    @When("I input {string} in the change password confirmation input")
    public void i_input_in_the_change_password_confirmation_input(String confirmationPassword) {
        Runner.employeePOM.setNewConfirmationPassword(confirmationPassword);
    }

    @When("I click the change password button")
    public void i_click_the_change_password_button() {
        Runner.employeePOM.clickChangePasswordButton();
    }

    @When("I click the delete profile modal")
    public void i_click_the_delete_profile_modal() {
        Runner.employeePOM.clickDeleteInformationModal();
    }

    @When("I click the delete profile button")
    public void i_click_the_delete_profile_button() {
        Runner.employeePOM.clickDeleteInformationButton();
    }
}
