package manager;

import models.Contact;
import models.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperUser extends HelperBase{

    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void submitLogin() {
        click(By.xpath("//button[1]"));
    }

    public void submitRegistration() {
        click(By.xpath("//button[@name='registration']"));
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//button[text()='Sign Out']"));
    }

    public void logout() {
        click(By.xpath("//button[text()='Sign Out']"));
    }

    public void login(String email, String password){
        openLoginRegistrationForm();
        filLoginRegistrationForm(email, password);
        submitLogin();
        pause(2000);
    }

    public void login(User data){
        openLoginRegistrationForm();
        filLoginRegistrationForm(data);
        submitLogin();
        pause(2000);
    }

    public void openLoginRegistrationForm() {
        click(By.xpath("//a[text()='LOGIN']"));
    }

    public void filLoginRegistrationForm(String email, String password) {
        type(By.xpath("//input[1]"), email);
        type(By.xpath("//input[2]"), password);
    }
    public void filLoginRegistrationForm(User data) {
        type(By.xpath("//input[1]"), data.getEmail());
        type(By.xpath("//input[2]"), data.getPassword());
    }

    public void fillAddForm(String name, String lastName, String phone, String email, String addres, String description){
        type(By.xpath("//input[@placeholder='Name']"), name);
        type(By.xpath("//input[@placeholder='Last Name']"), lastName);
        type(By.xpath("//input[@placeholder='Phone']"), phone);
        type(By.xpath("//input[@placeholder='email']"), email);
        type(By.xpath("//input[@placeholder='Address']"), addres);
        type(By.xpath("//input[@placeholder='description']"), description);
    }

    public void fillAddForm(Contact dataContact){
        type(By.xpath("//input[@placeholder='Name']"), dataContact.getName());
        type(By.xpath("//input[@placeholder='Last Name']"), dataContact.getLastName());
        type(By.xpath("//input[@placeholder='Phone']"), dataContact.getPhone());
        type(By.xpath("//input[@placeholder='email']"), dataContact.getEmail());
        type(By.xpath("//input[@placeholder='Address']"), dataContact.getAddres());
        type(By.xpath("//input[@placeholder='description']"), dataContact.getDescription());
    }

    public void fillEditForm(String name, String lastName, String phone, String email, String addres, String description){
        type(By.xpath("//div[@class='form_form__FOqHs']/input[@placeholder='Name']"), name);
        type(By.xpath("//div[@class='form_form__FOqHs']/input[@placeholder='Last Name']"), lastName);
        type(By.xpath("//div[@class='form_form__FOqHs']/input[@placeholder='Phone']"), phone);
        type(By.xpath("//div[@class='form_form__FOqHs']/input[@placeholder='email']"), email);
        type(By.xpath("//div[@class='form_form__FOqHs']/input[@placeholder='Address']"), addres);
        type(By.xpath("//div[@class='form_form__FOqHs']/input[@placeholder='desc']"), description);
    }

    public void fillEditForm(Contact dataContact){
        type(By.xpath("//div[@class='form_form__FOqHs']/input[@placeholder='Name']"), dataContact.getName());
        type(By.xpath("//div[@class='form_form__FOqHs']/input[@placeholder='Last Name']"), dataContact.getLastName());
        type(By.xpath("//div[@class='form_form__FOqHs']/input[@placeholder='Phone']"), dataContact.getPhone());
        type(By.xpath("//div[@class='form_form__FOqHs']/input[@placeholder='email']"), dataContact.getEmail());
        type(By.xpath("//div[@class='form_form__FOqHs']/input[@placeholder='Address']"), dataContact.getAddres());
        type(By.xpath("//div[@class='form_form__FOqHs']/input[@placeholder='desc']"), dataContact.getDescription());
    }


    public boolean isAlertPresent() {
        Alert alert = new WebDriverWait(wd, 10).until(ExpectedConditions.alertIsPresent());
        if(alert==null)
            return false;
        else {
            wd.switchTo().alert();
            System.out.println(alert.getText());
            alert.accept();//for Ok button
            //alert.dismiss() for button Cancel
            //alert.sendKeys() for input data
            return  true;
        }
    }


    public boolean isErrorMessageInFormat(){
        Alert alert = new WebDriverWait(wd, 10).until(ExpectedConditions.alertIsPresent());
        String message = "Wrong email or password";
        if(alert==null)
            return false;
        else {
            wd.switchTo().alert();
            return  alert.getText().contains(message);
        }
    }
}
