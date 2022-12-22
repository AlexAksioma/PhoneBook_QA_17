import manager.ApplicatiomManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class TestBase {
    //WebDriver wd;
    public static ApplicatiomManager app = new ApplicatiomManager();

    Logger logger = LoggerFactory.getLogger(TestBase.class);
    @BeforeSuite
    public void setUp() {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }


    @BeforeMethod
    public void startTest(Method method){
        logger.info("Start test "+ method.getName());
    }
    @AfterMethod
    public void stopTest(Method method){
        logger.info("Stop test "+ method.getName());
        logger.info("========================================================================================");
    }



}
