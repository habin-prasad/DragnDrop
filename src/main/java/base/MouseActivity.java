package base;

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
    private WebDriver driver;
    private Actions actions;

    public MouseActivity(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
    }

    public void clickAction(WebElement element) {
        actions.moveToElement(element).click().perform();
    }

    public void dragAndDrop(WebElement sourceElement, WebElement destinationElement, int X_axis, int Y_axis) {
        try {
            if (sourceElement.isDisplayed()) {
//                actions.dragAndDropBy(sourceElement, X_axis, Y_axis);
                actions.moveToElement(sourceElement).clickAndHold().moveByOffset(-1, -1)
                        .moveToElement(destinationElement, X_axis, Y_axis).release().build().perform();

            } else {
                System.out.println("Source Element was not displayed to drag");
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("Element with " + sourceElement + "is not attached to the page document " + e.getStackTrace());
        } catch (NoSuchElementException e) {
            System.out.println("Element " + sourceElement + " was not found in DOM " + e.getStackTrace());
        } catch (Exception e) {
            System.out.println("Error occurred while performing drag and drop operation " + e.getStackTrace());
        }
    }

}
