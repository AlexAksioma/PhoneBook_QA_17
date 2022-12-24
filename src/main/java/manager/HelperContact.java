package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HelperContact extends HelperBase{
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    Logger logger = LoggerFactory.getLogger(HelperContact.class);

    public void clickAddButton() {
        click(By.xpath("//a[@href='/add']"));
    }
    public void clickSaveButton() {
        //click(By.xpath("//b[text()='Save']"));
        click(By.xpath("//*[text()='Save']"));
    }
    public void clickLastElementContacts() {
        click(By.xpath("//div[@class='contact-item_card__2SOIM'][last()]"));
        logger.info("Last element with number phone = "+
                wd.findElement(By.xpath("//div[@class='contact-item_card__2SOIM'][last()]/h3")).getText());

    }

    public void clickRemoveButton() {
        click(By.xpath("//button[text()='Remove']"));
    }

    public void clickEditButton() {
        click(By.xpath("//button[text()='Edit']"));
    }

    public void clickContactsButton() {
        click(By.xpath("//a[@href='/contacts']"));
    }
    public void fillAddContactForm(Contact dataContact) {
        type(By.xpath("//input[@placeholder='Name']"), dataContact.getName());
        type(By.xpath("//input[@placeholder='Last Name']"), dataContact.getLastName());
        type(By.xpath("//input[@placeholder='Phone']"), dataContact.getPhone());
        type(By.xpath("//input[@placeholder='email']"), dataContact.getEmail());
        type(By.xpath("//input[@placeholder='Address']"), dataContact.getAddres());
        type(By.xpath("//input[@placeholder='description']"), dataContact.getDescription());
    }


    public void fillEditContactForm(Contact dataContact) {
        type(By.xpath("//div[@class='form_form__FOqHs']/input[@placeholder='Name']"), dataContact.getName());
        type(By.xpath("//div[@class='form_form__FOqHs']/input[@placeholder='Last Name']"), dataContact.getLastName());
        type(By.xpath("//div[@class='form_form__FOqHs']/input[@placeholder='Phone']"), dataContact.getPhone());
        type(By.xpath("//div[@class='form_form__FOqHs']/input[@placeholder='email']"), dataContact.getEmail());
        type(By.xpath("//div[@class='form_form__FOqHs']/input[@placeholder='Address']"), dataContact.getAddres());
        type(By.xpath("//div[@class='form_form__FOqHs']/input[@placeholder='desc']"), dataContact.getDescription());
    }


    public int quantityContacts() {
        //WebDriverWait wait = new WebDriverWait(wd, 5);
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='contact-item_card__2SOIM']")));
        List<WebElement> elementsAddForm = wd.findElements(By.xpath("//div[@class='contact-item_card__2SOIM']"));
        return elementsAddForm.size();
    }

    public boolean contactsListIsEmpty(){
        return  isElementPresent(By.xpath(
                "//div[@class='contact-page_message__2qafk']/h1[text()=' No Contacts here!']"));

    }
}
