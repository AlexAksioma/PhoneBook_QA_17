import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class AddNewContactTest extends TestBase{
    @BeforeClass
    public void login(){
        app.getUser().login("qwerty3171@gmail.com","Qwerty123!_");
    }

    @Test
    public void addNewContactPositiveTest(){

        app.getUser().click(By.xpath("//a[@href='/add']"));
        app.getUser().pause(5);
        String name = app.getUser().generateRandomString_a_z(7);
        System.out.println(name);
        app.getUser().fillAddForm(name, app.getUser().generateRandomString_a_z(15),
                app.getUser().generateRandomStringNumber(11), app.getUser().generateRandomStringEmail(),
                app.getUser().generateRandomString_a_z(25), app.getUser().generateRandomString_a_z(50));
        app.getUser().click(By.xpath("//b[text()='Save']"));

        //var 1
        Assert.assertTrue(app.getUser().returnContainsElement(By.xpath("(//div[@class='contact-item_card__2SOIM'])[last()]")).contains(name));

        //app.getUser().click(By.xpath("(//div[@class='contact-item_card__2SOIM'])[last()]")); //click last element ADD

        //var 2
        List<WebElement> elementsAddForm = app.getUser().elementsPresent(By.xpath("//div[@class='contact-item_card__2SOIM']"));
        for (WebElement e: elementsAddForm) {
            if(e.getText().contains(name)){
                Assert.assertTrue(true);
                return;
            }
        }


    }
}
