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
            app.getContact().clickContactsButton();
            app.getUser().pause(3000);
        } else
            app.getUser().loginWithCorrectData();
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

        app.getContact().clickAddButton();

        app.getContact().fillAddContactForm(dataContact);

        app.getContact().clickSaveButton();

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
