import models.Contact;
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
            app.getContact().clickContactsButton();
            app.getUser().pause(3000);}
        else
            app.getUser().loginWithCorrectData();
    }

    @Test
    public void deleteLastContactPositiveTest(){
        app.getContact().clickAddButton();
        app.getContact().pause(2000);

        String name = app.getUser().generateRandomString_a_z(5);
        String lastName = app.getUser().generateRandomString_a_z(15);
        String phone = app.getUser().generateRandomStringNumber(11);
        String email = app.getUser().generateRandomStringEmail();
        String addres = app.getUser().generateRandomString_a_z(20);
        String desc = app.getUser().generateRandomString_a_z(10);
        String concatStrActual = name+lastName+phone+email+addres+desc;
        System.out.println( concatStrActual);

        Contact dataContact = Contact.builder()
                .name(name)
                .lastName(lastName)
                .phone(phone)
                .email(email)
                .addres(addres)
                .description(desc)
                .build();

        app.getContact().fillAddContactForm(dataContact);

        app.getContact().clickSaveButton();

        List<WebElement> elementsAddForm = app.getUser().elementsPresent(By.xpath("//div[@class='contact-item_card__2SOIM']"));
        int quantityElements = elementsAddForm.size();

        app.getContact().clickLastElementContacts(); //click last element contacts

        app.getContact().clickRemoveButton();//delete last element

        app.getUser().pause(3000);

        Assert.assertEquals(quantityElements-1, app.getUser().elementsPresent(By.xpath("//div[@class='contact-item_card__2SOIM']")).size());

    }
}
