import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @BeforeMethod
    public void preCondition(){
        pause(5);
        if(isLogged())
            logout();
    }

    @Test
    public void positiveLoginTest(){
        openLoginRegistrationForm();
        filLoginRegistrationForm("qwerty3171@gmail.com","Qwerty123!_");
        submitLogin();
        pause(5);
        Assert.assertTrue(isElementPresent(By.xpath("//button[text()='Sign Out']")));

    }

    @Test
    public void negativeLoginTest_Email_WO_Dog(){
        openLoginRegistrationForm();
        filLoginRegistrationForm("qwerty3171gmail.com","Qwerty123!_");
        submitLogin();
        pause(15);

        //Assert.assertTrue(isElementPresent(By.xpath("//button[text()='Sign Out']")));
    }

    @AfterMethod
    public void tearDown(){
        //wd.quit();
    }
}
