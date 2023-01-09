import manager.DataProviderForContact;
import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class AddNewContactTest extends TestBase {//+negative tests!!!!!!!!
    @BeforeClass(alwaysRun = true)
    public void login() {
        if (app.getUser().isLogged()) {
            app.getContact().clickContactsButton();
            //app.getUser().pause(3000);
        } else
            app.getUser().loginWithCorrectData();
    }

    @Test(dataProvider = "myDPMethod", dataProviderClass = DataProviderForContact.class,
            groups = {"positiveGroup"})
    public void addNewContactPositiveTest(Contact contact) {
        String name = contact.getName();
        app.getContact().clickAddButton();
        app.getContact().fillAddContactForm(contact);
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

    @Test(dataProvider = "myDPFile", dataProviderClass = DataProviderForContact.class,
            groups = {"positiveGroup"})
    public void addNewContactPositiveTest_DP_file(Contact contact) {
        String name = contact.getName();
        app.getContact().clickAddButton();
        app.getContact().fillAddContactForm(contact);
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
    @AfterClass(alwaysRun = true)
    public void logout(){
        if(app.getUser().isLogged())
            app.getUser().logout();
        app.getUser().clickButtonHome();
    }
}
