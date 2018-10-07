package pages;

import base.BaseClass;
import base.MouseActivity;
import base.WaitEx;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author: habin,
 * created on: 06/10/18 : 9:55 PM
 * To change this template use File | Settings | File and Code Templates.
 */


public class PlumFuseCanvas extends BaseClass {
    final String phoneme = "1234567890";
    final String email = "abc@gmail.com";

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

    @FindBy(xpath = "//li[contains(.,'Send an SMS')]")
    private WebElement smsSendButton;

    @FindBy(xpath = "//div[@id='tabs-2']")
    private WebElement canvasArea;

    @FindBy(xpath = "//div[@id='module-1']//div[starts-with(@id,'node-')]")
    private WebElement startNode;

    @FindBy(xpath = "//div[@id='module-2']//div[starts-with(@id,'rec-')]")
    private WebElement smsSendRec;

    @FindBy(xpath = "//textarea[contains(@name,'phone_constant')]")
    private WebElement smsPhoneNumberArea;

    @FindBy(xpath = "//textarea[contains(@name,'message_phrase[]')]")
    private List<WebElement> smsMessageText;

    @FindBy(xpath = "//div[@id='module-2']//div[starts-with(@id,'node-')]")
    private List<WebElement> smsNodes;

    @FindBy(xpath = "//li[contains(.,'Send an Email')]")
    private WebElement emailSendButton;

    @FindBy(xpath = "//div[@id='module-3']")
    private WebElement emailDropped;

    @FindBy(xpath = "//input[contains(@name,'smtp_url')]")
    private WebElement emailSMTPText;

    @FindBy(xpath = "//input[contains(@name,'port')]")
    private WebElement emailPortText;

    @FindBy(xpath = "//div[contains(@class,'left')]//input[contains(@name,'username')]")
    private WebElement emailUsername;

    @FindBy(xpath = "//div[contains(@class,'left')]//input[contains(@name,'password')]")
    private WebElement emailPassword;

    @FindBy(xpath = "//textarea[contains(@name,'from_constant')]")
    private WebElement emailFromText;

    @FindBy(xpath = "//textarea[contains(@name,'to_constant')]")
    private WebElement emailToText;

    @FindBy(xpath = "//textarea[contains(@name,'subject_constant')]")
    private WebElement emailSubjectText;

    @FindBy(xpath = "//div[@id='module-3']//textarea[@name='message_phrase[]']")
    private List<WebElement> emailMessageArea;

    @FindBy(xpath = "//div[@id='module-3']//div[starts-with(@id,'rec-')]")
    private WebElement emailSendRec;

    @FindBy(xpath = "//a[contains(text(),'Basic')]")
    private WebElement basicButtonLeftPanel;

    @FindBy(xpath = "//li[contains(.,'Hang Up or Exit')]")
    private WebElement hangupButton;


    public PlumFuseCanvas() {
        setUp();
        PageFactory.initElements(super.driver, this);

    }

    private void clickOn(WebElement element) {
        mouseActivity.clickAction(element);
    }

    private void dragNdrop(WebElement source, WebElement target) {
        mouseActivity.dragAndDrop(source, target);
    }

    private void dragNdropBy(WebElement source, int Xaxis, int Yaxis) {
        mouseActivity.dragAndDropBy(source, Xaxis, Yaxis);
    }

    public void createAnApp() {
        mouseActivity = new MouseActivity(driver);
        waitEx = new WaitEx(driver);
        mouseActivity.clickAction(createAppButton);
        WebElement element = waitEx.waitForElementToBeClickable(
                By.xpath("//button[contains(text(),\"Let's get started!\")]"), 10000);
        mouseActivity.clickAction(element);
        mouseActivity.clickAction(newPagebutton);
        enterValue(inputPageName, "Test");
        mouseActivity.clickAction(createPageButton);
        mouseActivity.clickAction(messagingButtonLeftPanel);
        waiter();
        mouseActivity.dragAndDrop(smsSendButton, canvasArea);
        waiter();
        mouseActivity.dragAndDrop(startNode, smsSendRec);
        enterValue(smsPhoneNumberArea, phoneme);
        enterValue(smsMessageText.get(0), "Hello World");
        mouseActivity.dragAndDropBy(emailSendButton, 1000, 400);
        waiter();
        mouseActivity.dragAndDrop(smsNodes.get(1), emailSendRec);
        enterValue(emailSMTPText, "smtp.gmail.com");
        enterValue(emailPortText, "465");
        enterValue(emailUsername, email);
        enterValue(emailPassword, "qwerty");
        enterValue(emailFromText, email);
        enterValue(emailToText, "xyz@gmail.com");
        enterValue(emailSubjectText, "SMS not sent");
        enterValue(emailMessageArea.get(0), "SMS to phone no " + phoneme + " not sent");
        clickOn(basicButtonLeftPanel);
        dragNdropBy(hangupButton, 600, 300);




    }

    private void reloadPage(String expectedTitle) {
        if (!driver.getTitle().equalsIgnoreCase(expectedTitle)) {
            driver.navigate().refresh();
        }
    }

    private void enterValue(WebElement element, String data) {
        mouseActivity.clickAction(element);
        element.sendKeys(data);
    }
}
