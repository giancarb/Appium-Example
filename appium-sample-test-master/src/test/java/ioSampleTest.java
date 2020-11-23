import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class ioSampleTest {

    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;

    //Elements
    String secondNewJob = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.LinearLayout[1]/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.LinearLayout";






    @BeforeMethod
    public void setup () throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Pixel 2 API 24");
        caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "7.0");
        caps.setCapability("skipUnlock","true");
        caps.setCapability("appPackage", "com.isinolsun.app");
        caps.setCapability("appActivity","com.isinolsun.app.activities.SplashActivity");
        caps.setCapability("noReset","false");

        //caps.setCapability(MobileCapabilityType.APP, "/Users/gianlucacarbone/Documents/dev/IdeaProjects/appium-sample-test-master/apk/isinolsun-v2.1.7.apk" );
        driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),caps);
        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void basicTest () throws InterruptedException {
        //Click and pass Splash
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.isinolsun.app:id/animation_view"))).click();

        //Click I am searching a job
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.isinolsun.app:id/bluecollar_type_button"))).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.android.packageinstaller:id/permission_allow_button")));

        if (driver.findElements(By.id("com.android.packageinstaller:id/permission_allow_button")).size() > 0) {
            driver.findElements(By.id("com.android.packageinstaller:id/permission_allow_button")).get(0).click();
        }


        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath(secondNewJob)));
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
