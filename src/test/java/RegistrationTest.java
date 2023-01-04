import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class RegistrationTest extends TestBase{

    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        //app.getUser().pause(5);
        if(app.getUser().isLogged())
            app.getUser().logout();
    }

    @Test(groups = {"positivegroup"})
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

        Assert.assertTrue(app.getUser().isErrorMessageInFormat("Wrong email or password"));
        Assert.assertTrue(app.getUser().isAlertPresent());

    }

    @Test
    public void registrationNegativeTest_Email_Password_isRegistered(){
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        String email = "qwertyqwerty" + i + "@gmail.com";
        String password = "Qwerty!_"+i;
        User data = new User()
                .withEmail(email)
                .withPassword(password);
        app.getUser().openLoginRegistrationForm();
        app.getUser().filLoginRegistrationForm(data);
        app.getUser().submitRegistration();//registered new user with email and password
        app.getUser().pause(2000);
        app.getUser().logout();

        app.getUser().openLoginRegistrationForm();
        app.getUser().filLoginRegistrationForm(data);
        app.getUser().submitRegistration();

        Assert.assertTrue(app.getUser().isErrorMessageInFormat("User already exist"));
        Assert.assertTrue(app.getUser().isAlertPresent());


    }
    @AfterClass(alwaysRun = true)
    public void logout(){
        if(app.getUser().isLogged())
            app.getUser().logout();
        app.getUser().clickButtonHome();
    }



}
