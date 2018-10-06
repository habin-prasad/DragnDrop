package pages;

import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author: habin,
 * created on: 06/10/18 : 9:55 PM
 * To change this template use File | Settings | File and Code Templates.
 */


public class PlumFuseCanvas extends BaseClass {

    @FindBy(id = "link-create")
    private WebElement createAppButton;

    public PlumFuseCanvas() {
        setUp();
        PageFactory.initElements(super.driver, this);
    }

    private void clickButton(WebElement element) {
        element.click();
    }

    public void createAnApp() {
        clickButton(createAppButton);
    }
}
