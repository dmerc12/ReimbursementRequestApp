package Steps;

import Runner.Runner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class UniversalSteps {
    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        Runner.driver.get("http://localhost:5173/");
    }

    @Given("I am on the home landing page")
    public void i_am_on_the_home_page() {
        Runner.driver.get("http://localhost:5173/home");
    }

    @When("I click the home tab in the nav bar")
    public void i_click_the_home_tab_in_the_nav_bar() {
        Runner.employeePOM.clickHomeTab();
    }

    @Then("I should see a toast notification saying {string}")
    public void i_should_see_the_toast_notification_saying(String expectedToastText) {
        By toastLocator = By.cssSelector("toast");
        WebElement toastElement = Runner.wait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));
        String actualToastText = toastElement.getText();
        Assert.assertEquals(expectedToastText, actualToastText);
    }

    @Then("I am on a page with the title {string}")
    public void i_am_on_a_page_with_the_title(String expectedTitle) {
        String actualTitle = Runner.driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
}
