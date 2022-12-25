import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class DeleteContactTest extends TestBase{

    @BeforeClass
    public void login(){
        if(!app.getUser().isLogged()){
            app.getUser().loginWithCorrectData();
        }
    }

    @BeforeMethod
    public void addNewContactBeforeDeleteTest(){
        for(int i=0;i<5;i++){
            app.getContact().clickAddButton();
            Contact dataContact = Contact.builder()
                .name(app.getUser().generateRandomString_a_z(5))
                .lastName(app.getUser().generateRandomString_a_z(15))
                .phone(app.getUser().generateRandomStringNumber(11))
                .email(app.getUser().generateRandomStringEmail())
                .addres(app.getUser().generateRandomString_a_z(20))
                .description(app.getUser().generateRandomString_a_z(10))
                .build();
            app.getContact().fillAddContactForm(dataContact);
            app.getContact().clickSaveButton();//add contact in list of elements
            app.getContact().pause(1000);
        }
    }

    @Test
    public void deleteLastContactPositiveTest(){
        if(!app.getContact().contactsListIsEmpty()){
            int quantityElements = app.getContact().quantityContacts();
            logger.info("quantity contacts = "+quantityElements);
            app.getContact().clickLastElementContacts(); //click last element contacts
            app.getContact().clickRemoveButton();//delete last element
            app.getUser().pause(1000);

        Assert.assertEquals(quantityElements-1, app.getContact().quantityContacts());
        }

    }

    @Test
    public void deleteAllContactsPositiveTest(){
        while (!app.getContact().contactsListIsEmpty()) {
            app.getContact().clickLastElementContacts(); //click last element contacts
            app.getContact().clickRemoveButton();//delete last element
            app.getUser().pause(1000);
        }
        Assert.assertTrue(app.getContact().contactsListIsEmpty());
    }
}
