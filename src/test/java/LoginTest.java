import models.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        logger.info("start before method");
        if(app.getUser().isLogged())
            app.getUser().logout();
    }

    @BeforeGroups("positivegroup")
    public void initBeforeGroup(){
        app.init();
    }

    @Test(groups = {"positivegroup"})
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

        Assert.assertTrue(app.getUser().isErrorMessageInFormat("Wrong email or password"));
        Assert.assertTrue(app.getUser().isAlertPresent());
    }

    @Test
    public void negativeLoginTest_User_not_Register(){
        User data = new User().withEmail("qwertyqwerty@gmail.com").withPassword("Qwerty123!_");
        app.getUser().openLoginRegistrationForm();
        app.getUser().filLoginRegistrationForm(data);
        app.getUser().submitLogin();
        app.getUser().pause(2000);

        //Assert.assertTrue(app.getUser().isErrorMessageInFormat("Wrong email or password"));
        Assert.assertTrue(app.getUser().isAlertPresent());
    }

    @AfterClass(alwaysRun = true)
    public void logout(){
        logger.info("start after class");
        if(app.getUser().isLogged())
            app.getUser().logout();
        app.getUser().clickButtonHome();
    }
}
