import models.Contact;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EditContactTests extends TestBase{

    @BeforeClass
    public void login(){
        if(app.getUser().isLogged()) {
            app.getUser().click(By.xpath("//a[@href='/contacts']"));
            app.getUser().pause(3000);}
        else
            app.getUser().login("qwerty3171@gmail.com","Qwerty123!_");
    }

    @Test
    public void editContactPositiveTest(){
        app.getUser().pause(3000);
        app.getUser().click(By.xpath("//div[@class='contact-item_card__2SOIM'][last()]")); //click last element ADD
        app.getUser().click(By.xpath("//button[text()='Edit']")); //click EDIT
        app.getUser().pause(3000);
        //System.out.println(app.getUser().returnContainsAtributeElement(By.className("form_form__FOqHs"), "class"));
        String name = app.getUser().generateRandomString_a_z(5);
        String lastName = app.getUser().generateRandomString_a_z(15);
        String phone = app.getUser().generateRandomStringNumber(11);
        String email = app.getUser().generateRandomStringEmail();
        String addres = app.getUser().generateRandomString_a_z(20);
        String desc = app.getUser().generateRandomString_a_z(10);
        String concatStrActual = name+lastName+phone+email+addres+desc;
        System.out.println( concatStrActual);

        Contact dataContact = new Contact()
                .withName(name)
                .withLastName(lastName)
                .withPhone(phone)
                .withEmail(email)
                .withAddres(addres)
                .withDescription(desc);
        app.getUser().fillEditForm(dataContact);
        app.getUser().pause(3000);
        app.getUser().click(By.cssSelector("div[class='form_form__FOqHs']>button"));
        app.getUser().pause(3000);
        System.out.println(app.getUser().returnContainsElement(By.className("contact-item-detailed_card__50dTS")));
        app.getUser().click(By.xpath("//button[text()='Edit']")); //click EDIT
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
