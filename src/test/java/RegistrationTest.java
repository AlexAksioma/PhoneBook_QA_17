import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.charset.Charset;
import java.util.Random;

public class RegistrationTest extends TestBase{

    @BeforeMethod
    public void preCondition(){
        app.getUser().pause(5);
        if(app.getUser().isLogged())
            app.getUser().logout();
    }

    @Test
    public void registrationPositiveTest() {
        User data = new User()
                .withEmail(app.getUser().generateRandomStringEmail())
                .withPassword("Qwerty123!_");
        app.getUser().openLoginRegistrationForm();
        app.getUser().filLoginRegistrationForm(data);
        app.getUser().submitRegistration();
        app.getUser().pause(2000);
        Assert.assertTrue(app.getUser().isLogged());
    }


    /*@Test
    public void registrationPositiveTest_ForDel() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        String email = "qwerty" + i + "@gmail.com";
        String password = "Qwerty123!_";
        System.out.println(email);
        openLoginRegistrationForm();
        filLoginRegistrationForm(email, password);
        submitRegistration();
        pause(3);
        Assert.assertTrue(isElementPresent(By.xpath("//button[text()='Sign Out']")));
    }*/

    @Test
    public void registrationNegativeTest_Email_WO_Dog(){
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        String email = "qwerty" + i + "gmail.com";
        String password = "Qwerty123!_";
        User data = new User()
                .withEmail(email)
                .withPassword(password);
        app.getUser().openLoginRegistrationForm();
        app.getUser().filLoginRegistrationForm(data);
        app.getUser().submitRegistration();
        app.getUser().pause(3);

        Assert.assertTrue(app.getUser().isErrorMessageInFormat());
        Assert.assertTrue(app.getUser().isAlertPresent());

        //Assert.assertFalse(app.getUser().isElementPresent(By.xpath("//button[text()='Sign Out'")));
    }




}
