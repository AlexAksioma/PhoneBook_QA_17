import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTest extends TestBase{

    @BeforeMethod
    public void preCondition(){
        app.getUser().pause(5);
        if(app.getUser().isLogged())
            app.getUser().logout();
    }

    @Test
    public void registrationPositiveTest() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        String email = "qwerty" + i + "@gmail.com";
        String password = "Qwerty123!_";
        System.out.println(email);
        app.getUser().openLoginRegistrationForm();
        app.getUser().filLoginRegistrationForm(email, password);
        app.getUser().submitRegistration();
        app.getUser().pause(3);
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//button[text()='Sign Out']")));
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
        System.out.println(email);
        app.getUser().openLoginRegistrationForm();
        app.getUser().filLoginRegistrationForm(email, password);
        app.getUser().submitRegistration();
        app.getUser().pause(3);
        Assert.assertFalse(app.getUser().isElementPresent(By.xpath("//button[text()='Sign Out'")));
    }


}
