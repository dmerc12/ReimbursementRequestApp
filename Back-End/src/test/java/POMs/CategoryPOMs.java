package POMs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CategoryPOMs {
    private WebDriver driver;
    private By manageCategoriesTab = By.id("manageCategoriesTab");
    private By manageCategoriesButton = By.id("manageCategoriesButton");
    private By addCategoryModal = By.id("addCategoryModal");
    private By newCategoryName = By.id("newCategoryName");
    private By addCategoryButton = By.id("addCategoryButton");

    public CategoryPOMs(WebDriver driver) {
        this.driver = driver;
    }


    public void clickManageCategoriesTab() {
        driver.findElement(manageCategoriesTab).click();
    }

    public void clickManageCategoriesButton() {
        driver.findElement(manageCategoriesButton).click();
    }
    public void clickAddCategoryModal() {
        driver.findElement(addCategoryModal).click();
    }

    public void setNewCategoryName(String categoryName) {
        driver.findElement(newCategoryName).sendKeys(categoryName);
    }

    public void clickAddCategoryButton() {
        driver.findElement(addCategoryButton).click();
    }

}
