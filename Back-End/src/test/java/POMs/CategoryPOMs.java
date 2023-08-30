package POMs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CategoryPOMs {
    private WebDriver driver;
    private By manageCategoriesTab = By.id("manageCategoriesTab");
    private By manageCategoriesButton = By.id("manageCategoriesButton");

    public CategoryPOMs(WebDriver driver) {
        this.driver = driver;
    }


    public void clickManageCategoriesTab() {
        driver.findElement(manageCategoriesTab).click();
    }

    public void clickManageCategoriesButton() {
        driver.findElement(manageCategoriesButton).click();
    }

}
