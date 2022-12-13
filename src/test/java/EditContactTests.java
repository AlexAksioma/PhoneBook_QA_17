import models.Contact;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EditContactTests extends TestBase{

    @BeforeClass
    public void login(){
        if(app.getUser().isLogged()) {
            app.getContact().clickContactsButton();
            app.getUser().pause(3000);}
        else
            app.getUser().loginWithCorrectData();
    }

    @Test
    public void editContactPositiveTest(){
        app.getUser().pause(3000);
        app.getContact().clickLastElementContacts();//click last element ADD

        app.getContact().clickEditButton(); //click EDIT

        app.getUser().pause(3000);

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
