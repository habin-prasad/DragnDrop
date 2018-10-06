package pages;

import base.BaseClass;
import base.MouseActivity;
import base.WaitEx;
import org.openqa.selenium.By;
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

    @FindBy(xpath = "//button[contains(text(),\"Let's get started!\")]")
    private WebElement getStartedButton;

    @FindBy(id = "add-page")
    private WebElement newPagebutton;

    @FindBy(xpath = "//form[@class='unsubmittable']//input[@name='name']")
    private WebElement inputPageName;

    @FindBy(xpath = "//form[contains(@class,'unsubmittable')]//input[contains(@name,'name')]" +
            "/parent::*/parent::*/parent::*/parent::*//button[contains(text(),'Create')]")
    private WebElement createPageButton;

    @FindBy(xpath = "//div[@id='module-1']//img")
    private WebElement loaderIcon;

    @FindBy(xpath = "//a[contains(text(),'Messaging')]")
    private WebElement messagingButtonLeftPanel;

    @FindBy(xpath = "//span[contains(.,'SMS')]/parent::li")
    private WebElement sendSmsButton;

    @FindBy(id = "tabs-1")
    private WebElement canvasArea;


    public PlumFuseCanvas() {
        setUp();
        PageFactory.initElements(super.driver, this);

    }

    private void clickButton(WebElement element) {
        element.click();
    }

    public void createAnApp() {
        mouseActivity = new MouseActivity(driver);
        waitEx = new WaitEx(driver);
        mouseActivity.clickAction(createAppButton);
        WebElement element = waitEx.waitForElementToBeClickable(
                By.xpath("//button[contains(text(),\"Let's get started!\")]"), 10000);
        mouseActivity.clickAction(element);
        mouseActivity.clickAction(newPagebutton);
        mouseActivity.clickAction(inputPageName);
        inputPageName.sendKeys("Test");
        mouseActivity.clickAction(createPageButton);
        mouseActivity.clickAction(messagingButtonLeftPanel);
        mouseActivity.dragAndDrop(sendSmsButton, canvasArea, -100, 50);






    }

    private void reloadPage(String expectedTitle) {
        if (!driver.getTitle().equalsIgnoreCase(expectedTitle)) {
            driver.navigate().refresh();
        }
    }
}
