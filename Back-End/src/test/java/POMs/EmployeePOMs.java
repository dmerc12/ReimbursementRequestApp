package POMs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmployeePOMs {
    public  WebDriver driver;
    private final By homeTab = By.id("homeTab");
    private final By registerTab = By.id("registerTab");
    private final By loginTab = By.id("loginTab");
    private final By manageInformationTab = By.id("manageInformationTab");
    private final By logoutButton = By.id("logoutButton");
    private final By manageInformationButton = By.id("manageInformationButton");
    private final By loginEmailInput = By.id("loginEmail");
    private final By loginPasswordInput = By.id("loginPassword");
    private final By loginButton = By.id("loginButton");
    private final By registerFirstName = By.id("registerFirstName");
    private final By registerLastName = By.id("registerLastName");
    private final By registerEmail = By.id("registerEmail");
    private final By registerPassword = By.id("registerPassword");
    private final By registerConfirmationPassword = By.id("registerConfirmationPassword");
    private final By registerPhoneNumber = By.id("registerPhoneNumber");
    private final By registerStreetAddress = By.id("registerStreetAddress");
    private final By registerCity = By.id("registerCity");
    private final By registerState = By.id("registerState");
    private final By registerZipCode = By.id("registerZipCode");
    private final By registerButton = By.id("registerButton");
    private final By updateInformationModal = By.id("updateInformationModal");
    private final By updateFirstName = By.id("updateFirstName");
    private final By updateLastName = By.id("updateLastName");
    private final By updateEmail = By.id("updateEmail");
    private final By updatePhoneNumber = By.id("updatePhoneNumber");
    private final By updateStreetAddress = By.id("updateStreetAddress");
    private final By updateCity = By.id("updateCity");
    private final By updateState = By.id("updateState");
    private final By updateZipCode = By.id("updateZipCode");
    private final By updateInformationButton = By.id("updateInformationButton");
    private final By changePasswordModal = By.id("changePasswordModal");
    private final By newPassword = By.id("newPassword");
    private final By newConfirmationPassword = By.id("newConfirmationPassword");
    private final By changePasswordButton = By.id("changePasswordButton");
    private final By deleteInformationModal = By.id("deleteInformationModal");
    private final By deleteInformationButton = By.id("deleteInformationButton");

    public EmployeePOMs(WebDriver driver) {
        this.driver = driver;
    }

    public void clickHomeTab(){driver.findElement(homeTab).click();}

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
