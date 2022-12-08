import models.User;
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
        User data = new User().withEmail("qwerty3171@gmail.com").withPassword("Qwerty123!_");
        app.getUser().login(data);
        Assert.assertTrue(app.getUser().isLogged());

    }

    @Test
    public void negativeLoginTest_Email_WO_Dog(){
        User data = new User().withEmail("qwerty3171gmail.com").withPassword("Qwerty123!_");
        app.getUser().openLoginRegistrationForm();
        app.getUser().filLoginRegistrationForm(data);
        app.getUser().submitLogin();
        app.getUser().pause(2000);



        //Assert.assertTrue(isElementPresent(By.xpath("//button[text()='Sign Out']")));
    }

    @AfterMethod
    public void tearDown(){
        //wd.quit();
    }
}
