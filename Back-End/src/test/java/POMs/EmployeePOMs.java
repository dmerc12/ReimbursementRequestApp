package POMs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmployeePOMs {
    public WebDriver driver;
    private By registerTab = By.id("registerTab");
    private By loginTab = By.id("loginTab");
    private By manageInformationTab = By.id("manageInformationTab");
    private By logoutButton = By.id("logoutButton");
    private By manageInformationButton = By.id("manageInformationButton");
    private By loginEmailInput = By.id("loginEmail");
    private By loginPasswordInput = By.id("loginPassword");
    private By loginButton = By.id("loginButton");
    private By registerFirstName = By.id("registerFirstName");
    private By registerLastName = By.id("registerLastName");
    private By registerEmail = By.id("registerEmail");
    private By registerPassword = By.id("registerPassword");
    private By registerConfirmationPassword = By.id("registerConfirmationPassword");
    private By registerPhoneNumber = By.id("registerPhoneNumber");
    private By registerStreetAddress = By.id("registerStreetAddress");
    private By registerCity = By.id("registerCity");
    private By registerState = By.id("registerState");
    private By registerZipCode = By.id("registerZipCode");
    private By registerButton = By.id("registerButton");
    private By updateInformationModal = By.id("updateInformationModal");
    private By updateFirstName = By.id("updateFirstName");
    private By updateLastName = By.id("updateLastName");
    private By updateEmail = By.id("updateEmail");
    private By updatePhoneNumber = By.id("updatePhoneNumber");
    private By updateStreetAddress = By.id("updateStreetAddress");
    private By updateCity = By.id("updateCity");
    private By updateState = By.id("updateState");
    private By updateZipCode = By.id("updateZipCode");
    private By updateInformationButton = By.id("updateInformationButton");
    private By changePasswordModal = By.id("changePasswordModal");
    private By newPassword = By.id("newPassword");
    private By newConfirmationPassword = By.id("newConfirmationPassword");
    private By changePasswordButton = By.id("changePasswordButton");
    private By deleteInformationModal = By.id("deleteInformationModal");
    private By deleteInformationButton = By.id("deleteInformationButton");

    public EmployeePOMs(WebDriver driver) {
        this.driver = driver;
    }

    public void clickRegisterTab() {
        driver.findElement(registerTab).click();
    }

    public void clickLoginTab() {
        driver.findElement(loginTab).click();
    }

    public void clickManageInformationTab() {
        driver.findElement(manageInformationTab).click();
    }
    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    public void clickManageInformationButton() {
        driver.findElement(manageInformationButton).click();
    }

    public void setLoginEmailInput(String email) {
        driver.findElement(loginEmailInput).sendKeys(email);
    }

    public void setLoginPasswordInput(String password) {
        driver.findElement(loginPasswordInput).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void setRegisterFirstName(String firstName) {
        driver.findElement(registerFirstName).sendKeys(firstName);
    }

    public void setRegisterLastName(String lastName) {
        driver.findElement(registerLastName).sendKeys(lastName);
    }

    public void setRegisterEmail(String email) {
        driver.findElement(registerEmail).sendKeys(email);
    }

    public void setRegisterPassword(String password) {
        driver.findElement(registerPassword).sendKeys(password);
    }

    public void setRegisterConfirmationPassword(String confirmationPassword) {
        driver.findElement(registerConfirmationPassword).sendKeys(confirmationPassword);
    }

    public void setRegisterPhoneNumber(String phoneNumber) {
        driver.findElement(registerPhoneNumber).sendKeys(phoneNumber);
    }

    public void setRegisterStreetAddress(String streetAddress) {
        driver.findElement(registerStreetAddress).sendKeys(streetAddress);
    }

    public void setRegisterCity(String city) {
        driver.findElement(registerCity).sendKeys(city);
    }

    public void setRegisterState(String state) {
        driver.findElement(registerState).sendKeys(state);
    }

    public void setRegisterZipCode(String zipCode) {
        driver.findElement(registerZipCode).sendKeys(zipCode);
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public void clickUpdateInformationModal() {
        driver.findElement(updateInformationModal).click();
    }

    public void setUpdateFirstName(String firstName) {
        driver.findElement(updateFirstName).sendKeys(firstName);
    }

    public void setUpdateLastName(String lastName) {
        driver.findElement(updateLastName).sendKeys(lastName);
    }

    public void setUpdateEmail(String email) {
        driver.findElement(updateEmail).sendKeys(email);
    }

    public void setUpdatePhoneNumber(String phoneNumber) {
        driver.findElement(updatePhoneNumber).sendKeys(phoneNumber);
    }

    public void setUpdateStreetAddress(String streetAddress) {
        driver.findElement(updateStreetAddress).sendKeys(streetAddress);
    }

    public void setUpdateCity(String city) {
        driver.findElement(updateCity).sendKeys(city);
    }

    public void setUpdateState(String state) {
        driver.findElement(updateState).sendKeys(state);
    }

    public void setUpdateZipCode(String zipCode) {
        driver.findElement(updateZipCode).sendKeys(zipCode);
    }

    public void clickUpdateInformationButton() {
        driver.findElement(updateInformationButton).click();
    }
    public void clickChangePasswordModal() {
        driver.findElement(changePasswordModal).click();
    }

    public void setNewPassword(String password) {
        driver.findElement(newPassword).sendKeys(password);
    }

    public void setNewConfirmationPassword(String confirmationPassword) {
        driver.findElement(newConfirmationPassword).sendKeys(confirmationPassword);
    }

    public void clickChangePasswordButton() {
        driver.findElement(changePasswordButton).click();
    }

    public void clickDeleteInformationModal() {
        driver.findElement(deleteInformationModal).click();
    }

    public void clickDeleteInformationButton() {
        driver.findElement(deleteInformationButton).click();
    }
}
