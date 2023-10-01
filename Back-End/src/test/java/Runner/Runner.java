package Runner;

import POMs.*;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.edge.EdgeDriver;
import java.time.Duration;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/Features"},
        glue = "Steps",
        plugin = {"pretty", "html:documentation/Selenium-Tests-Report.html"}
)

public class Runner {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static RequestPOMs requestPOM;
    public static CategoryPOMs categoryPOM;
    public static EmployeePOMs employeePOM;

    @BeforeClass
    public static void setup() {
        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("win")) {
            System.setProperty("webdriver.edge.driver", "src/test/resources/msedgedriver.exe");
            driver = new EdgeDriver();
        } else if (osName.contains("mac")) {
            driver = new SafariDriver();
        } else {
            throw new UnsupportedOperationException("Unsupported OS for these tests!");
        }

        requestPOM = new RequestPOMs(driver);
        categoryPOM = new CategoryPOMs(driver);
        employeePOM = new EmployeePOMs(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterClass
    public static void teardown() {
        driver.quit();
    }

}
