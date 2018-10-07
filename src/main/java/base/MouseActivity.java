package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;



/**
 * author: habin,
 * created on: 06/10/18 : 9:07 PM
 * To change this template use File | Settings | File and Code Templates.
 */


public class MouseActivity {
    private WebDriver driver;
    private Actions actions;
    protected static final Logger log = LogManager.getLogger(WaitEx.class.getName());
    String dragndrop_js = null;



    public MouseActivity(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
        try (
                BufferedReader br =
                        Files.newBufferedReader(Paths.get("/drag_and_drop_helper.js"))) {
            dragndrop_js = br
                    .lines()
                    .collect(Collectors.joining(" "));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void clickAction(WebElement element) {
        actions.moveToElement(element).click().perform();
    }

    public void dragAndDropBy(WebElement sourceElement, int X_axis, int Y_axis) {
        try {
            if (sourceElement.isDisplayed()) {
                actions.dragAndDropBy(sourceElement, X_axis, Y_axis).build().perform();
//                actions.moveToElement(sourceElement).clickAndHold().moveByOffset(-1, -1)
//                        .moveToElement(destinationElement, X_axis, Y_axis).release().build().perform();

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

    public void dragDrop(WebDriver driver, String src, String dst) {
        String js = String.format("$('%s').simulateDragDrop({ dropTarget: '%s'});", src, dst);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(dragndrop_js + js);
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
                        System.out.println("Destination was not displayed to drag");
                    }
                } else {
                    System.out.println("Source was not displayed to drag");
                }
            } catch (StaleElementReferenceException e) {
                System.out.println("Element with " + sourceElement + "or" + destinationElement + "is not attached to the page document "
                        + e.getStackTrace());
                count++;
            } catch (NoSuchElementException e) {
                System.out.println("Element " + sourceElement + "or" + destinationElement + " was not found in DOM " + e.getStackTrace());
                count++;
            } catch (Exception e) {
                System.out.println("Error occurred while performing drag and drop operation " + e.getStackTrace());
                count++;
            }
            count = count + 4;
        }
    }

}
