package ios.contact;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by jiangxun.huang on 2016/5/24.
 */
public class ContactIOS {
    private AndroidDriver driver;
    private File file;

    @BeforeSuite
    public void setUp() throws Exception {
        // setting apk path
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "apps");
        File app = new File(appDir, "RCMobile_stab_8.1.0.1.115_Production_InHouse.ipa");

        //setting auto par
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Devices");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("unicodeKeyboard","true");
        capabilities.setCapability("resetKeyboard","true");
        capabilities.setCapability("appPackage", "com.example.android.contactmanager");
        capabilities.setCapability("appActivity", ".ContactManager");
        capabilities.setCapability("udid","0302e96908ea8022");

        //inital  打开应用，开启一二个session
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @AfterSuite
    public void tearDown() throws Exception {
        //关闭seesion
        driver.quit();
    }


    @Test
    public void test_addContact() throws InterruptedException {
        Thread.sleep(5000);

        WebElement el = driver.findElement(By.name("Add Contact"));
        el.click();
        List<WebElement> textFieldsList = driver.findElementsByClassName("android.widget.EditText");

        textFieldsList.get(0).click();
        WebElement name = driver.findElement(By.id("com.example.android.contactmanager:id/accountSpinner"));

        textFieldsList.get(0).sendKeys("Some Name");
        textFieldsList.get(2).sendKeys("Some@example.com");
        driver.swipe(100, 500, 100, 100, 2);
        driver.findElementByName("Save").click();

    }


}
