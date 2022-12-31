package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public boolean isElementPresent(By locator) {
        return wd.findElements(locator).size() > 0;
    }

    public void type(By locator, String text) {
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateRandomString(int lengthString, int leftLimit, int rightLimit) {

        // letter 'a' 97
         // letter 'z' 122
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(lengthString);
        for (int i = 0; i < lengthString; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
    public String generateRandomString_a_z(int lengthString) {
        // 97 letter 'a'
        // 122 letter 'z'
        return generateRandomString(lengthString, 97, 122);

    }

    public String generateRandomStringNumber(int lengthString) {
        // 48 letter 0
        // 57 letter 9
        return generateRandomString(lengthString, 48, 57);
    }

    public String generateRandomStringEmail() {
        String str1 = generateRandomString_a_z(5);
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        String str2 = "@mail.com";
        return str1+i+str2;
    }

    public List<WebElement> elementsPresent(By locator){
        List<WebElement> elements = wd.findElements(locator);
        return elements;
    }

    public String returnContainsElement(By locator){
        WebElement element = wd.findElement(locator);
        return element.getText();
    }public String returnContainsAtributeElement(By locator, String atribute){
        WebElement element = wd.findElement(locator);
        return element.getAttribute(atribute);
    }

    public void takeScreenShot(String link){
        File file = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
        File screenShot = new File(link);

        try {
            Files.copy(file, screenShot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickButtonHome() {
        click(By.cssSelector("a[href='/home']"));
    }
}
