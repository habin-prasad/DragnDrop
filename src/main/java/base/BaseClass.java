package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

/**
 * author: habin,
 * created on: 06/10/18 : 9:07 PM
 * To change this template use File | Settings | File and Code Templates.
 */


public abstract class BaseClass {
    private static final int WAIT_TIME_IN_SECS = 5;
    private static final int WAIT_TIME_IN_MILLISECS = 5000;
    private static final String baseUrl = "http://quickfuseapps.com/";
    protected static final Logger log = LogManager.getLogger(WaitEx.class.getName());

    protected WebDriver driver;
    protected MouseActivity mouseActivity;
    protected WaitEx waitEx;

    public void setUp() {
        this.driver = selectBrowser();
        this.driver = maximizeWindow();
        this.driver = implicitDriverWait(WAIT_TIME_IN_SECS);
        driver.get(baseUrl);
    }

    private WebDriver selectBrowser() {
        String webDriver = "chrome";
        if (webDriver.equalsIgnoreCase("chrome")) {
//            String directoryName = System.getProperty("user.dir") + "/drivers/";
//            System.setProperty("webdriver.chrome.driver", directoryName + "chromedriver");
            WebDriverManager.chromedriver().setup();

            driver = new ChromeDriver();
        } else if (webDriver.equalsIgnoreCase("safari"))
            driver = new SafariDriver();
        else
            driver = new FirefoxDriver();

        return driver;
    }

    private WebDriver maximizeWindow() {
        this.driver.manage().window().fullscreen();
        return this.driver;
    }

    private WebDriver implicitDriverWait(int timeInSeconds) {
        this.driver.manage().timeouts().implicitlyWait(timeInSeconds, TimeUnit.SECONDS);
        return this.driver;
    }


    public void tearDown() {
        driver.quit();
    }

    public void waiter() {
        try {
            Thread.sleep(WAIT_TIME_IN_MILLISECS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
