package POMs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmployeePOMs {
    private WebDriver driver;
    private By registerTab = By.id("registerTab");
    private By loginTab = By.id("loginTab");
    private By manageRequestsTab = By.id("manageRequestsTab");
    private By manageCategoriesTab = By.id("manageCategoriesTab");
    private By manageInformationTab = By.id("manageInformationTab");
    private By loginEmailInput = By.id("loginEmail");
    private By loginPasswordInput = By.id("loginPassword");
    private By loginButton = By.id("loginButton");

    public EmployeePOMs(WebDriver driver) {
        this.driver = driver;
    }

    public void clickRegisterTab() {
        driver.findElement(registerTab).click();
    }

    public void clickLoginTab() {
        driver.findElement(loginTab).click();
    }

    public void clickManageRequestsTab() {
        driver.findElement(manageRequestsTab).click();
    }

    public void clickManageCategoriesTab() {
        driver.findElement(manageCategoriesTab).click();
    }

    public void clickManageInformationTab() {
        driver.findElement(manageInformationTab).click();
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
}
