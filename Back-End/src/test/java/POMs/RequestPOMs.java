package POMs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RequestPOMs {
    private WebDriver driver;
    private By manageRequestsTab = By.id("manageRequestsTab");

    private By manageRequestsButton = By.id("manageRequestsButton");
    private By addRequestModal = By.id("addRequestModal");
    private By addRequestCategoryDropdown = By.id("addRequestCategoryDropdown");
    private By addRequestComment = By.id("addRequestComment");
    private By addRequestAmount = By.id("addRequestAmount");
    private By addRequestButton = By.id("addRequestButton");
    private By updateRequestModal = By.id("updateRequestModal");
    private By updateRequestCategoryDropDown = By.id("updateRequestCategoryDropDown");
    private By updateRequestComment = By.id("updateRequestComment");
    private By updateRequestAmount = By.id("updateRequestAmount");
    private By updateRequestButton = By.id("updateRequestButton");

    public RequestPOMs(WebDriver driver) {
        this.driver = driver;
    }

    public void clickManageRequestsTab() {
        driver.findElement(manageRequestsTab).click();
    }

    public void clickManageRequestsButton() {
        driver.findElement(manageRequestsButton).click();
    }
    public void clickAddRequestModal() {
        driver.findElement(addRequestModal).click();
    }

    public void setAddRequestCategoryDropdown(String category) {
        driver.findElement(addRequestCategoryDropdown).sendKeys(category);
    }

    public void setAddRequestComment(String comment) {
        driver.findElement(addRequestComment).sendKeys(comment);
    }

    public void setAddRequestAmount(double amount) {
        driver.findElement(addRequestAmount).sendKeys(String.valueOf(amount));
    }

    public void clickAddRequestButton() {
        driver.findElement(addRequestButton).click();
    }

    public void clickUpdateRequestModal() {
        driver.findElement(updateRequestModal).click();
    }

    public void setUpdateRequestCategoryDropDown(String category) {
        driver.findElement(updateRequestCategoryDropDown).sendKeys(category);
    }

    public void setUpdateRequestComment(String comment) {
        driver.findElement(updateRequestComment).sendKeys(comment);
    }

    public void setUpdateRequestAmount(double amount) {
        driver.findElement(updateRequestAmount).sendKeys(String.valueOf(amount));
    }

    public void clickUpdateRequestButton() {
        driver.findElement(updateRequestButton).click();
    }
}
