import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class DeleteContactTest extends TestBase{
    @BeforeMethod
    public void login(){
        if(app.getUser().isLogged()) {
            app.getUser().click(By.xpath("//a[@href='/contacts']"));
            app.getUser().pause(3000);}
        else
            app.getUser().login("qwerty3171@gmail.com","Qwerty123!_");
    }

    @Test
    public void deleteLastContactPositiveTest(){
        app.getUser().click(By.xpath("//a[@href='/add']"));
        app.getUser().pause(5);
        String name = app.getUser().generateRandomString_a_z(7);
        String lastName = app.getUser().generateRandomString_a_z(15);
        System.out.println(name+" "+lastName);
        app.getUser().fillAddForm(name, lastName,
                app.getUser().generateRandomStringNumber(11), app.getUser().generateRandomStringEmail(),
                app.getUser().generateRandomString_a_z(25), app.getUser().generateRandomString_a_z(50));
        app.getUser().click(By.xpath("//b[text()='Save']"));
        List<WebElement> elementsAddForm = app.getUser().elementsPresent(By.xpath("//div[@class='contact-item_card__2SOIM']"));
        int quantityElements = elementsAddForm.size();

        app.getUser().click(By.xpath("//div[@class='contact-item_card__2SOIM'][last()]")); //click last element ADD
        app.getUser().click(By.xpath("//button[text()='Remove']")); //delete last element
        app.getUser().pause(3000);

        Assert.assertEquals(quantityElements-1, app.getUser().elementsPresent(By.xpath("//div[@class='contact-item_card__2SOIM']")).size());

    }
}
