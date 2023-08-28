package POMs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmployeePOMs {
    private WebDriver driver;
    private By loginEmailInput = By.id("element-id(not currently the real id)");

    public EmployeePOMs(WebDriver driver) {
        this.driver = driver;
    }

    public void loginEmailInput(String comment) {
        driver.findElement(loginEmailInput).sendKeys(comment);
    }
}
