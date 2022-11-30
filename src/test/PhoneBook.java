import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class PhoneBook {
    WebDriver wd;

    @BeforeMethod
    public  void init(){
        wd = new ChromeDriver();
        wd.navigate().to("https://contacts-app.tobbymarshall815.vercel.app/home");
    }

    @Test
    public void LoginPositiveTest(){
        WebElement = wd.findElement(By.xpath("/a[text()='LOGIN']"));
    }


    @AfterMetod
    public void  tearDown(){
        wd.quit();
    }



}
