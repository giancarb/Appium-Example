import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class ioSampleTest {

    public AndroidDriver driver;
    public WebDriverWait wait;

    //Elements
    final String secondNewJob = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.LinearLayout[1]/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.LinearLayout";


    @BeforeMethod
    public void setup() throws MalformedURLException {


        UiAutomator2Options options = new UiAutomator2Options()
                .setUdid("emulator-5554")  //DeviceId from "adb devices" command
                .setApp("/Users/gianlucacarbone/Dev/Appium/Appium-Example/appium-sample-test-master/apk/isinolsun-v2.1.7.apk")
                .setAppActivity("com.isinolsun.app.activities.SplashActivity")
                .setAppPackage("com.isinolsun.app")
                .setDeviceName("Pixel 5 API 24")
                .setPlatformVersion("7.0")
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setNoReset(false)
                .setSkipUnlock(true);

        driver = new AndroidDriver(new URL("http://0.0.0.0:4723"), options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    @Test
    public void basicTest() {
        //Click and pass Splash
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.isinolsun.app:id/animation_view"))).click();

        //Click I am searching a job
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.isinolsun.app:id/bluecollar_type_button"))).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("com.android.packageinstaller:id/permission_allow_button")));

        if (!driver.findElements(By.id("com.android.packageinstaller:id/permission_allow_button")).isEmpty()) {
            driver.findElements(By.id("com.android.packageinstaller:id/permission_allow_button")).get(0).click();
        }


        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath(secondNewJob)));
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
