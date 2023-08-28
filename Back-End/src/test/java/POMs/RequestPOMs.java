package POMs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RequestPOMs {
    private WebDriver driver;
    private By addRequestCommentInput = By.id("element-id(not currently the real id)");

    public RequestPOMs(WebDriver driver) {
        this.driver = driver;
    }

    public void addRequestCommentInput(String comment) {
        driver.findElement(addRequestCommentInput).sendKeys(comment);
    }
}
