package testPages;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.PlumFuseCanvas;

/**
 * @author: habin,
 * created on: 06/10/18 : 9:37 PM
 * To change this template use File | Settings | File and Code Templates.
 */


public class PlumFuseCanvasTest {
    private PlumFuseCanvas plumFuseCanvas;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        plumFuseCanvas = new PlumFuseCanvas();

    }

    @Test(groups = "create")
    public void createAnApp() {
        plumFuseCanvas.createAnApp();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        plumFuseCanvas.tearDown();
    }
}
