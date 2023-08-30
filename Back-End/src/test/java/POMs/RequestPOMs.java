package POMs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RequestPOMs {
    private WebDriver driver;
    private By manageRequestsTab = By.id("manageRequestsTab");

    private By manageRequestsButton = By.id("manageRequestsButton");

    public RequestPOMs(WebDriver driver) {
        this.driver = driver;
    }

    public void clickManageRequestsTab() {
        driver.findElement(manageRequestsTab).click();
    }

    public void clickManageRequestsButton() {
        driver.findElement(manageRequestsButton).click();
    }
}
