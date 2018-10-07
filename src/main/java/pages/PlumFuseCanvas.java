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
    private final String phoneNo = "1234567890";
    private final String email = "abc@gmail.com";

    @FindBy(id = "link-create")
    private WebElement createAppButton;

    @FindBy(xpath = "//button[contains(text(),\"Let's get started!\")]")
    private WebElement getStartedButton;

    @FindBy(id = "add-page")
    private WebElement newPagebutton;

    @FindBy(xpath = "//form[@class='unsubmittable']//input[@name='name']")
    private WebElement inputPageName;

    @FindBy(xpath = "//button[contains(.,'Create')]")
    private List<WebElement> createPageButtons;

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

    @FindBy(xpath = "//div[@id='module-4']//div[starts-with(@id,'rec-')]")
    private WebElement hangup1Rec;

    @FindBy(xpath = "//div[@id='module-3']//div[starts-with(@id,'node-')]")
    private List<WebElement> emailNodes;

    @FindBy(xpath = "//div[@id='module-5']//div[starts-with(@id,'rec-')]")
    private WebElement hangup2Rec;

    @FindBy(xpath = "//div[@id='module-6']//div[starts-with(@id,'rec-')]")
    private WebElement hangup3Rec;



    public PlumFuseCanvas() {
        setUp();
        PageFactory.initElements(super.driver, this);

    }

    private void clickOn(WebElement element) {
        mouseActivity.clickAction(element);
    }

    private void dragNdrop(WebElement source, WebElement target) {
        log.info("Waiting for elements to available with DOM");
        waiter();
        mouseActivity.dragAndDrop(source, target);
        log.info("Drag and Drop operation success");
    }

    private void dragNdropBy(WebElement source, int Xaxis, int Yaxis) {
        log.info("Waiting for elements to available with DOM");
        waiter();
        mouseActivity.dragAndDropBy(source, Xaxis, Yaxis);
        log.info("Drag and Drop By operation success");
    }

    private void navigateToCanvas() {
        clickOn(createAppButton);
        log.info("Successfully navigated to Canvas Page");
    }

    private void addCanvasSheet() {
        WebElement element = waitEx.waitForElementToBeClickable(
                By.xpath("//button[contains(text(),\"Let's get started!\")]"), 10000);
        clickOn(element);
        clickOn(newPagebutton);
        enterValue(inputPageName, "Test");
        clickOn(createPageButtons.get(1));
        log.info("Successfully added new canvas sheet");
    }

    private void dragNdropSMSBlock() {
        clickOn(messagingButtonLeftPanel);
        dragNdropBy(smsSendButton, 650, 30);
        dragNdrop(startNode, smsSendRec);
        enterValue(smsPhoneNumberArea, phoneNo);
        enterValue(smsMessageText.get(0), "Hello World");
        log.info("Successfully dropped SMS block to canvas");
    }

    private void dragNdropEmailBlock() {
        dragNdropBy(emailSendButton, 900, 200);
        dragNdrop(smsNodes.get(1), emailSendRec);
        enterValue(emailSMTPText, "smtp.gmail.com");
        enterValue(emailPortText, "465");
        enterValue(emailUsername, email);
        enterValue(emailPassword, "qwerty");
        enterValue(emailFromText, email);
        enterValue(emailToText, "xyz@gmail.com");
        enterValue(emailSubjectText, "SMS not sent");
        enterValue(emailMessageArea.get(0), "SMS to phone no " + phoneNo + " not sent");
        log.info("Successfully dropped Email block to canvas");
    }

    private void dragNdropHangUpBlocks() {
        clickOn(basicButtonLeftPanel);
        dragNdropBy(hangupButton, 400, 200);
        dragNdrop(smsNodes.get(0), hangup1Rec);
        dragNdropBy(hangupButton, 700, 470);
        dragNdrop(emailNodes.get(0), hangup2Rec);
        dragNdropBy(hangupButton, 1250, 470);
        dragNdrop(emailNodes.get(1), hangup3Rec);
        log.info("Successfully dropped Hang Up block to canvas");
    }

    public void createAnApp() {
        mouseActivity = new MouseActivity(driver);
        waitEx = new WaitEx(driver);
        navigateToCanvas();
        addCanvasSheet();
        dragNdropSMSBlock();
        dragNdropEmailBlock();
        dragNdropHangUpBlocks();
        log.info("Successfully completed the execution");
    }

    private void reloadPage(String expectedTitle) {
        if (!driver.getTitle().equalsIgnoreCase(expectedTitle)) {
            driver.navigate().refresh();
        }
    }

    private void enterValue(WebElement element, String data) {
        clickOn(element);
        element.sendKeys(data);
    }
}
