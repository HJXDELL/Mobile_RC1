package android.contact;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

/**
 * Created by jiangxun.huang on 2016/6/1.
 */
public class call {

    private AndroidDriver driver2;

    @Parameters({"Device_id","Port"})
    @BeforeTest
    public void setUp(String Device_id,String Port)  throws Exception {
        //drive 1
        startAppiumServer();
        // setting apk path
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "apps");
        File app = new File(appDir, "ContactManager.apk");

        //setting auto par
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Devices");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("unicodeKeyboard","true");
        capabilities.setCapability("udid",Device_id);
        capabilities.setCapability("resetKeyboard","true");
        capabilities.setCapability("appPackage", "com.example.android.contactmanager");
        capabilities.setCapability("appActivity", ".ContactManager");

        //inital  打开应用，开启一二个session
        driver2 = new AndroidDriver(new URL("http://127.0.0.1:"+Port+"/wd/hub"), capabilities);
    }

    @Test
    public void test_callToB(){
        WebElement el = driver2.findElement(By.name("Add Contact"));
        el.click();
        List<WebElement> textFieldsList = driver2.findElementsByClassName("android.widget.EditText");

        textFieldsList.get(0).click();
        WebElement name = driver2.findElement(By.id("com.example.android.contactmanager:id/accountSpinner"));

        textFieldsList.get(0).sendKeys("Some Name");
        textFieldsList.get(2).sendKeys("Some@example.com");
        driver2.swipe(100, 500, 100, 100, 2);
        driver2.findElementByName("Save").click();
    }

    @AfterTest
    public  void tearDown(){
        driver2.quit();
    }
    public void  startAppiumServer(){
        String cmd="cmd.exe /k start appium -p 4730 -bp 4731 -U 0302e96908ea8022";
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            System.out.println("[Appium Server start *********]");
//            while ((line = reader.readLine()) != null) {
//                if(line.contains(" Console LogLevel: debug")){
//                   System.out.println("[Appium Server start success]");
//                }
//            }
            // 关闭输出流
            process.getOutputStream().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
