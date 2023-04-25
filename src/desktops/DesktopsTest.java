package desktops;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.Utility;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DesktopsTest extends Utility {

    static String month = "November";
    static String year = "2022";
    static int day = 11;
    static String calMonth = month + " " + year;

    @Before
    public void setUp() {
        String baseUrl = "http://tutorialsninja.com/demo/index.php?";
        openBrowser(baseUrl);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
    By desktops = By.xpath("//a[@class='dropdown-toggle'][normalize-space()='Desktops']");
    By ShowAllDesktops = By.xpath("//a[normalize-space()='Show AllDesktops']");
    By sortZtoA = By.xpath("//select[@id='input-sort']");
    By sortAtoZ = By.xpath("//select[@id='input-sort']");
    By HPLP3065 = By.xpath("//a[normalize-space()='HP LP3065']");
    By LP3065Text = By.xpath("//a[normalize-space()='HP LP3065']");
    By clickOnDeliveryDate = By.xpath("//i[@class='fa fa-calendar']");
    By enterQuantity = By.xpath("//input[@id='input-quantity']");
    By AddToCart = By.xpath("//button[@id='button-cart']");
    By shoppingCart = By.xpath("//a[normalize-space()='shopping cart']");
    By shoppingCartMessage = By.xpath("//h1[contains(text(),'Shopping Cart')]");
    By productName = By.xpath("(//a[contains(text(),'HP LP3065')])[2]");
    By dateChecking = By.xpath("(//td[@class='text-left']//small[contains(text(),'Delivery Date')])[2]");
    By product21 = By.xpath("//td[normalize-space()='Product 21']");
    By total = By.xpath("(//td[contains(text(),'$122.00')])[4]");

    /**
     * 1.Test name verifyProductArrangeInAlphaBaticalOrder()
     * 1.1 Mouse hover on Desktops Tab.and click
     * 1.2 Click on “Show All Desktops”
     * 1.3 Select Sort By position "Name: Z to A"
     * 1.4 Verify the Product will arrange in Descending order.
     */
    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() throws InterruptedException {

        hoverMouseToElementAndClick(desktops);
        clickOnElement(ShowAllDesktops);
        selectElementFromDropDownByIndex(sortZtoA, 2); // Z-A descending order

        ArrayList<String> actualList = new ArrayList<>();
        List<WebElement> listOfElements = driver.findElements(By.xpath("//div[@class='caption']//h4"));
        System.out.println("listOfElements=" + listOfElements);
        for (WebElement element : listOfElements) {
            actualList.add(element.getText());
            System.out.println(element.getText());
        }

        //System.out.println("actual list = " + actualList);

        ArrayList<String> expectedList = new ArrayList<>();
        for (WebElement element : listOfElements) {
            expectedList.add(element.getText());
            System.out.println("expected list = " + element.getText());
        }

        //System.out.println("expected list = " + expectedList);

        Thread.sleep(3000);
        // z -a
        Collections.sort(expectedList, String.CASE_INSENSITIVE_ORDER);
        System.out.println("expected sort = " + expectedList);
        Collections.reverse(expectedList);

        System.out.println("expected reverse = " + expectedList);
        Assert.assertEquals("Not matching", expectedList, actualList);
    }

    //By desktops = By.xpath("//a[@class='dropdown-toggle'][normalize-space()='Desktops']");
    //By ShowAllDesktops = By.xpath("//a[normalize-space()='Show AllDesktops']");
    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {

        // 2.1 Mouse hover on Desktops Tab.and click

        hoverMouseToElementAndClick(desktops);
        //2.2 Click on “Show All Desktops”
        clickOnElement(ShowAllDesktops);
        //2.3 Select Sort By position "Name: A to Z"
        selectElementFromDropDownByIndex(sortAtoZ, 1);
        // 2.4 Select product “HP LP3065”
        clickOnElement(HPLP3065);
        //2.5 Verify the Text "HP LP3065"
        assertToVerify(LP3065Text, "HP LP3065");

        //2.6 Select Delivery Date "2022-11-30"
//        String month = "November 2011";
//        String day = "25";

        clickOnElement(clickOnDeliveryDate);
        Thread.sleep(2000);
        System.out.println("calmonth = " + calMonth);
        while (true) {

            WebElement element = driver.findElement(By.cssSelector(".picker-switch"));
            String text = element.getText().trim();

            if (text.equals(calMonth)) {
                break;
            } else {
                driver.findElement(By.xpath("//div[@class='datepicker-days']//th[@class='next'][contains(text(),'›')]")).click();
            }
        }

        List<WebElement> dateList = driver.findElements(By.xpath("//tbody//tr//td[@class='day']"));

        // parsing int day into string to use equals method in for each loop
        String dayInString = Integer.toString(day);
        for (WebElement element : dateList) {
            if (element.getText().trim().equals(dayInString)) {
                System.out.println("my day=" + element.getText().trim());
                element.click();
                break;
            }
        }

        //2.7.Enter Qty "1” using Select class.

        WebElement element = driver.findElement(enterQuantity);
        element.clear();
        sendTextToElement(enterQuantity, "1");
        //2.8 Click on “Add to Cart” button
        clickOnElement(AddToCart);

        //2.9 Verify the Message “Success:
        //You have added HP LP3065 to your shopping cart !”
        WebElement elementMessage = driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"));
        String actual = elementMessage.getText();
        System.out.println(actual);
        String expected = "Success: You have added HP LP3065 to your shopping cart!";

        System.out.println(expected);
        boolean message = actual.contains(expected.trim());
        Assert.assertTrue(message);
        //assertToVerify(successMessage,"Success: You have added HP LP3065 to your shopping cart!" );

        //2.10 Click on link “shopping cart”display into success message

        clickOnElement(shoppingCart);
        //2.11 Verify the text "Shopping Cart"
        assertToVerify(shoppingCartMessage, "Shopping Cart  (1.00kg)");
        //2.12 Verify the Product name "HP LP3065"
        assertToVerify(productName, "HP LP3065");

        //2.13 Verify the Delivery Date "2022-11-30"
        // converting Month name to two digit numbered string
        int monthInInt = Month.valueOf(month.toUpperCase()).getValue();
        String monthInTwoDigits = String.format("%02d", monthInInt);

        // converting single digit day string into two digit by adding '0' before number in string
        String dayInTwoDigits = String.format("%02d", day);

        // preparing expected delivery date
        String deliveryDate = year + "-" + monthInTwoDigits + "-" + dayInTwoDigits;
        System.out.println("expected delivery date = " + deliveryDate);


        //System.out.println("xpath gettext="+ dateChecking);
        WebElement element1 = driver.findElement(dateChecking);
        System.out.println("actual delivery date: " + element1.getText());
        assertToVerify(dateChecking, "Delivery Date:" + deliveryDate);

        //2.14 Verify the Model "Product21"
        assertToVerify(product21, "Product 21");

        //2.15 Verify the Todat "£74.73"
        assertToVerify(total, "$122.00");
    }
}

