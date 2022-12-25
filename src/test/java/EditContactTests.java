import models.Contact;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EditContactTests extends TestBase{

    @BeforeClass
    public void login(){
        if(!app.getUser().isLogged()) {
            app.getUser().loginWithCorrectData();
        }
    }

    @BeforeMethod
    public void addNewContactBeforeEditTest(){
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
    public void editContactPositiveTest(){
        app.getContact().clickContactsButton();
        app.getContact().clickLastElementContacts();//click last element ADD
        app.getContact().clickEditButton(); //click EDIT
        app.getUser().pause(1000);

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

        app.getContact().fillEditContactForm(dataContact);

        app.getContact().clickSaveButton();
        app.getUser().pause(3000);

        System.out.println(app.getUser().returnContainsElement(By.className("contact-item-detailed_card__50dTS")));
        app.getContact().clickEditButton();//click EDIT

        String concatStrExpected = app.getUser().returnContainsAtributeElement(By.xpath("//div[@class='form_form__FOqHs']/input[@placeholder='Name']"), "value")+
                app.getUser().returnContainsAtributeElement(By.xpath("//div[@class='form_form__FOqHs']/input[@placeholder='Last Name']"), "value")+
                app.getUser().returnContainsAtributeElement(By.xpath("//div[@class='form_form__FOqHs']/input[@placeholder='Phone']"), "value")+
                app.getUser().returnContainsAtributeElement(By.xpath("//div[@class='form_form__FOqHs']/input[@placeholder='email']"), "value")+
                app.getUser().returnContainsAtributeElement(By.xpath("//div[@class='form_form__FOqHs']/input[@placeholder='Address']"), "value")+
                app.getUser().returnContainsAtributeElement(By.xpath("//div[@class='form_form__FOqHs']/input[@placeholder='desc']"), "value");
        System.out.println(concatStrExpected);
        Assert.assertEquals(concatStrActual, concatStrExpected);

    }
}
