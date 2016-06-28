package android.ringcentral;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import java.io.File;
import java.net.URL;
import java.util.List;

public class Contact{
    private AndroidDriver driver;
    private File file;

    @BeforeSuite

    public void setUp() throws Exception {
        // setting apk path
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "apps");
        File app = new File(appDir, "RCMobile_8.2.0.1.1184_Production.apk");

        //setting auto par
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Devices");
        capabilities.setCapability("platformVersion", "6.1");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("unicodeKeyboard","true");
        capabilities.setCapability("resetKeyboard","true");
        capabilities.setCapability("appPackage", "com.example.android.contactmanager");
        capabilities.setCapability("appActivity", ".ContactManager");
        capabilities.setCapability("udid","Device_ID");

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
        driver.findElementById("com.ringcentral.android:id/phone").sendKeys("2054097406");
        driver.findElementById("com.ringcentral.android:id/password")  .sendKeys("Test!123");
        driver.findElementById("com.ringcentral.android:id/btnSignIn").click();


//        WebElement el = driver.findElement(By.name("Add Contact"));
//        el.click();
//        List<WebElement> textFieldsList = driver.findElementsByClassName("android.widget.EditText");
//
//        textFieldsList.get(0).click();
//        WebElement name = driver.findElement(By.id("com.example.android.contactmanager:id/accountSpinner"));
//
//        textFieldsList.get(0).sendKeys("Some Name");
//        textFieldsList.get(2).sendKeys("Some@example.com");
//        driver.swipe(100, 500, 100, 100, 2);
//        driver.findElementByName("Save").click();

    }


    public void someTest(){
        /**

         //Find out the menu entry of "Delete"
         WebElement optionListView = driver.findElement(By.className("android.widget.ListView"));
         List<WebElement> options = optionListView.findElements(By.className("android.widget.TextView"));
         WebElement delete = null;

         for (WebElement option : options) {
         if (option.getText().equals("Delete")) {
         delete = option;
         break;

         }
         }



         //Long press on the men entry to call out the context menu options
         TouchAction action = new TouchAction(driver);
         action.longPress(driver.findElement(By.name("")));
         //action.moveTo(we,240,10);
         action.perform();


         //Delete the menu entry
         delete.click();

         try {
         Thread.sleep(1000);
         } catch (Exception e) {
         System.out.println(e.getMessage());
         }
         //Demo get element
         WebElement e3 = driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Add note\")");
         Assert.assertTrue(el.getText() == ("Add note"));

         //Demo contentDescription
         /**
         * Accessibility ID 在 Android 上面就等同于 contentDescription。这个属性是方便一些生理功能有缺陷的人使用应用程序的。
         * 比如我们有一个 ImageView 里面放置一张颜色复杂的图片，可能一些色弱色盲的人，分不清这张图片中画的是什么东西。如果用户安装了辅助浏览工具比如 TalkBack，TalkBack 就会大声朗读出用户目前正在浏览的内容。
         * TextView 控件 TalkBack 可以直接读出里面的内容，但是 ImageView，TalkBack 就只能去读 contentDescription 的值，告诉用户这个图片到底是什么。
         鉴于这是一个隐藏属性，而 Android 上用于查找控件的各种属性可能有所缺失或者有重复
         （比如id重复，比如一个list下面的所有项可能都是叫做“id/text1”），所以最佳的办法就是跟开发团队沟通好每个 view 都赋予一个唯一的 contentDescription。
         */
        WebElement e4 = driver.findElementByAccessibilityId("menu_add_note_description");

        //API DEMO findEelementByXPATH
        /**
         *    XPath格式变化
         从老版本的 Appium0.18.x 升级到现在的 Appium1.x 后，注意 class name 和 xpath 策略的变化：你现在需要使用 FQCN 来描述你的控件。也就是说原来的：findElementByXpath(""//TextView[contains(@text,'Add note')]"") 需要改成findElementByXpath("//android.widget.TextView[contains(@text,'Add note')]")
         */
        WebElement e5 = driver.findElementByXPath("//android.widget.TextView[contains(@text,'Add note')]");

        //// TODO: 2016/2/23  终极方法：AppiumDriver getPageSource
        /**
         *   最后介绍AppiumDriver的getPageSource函数，可以把当前页面的元素以XML的方式打印出来，建议大家在找不到view的相应属性的时候就调用该函数来慢慢查找了
         */

        driver.getPageSource();

    }
}
