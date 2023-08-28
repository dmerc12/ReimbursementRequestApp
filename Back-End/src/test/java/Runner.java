import POMs.*;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
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
    public static WebDriver edgeDriver;
    public static WebDriverWait wait;
    public static RequestPOMs requestPOM;
    public static CategoryPOMs categoryPOM;
    public static EmployeePOMs employeePOM;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.edge.driver", "src/test/resources/msedgedriver.exe");
        edgeDriver = new EdgeDriver();

        requestPOM = new RequestPOMs(edgeDriver);
        categoryPOM = new CategoryPOMs(edgeDriver);
        employeePOM = new EmployeePOMs(edgeDriver);

        edgeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        wait = new WebDriverWait(edgeDriver, Duration.ofSeconds(2));
    }

    @AfterClass
    public static void teardown() {
        edgeDriver.quit();
    }

}
