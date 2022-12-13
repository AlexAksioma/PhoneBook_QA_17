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
    public void loginWithCorrectData() {
        openLoginRegistrationForm();
        filLoginRegistrationForm("qwerty3171@gmail.com","Qwerty123!_");
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
