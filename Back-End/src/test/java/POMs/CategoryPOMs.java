package POMs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CategoryPOMs {
    private WebDriver driver;
    private By addCategoryNameInput = By.id("element-id(not currently the real id)");

    public CategoryPOMs(WebDriver driver) {
        this.driver = driver;
    }

    public void addCategoryNameInput(String comment) {
        driver.findElement(addCategoryNameInput).sendKeys(comment);
    }
}
