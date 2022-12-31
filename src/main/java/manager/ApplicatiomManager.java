package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class ApplicatiomManager {

    Logger logger = LoggerFactory.getLogger(ApplicatiomManager.class);

    EventFiringWebDriver wd;
    //WebDriver wd;
    HelperUser user;
    HelperContact contact;

    String browser;

    public ApplicatiomManager(String browser) {
        this.browser = browser;
    }

    public HelperUser getUser() {
        return user;
    }

    public HelperContact getContact() {
        return contact;
    }

    @BeforeSuite
    public void init() {
       //wd = new ChromeDriver();
        //wd = new EventFiringWebDriver(new FirefoxDriver());
        //wd = new EventFiringWebDriver(new EdgeDriver());
        //wd = new EventFiringWebDriver(new ChromeDriver());
        if(browser.equals(BrowserType.FIREFOX)){
            wd = new EventFiringWebDriver(new FirefoxDriver());
            logger.info("Tests on FireFox");
        }
        else if(browser.equals(BrowserType.CHROME)){
            wd = new EventFiringWebDriver(new ChromeDriver());
            logger.info("Tests on Chrome");
        }
        wd.register(new MyListener());
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //wd.navigate().to("https://contacts-app.tobbymarshall815.vercel.app/home"); //old
        wd.navigate().to("https://telranedu.web.app/home"); //new

        user = new HelperUser(wd);
        contact = new HelperContact(wd);
    }

    @AfterSuite
    public void stop() {
        //wd.quit();
    }
}
