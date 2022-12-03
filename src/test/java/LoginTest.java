import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @BeforeMethod
    public void preCondition(){
        app.getUser().pause(5);
        if(app.getUser().isLogged())
            app.getUser().logout();
    }

    @Test
    public void positiveLoginTest(){
        //app.getUser().openLoginRegistrationForm();
        //app.getUser().filLoginRegistrationForm("qwerty3171@gmail.com","Qwerty123!_");
        //app.getUser().submitLogin();
        //app.getUser().pause(5);
        app.getUser().login("qwerty3171@gmail.com","Qwerty123!_");
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//button[text()='Sign Out']")));

    }

    @Test
    public void negativeLoginTest_Email_WO_Dog(){
        app.getUser().openLoginRegistrationForm();
        app.getUser().filLoginRegistrationForm("qwerty3171gmail.com","Qwerty123!_");
        app.getUser().submitLogin();
        app.getUser().pause(15);

        //Assert.assertTrue(isElementPresent(By.xpath("//button[text()='Sign Out']")));
    }

    @AfterMethod
    public void tearDown(){
        //wd.quit();
    }
}
