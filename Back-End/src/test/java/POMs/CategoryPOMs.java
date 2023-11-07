package POMs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CategoryPOMs {
    public WebDriver driver;
    private final By manageCategoriesTab = By.id("manageCategoriesTab");
    private final By manageCategoriesButton = By.id("manageCategoriesButton");
    private final By addCategoryModal = By.id("addCategoryModal");
    private final By newCategoryName = By.id("newCategoryName");
    private final By addCategoryButton = By.id("addCategoryButton");
    private final By updateCategoryName = By.id("updateCategoryName");
    private final By updateCategoryButton = By.id("updateCategoryButton");
    private final By deleteCategoryButton = By.id("deleteCategoryButton");

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
    public void clickUpdateCategoryModal(String categoryId) {
        String dynamicId = "updateCategoryModal" + categoryId;
        By dynamicLocator = By.id(dynamicId);
        driver.findElement(dynamicLocator).click();
    }

    public void setUpdateCategoryName(String categoryName) {
        driver.findElement(updateCategoryName).sendKeys(categoryName);
    }

    public void clickUpdateCategoryButton() {
        driver.findElement(updateCategoryButton).click();
    }

    public void clickDeleteCategoryModal(String categoryId) {
        String dynamicId = "deleteCategoryModal" + categoryId;
        By dynamicLocator = By.id(dynamicId);
        driver.findElement(dynamicLocator).click();
    }

    public void clickDeleteCategoryButton() {
        driver.findElement(deleteCategoryButton).click();
    }

}
