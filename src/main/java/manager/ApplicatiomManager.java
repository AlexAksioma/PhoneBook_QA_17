package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class ApplicatiomManager {
    WebDriver wd;
    HelperUser user;

    public HelperUser getUser() {
        return user;
    }

    @BeforeSuite
    public void init() {
        wd = new ChromeDriver();
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //wd.navigate().to("https://contacts-app.tobbymarshall815.vercel.app/home"); //old
        wd.navigate().to("https://telranedu.web.app/home"); //new

        user = new HelperUser(wd);
    }

    @AfterSuite
    public void stop() {
        //wd.quit();
    }
}
