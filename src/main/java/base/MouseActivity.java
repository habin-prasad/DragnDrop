package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


/**
 * author: habin,
 * created on: 06/10/18 : 9:07 PM
 * To change this template use File | Settings | File and Code Templates.
 */


public class MouseActivity {
    private static final Logger log = LogManager.getLogger(WaitEx.class.getName());
    private WebDriver driver;
    private Actions actions;


    public MouseActivity(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
    }

    public void clickAction(WebElement element) {
        if (element.isDisplayed())
            actions.moveToElement(element).click().perform();
        else
            log.error("Source Element was not displayed to click");
    }

    public void dragAndDropBy(WebElement sourceElement, int X_axis, int Y_axis) {
        try {
            if (sourceElement.isDisplayed()) {
                actions.dragAndDropBy(sourceElement, X_axis, Y_axis).build().perform();
            } else {
                log.error("Source Element was not displayed to drag");
            }
        } catch (StaleElementReferenceException e) {
            log.error("Element with " + sourceElement + "is not attached to the page document " + e.getMessage());
        } catch (NoSuchElementException e) {
            log.error("Element " + sourceElement + " was not found in DOM " + e.getMessage());
        } catch (Exception e) {
            log.error("Error occurred while performing drag and drop operation " + e.getMessage());
        }
    }

    public void dragAndDrop(WebElement sourceElement, WebElement destinationElement) {
        int count = 0;
        while (count < 4) {
            try {
                if (sourceElement.isDisplayed()) {
                    if (destinationElement.isDisplayed()) {
                        Actions action = new Actions(driver);
                        action.dragAndDrop(sourceElement, destinationElement).build().perform();
                    } else {
                        log.error("Destination was not displayed to drag");
                    }
                } else {
                    log.error("Source was not displayed to drag");
                }
            } catch (StaleElementReferenceException e) {
                log.error("Element with " + sourceElement + "or" + destinationElement + "is not attached to the page document "
                        + e.getMessage());
                count++;
            } catch (NoSuchElementException e) {
                log.error("Element " + sourceElement + "or" + destinationElement + " was not found in DOM " + e.getMessage());
                count++;
            } catch (Exception e) {
                log.error("Error occurred while performing drag and drop operation " + e.getMessage());
                count++;
            }
            count += 4;
        }
    }

}
