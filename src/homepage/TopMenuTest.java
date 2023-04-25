package homepage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.Utility;

import java.util.List;

public class TopMenuTest extends Utility {

    @Before
    public void setUp() {
        String baseUrl = "http://tutorialsninja.com/demo/index.php?";
        openBrowser(baseUrl);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }

    public void selectMenu(String menu) {
        List<WebElement> listOfMenuElements = driver.findElements(By.xpath("//ul[@class='nav navbar-nav']"));
        for (WebElement element : listOfMenuElements) {
            System.out.println(element.getText());

            if (element.getText().trim().equalsIgnoreCase(menu)) {
                element.click();
                break;
            }
        }
    }

    /**
     * verifyUserShouldNavigateToDesktopsPageSuccessfully()
     * 1.1 Mouse hover on “Desktops” Tab and click
     * 1.2 call selectMenu method and pass the menu = “Show All Desktops”
     * 1.3 Verify the text ‘Desktops’
     */
    @Test
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        By desktops = By.xpath("//a[@class='dropdown-toggle'][normalize-space()='Desktops']");
        hoverMouseToElement(desktops);
        selectMenu("Show All Desktops");
        clickOnElement(desktops);
        assertToVerify(desktops, "Desktops");
    }

    /**
     * verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully()
     * 2.1 Mouse hover on “Laptops & Notebooks” Tab and click
     * 2.2 call selectMenu method and pass the menu = “Show All Laptops & Notebooks”
     * 2.3 Verify the text ‘Laptops & Notebooks’
     */
    @Test
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() {
        By LaptopsNotebooks = By.xpath("//a[normalize-space()='Laptops & Notebooks']");
        hoverMouseToElement(LaptopsNotebooks);
        selectMenu("Show All Laptops & Notebooks");
        clickOnElement(LaptopsNotebooks);
        assertToVerify(LaptopsNotebooks, "Laptops & Notebooks");

    }

    /**
     * verifyUserShouldNavigateToComponentsPageSuccessfully()
     * 3.1 Mouse hover on “Components” Tab and click
     * 3.2 call selectMenu method and pass the menu = “Show All Components”
     * 3.3 Verify the text ‘Components’
     */
    @Test
    public void verifyUserShouldNavigateToComponentsPageSuccessfully() {
        By components = By.xpath("//a[normalize-space()='Components']");
        hoverMouseToElement(components);
        selectMenu("Show All Components");
        clickOnElement(components);
        assertToVerify(components, "Components");


    }


}
