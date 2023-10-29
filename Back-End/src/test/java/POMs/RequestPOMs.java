package POMs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RequestPOMs {
    public WebDriver driver;
    private final By manageRequestsTab = By.id("manageRequestsTab");

    private final By manageRequestsButton = By.id("manageRequestsButton");
    private final By addRequestModal = By.id("addRequestModal");
    private final By addRequestCategoryDropdown = By.id("addRequestCategoryDropdown");
    private final By addRequestComment = By.id("addRequestComment");
    private final By addRequestAmount = By.id("addRequestAmount");
    private final By addRequestButton = By.id("addRequestButton");
    private final By updateRequestCategoryDropDown = By.id("updateRequestCategoryDropDown");
    private final By updateRequestComment = By.id("updateRequestComment");
    private final By updateRequestAmount = By.id("updateRequestAmount");
    private final By updateRequestButton = By.id("updateRequestButton");
    private final By deleteRequestButton = By.id("deleteRequestButton");

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

    public void clickUpdateRequestModal(String requestId) {
        driver.findElement(By.id("updateRequestModal" + requestId)).click();
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
    public void clickDeleteRequestModal(String requestId) {
        driver.findElement(By.id("deleteRequestModal" + requestId)).click();
    }

    public void clickDeleteRequestButton() {
        driver.findElement(deleteRequestButton).click();
    }
}
