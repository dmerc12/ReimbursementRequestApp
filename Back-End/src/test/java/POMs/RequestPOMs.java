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
}
