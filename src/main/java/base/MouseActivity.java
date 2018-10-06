package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * author: habin,
 * created on: 06/10/18 : 9:07 PM
 * To change this template use File | Settings | File and Code Templates.
 */


public class MouseActivity {
    private WebDriver driver;
    private Actions actions;

    public MouseActivity(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
    }

}
