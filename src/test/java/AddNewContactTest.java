import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class AddNewContactTest extends TestBase {
    @BeforeClass
    public void login() {
        if (app.getUser().isLogged()) {
            app.getUser().click(By.xpath("//a[@href='/contacts']"));
            app.getUser().pause(3000);
        } else
            app.getUser().login("qwerty3171@gmail.com", "Qwerty123!_");
    }

    @Test
    public void addNewContactPositiveTest() {
        app.getUser().pause(5);
        String name = app.getUser().generateRandomString_a_z(7);
        System.out.println(name);

        Contact dataContact = Contact.builder()
                .name(name)
                .lastName(app.getUser().generateRandomString_a_z(15))
                .phone(app.getUser().generateRandomStringNumber(11))
                .email(app.getUser().generateRandomStringEmail())
                .addres(app.getUser().generateRandomString_a_z(25))
                .description(app.getUser().generateRandomString_a_z(50))
                .build();

        app.getUser().click(By.xpath("//a[@href='/add']"));

        app.getUser().fillAddForm(dataContact);

        app.getUser().click(By.xpath("//b[text()='Save']"));

        //var 1
        Assert.assertTrue(app.getUser().returnContainsElement(By.xpath("//div[@class='contact-item_card__2SOIM'][last()]")).contains(name));

        app.getUser().click(By.xpath("//div[@class='contact-item_card__2SOIM'][last()]")); //click last element ADD

        //var 2
        List<WebElement> elementsAddForm = app.getUser().elementsPresent(By.xpath("//div[@class='contact-item_card__2SOIM']"));
        for (WebElement e : elementsAddForm) {
            if (e.getText().contains(name)) {
                Assert.assertTrue(true);
                return;
            }
        }


    }
}
