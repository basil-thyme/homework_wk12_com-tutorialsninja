package laptopsandnotebooks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.Utility;

import java.util.ArrayList;
import java.util.List;

public class LaptopsAndNotebooksTest extends Utility {

    static String email = "xyz_999@example.com";
    static String password = "Password1";

    /**
     * 1.1 Mouse hover on Laptops & Notebooks Tab.and click
     * 1.2 Click on “Show All Laptops & Notebooks”
     * 1.3 Select Sort By "Price (High > Low)"
     * 1.4 Verify the Product price will arrange in High to Low order.
     */
    @Before
    public void setUp() {
        String baseUrl = "http://tutorialsninja.com/demo/index.php?";
        openBrowser(baseUrl);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }

    /**
     * Test name verifyProductsPriceDisplayHighToLowSuccessfully()
     * 1.1 Mouse hover on Laptops & Notebooks Tab.and click
     * 1.2 Click on “Show All Laptops & Notebooks”
     * 1.3 Select Sort By "Price (High > Low)"
     * 1.4 Verify the Product price will arrange in High to Low order.
     */
    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully() throws InterruptedException {
        By LaptopsNotebooks = By.xpath("//a[normalize-space()='Laptops & Notebooks']");
        hoverMouseToElement(LaptopsNotebooks);
        clickOnElement(LaptopsNotebooks);

        By showAllLaptops = By.xpath("//a[normalize-space()='Show AllLaptops & Notebooks']");
        clickOnElement(showAllLaptops);

        By sortByPrice = By.xpath("//select[@id='input-sort']");
        selectElementFromDropDownByVisibleText(sortByPrice, "Price (High > Low)");

        ArrayList<String> actualList = new ArrayList<>();
        Thread.sleep(2000);
        List<WebElement> listOfElements = driver.findElements(By.xpath("//div[@class='caption']//p[@class='price']"));

        for (int i = 0; i < listOfElements.size(); i++) {
            String s = listOfElements.get(i).getText();
            String[] a = s.split("E");
            actualList.add(a[0]);
        }
        ArrayList<String> expectedList = new ArrayList<>();

        for (int i = 0; i < listOfElements.size(); i++) {
            String s = listOfElements.get(i).getText();
            String[] a = s.split("E");
            expectedList.add(a[0]);
        }
        Assert.assertEquals("Not matching", expectedList, actualList);
    }

    @Test
    public void verifyThatUserPlaceOrderSuccessfully() throws InterruptedException {
        //2.1 Mouse hover on Laptops & Notebooks Tab and click
        By LaptopsNotebooks = By.xpath("//a[normalize-space()='Laptops & Notebooks']");
        hoverMouseToElement(LaptopsNotebooks);
        clickOnElement(LaptopsNotebooks);

        //2.2 Click on “Show All Laptops & Notebooks”
        By showAllLaptops = By.xpath("//a[normalize-space()='Show AllLaptops & Notebooks']");
        clickOnElement(showAllLaptops);

        //2.3 Select Sort By "Price (High > Low)"
        By sortByPrice = By.xpath("//select[@id='input-sort']");
        selectElementFromDropDownByVisibleText(sortByPrice, "Price (High > Low)");

        //2.4 Select Product “MacBook”
        By selectMacBook = By.xpath("//a[normalize-space()='MacBook']");
        clickOnElement(selectMacBook);

        //2.5 Verify the text “MacBook”
        By verifyMacBook = By.xpath("//h1[normalize-space()='MacBook']");
        clickOnElement(verifyMacBook);

        //2.6 Click on ‘Add To Cart’ button
        By addToCart = By.xpath("//button[@id='button-cart']");
        clickOnElement(addToCart);

        //2.7 Verify the message “Success: You have added MacBook to your shopping cart!”
        By verifyMessage = By.xpath("//div[@class='alert alert-success alert-dismissible']");
        WebElement element = driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"));
        String actual = element.getText();
        System.out.println(actual);

        String expected = "Success: You have added MacBook to your shopping cart!";
        System.out.println(expected);

        boolean message = actual.contains(expected.trim());
        Assert.assertTrue(message);

        //2.8 Click on link “shopping cart” display into success message
        By shoppingCart = By.xpath("//a[normalize-space()='shopping cart']");
        clickOnElement(shoppingCart);

        //2.9 Verify the text "Shopping Cart"
        By testShoppingCart = By.xpath("//h1[contains(text(),'Shopping Cart')]");
        assertToVerify(testShoppingCart, "Shopping Cart  (0.00kg)");

        //2.10 Verify the Product name "MacBook"
        By productName = By.xpath("(//a[contains(text(),'MacBook')])[2]");
        assertToVerify(productName, "MacBook");

        //2.12 Click on “Update”Tab
        By updateTab = By.xpath("//i[@class='fa fa-refresh']");
        clickOnElement(updateTab);

        //2.13 Verify the message “Success: You have modified your shopping cart!”
        By verifyTheMessage = By.xpath("//div[@class='alert alert-success alert-dismissible']");

        WebElement elementMessage = driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"));
        String actualMessage = elementMessage.getText();
        System.out.println(actualMessage);

        String expectedMessage = "Success: You have added MacBook to your shopping cart!";
        System.out.println(expectedMessage);
        boolean messageVerification = actual.contains(expectedMessage.trim());
        Assert.assertTrue(messageVerification);

        //2.14 Verify the Total £737.45
        By totalPriceLocator = By.xpath("(//td[@class='text-right'][normalize-space()='$602.00'])[4]");
        assertToVerify(totalPriceLocator, "$602.00");

        // 2.15 Click on “Checkout” button
        By checkoutButtonLocator = By.xpath("//a[@class='btn btn-primary']");
        clickOnElement(checkoutButtonLocator);

        //2.16 Verify the text “Checkout”
        By checkout = By.xpath("//h1[normalize-space()='Checkout']");
        assertToVerify(checkout, "Checkout");

        //2.17 Verify the Text “New Customer”
        By newCustomer = By.xpath("//h2[normalize-space()='New Customer']");
        Thread.sleep(2000);
        assertToVerify(newCustomer, "New Customer");

        // 2.18 Click on “Guest Checkout” radio button
        By guestCheckout = By.xpath("//input[@name='account' and @value='guest']");
        clickOnElement(guestCheckout);

        // 2.19 Click on “Continue” tab
        By clickContinue = By.xpath("//input[@id='button-account']");
        clickOnElement(clickContinue);

        //2.20 Fill the mandatory fields
        By firstName = By.xpath("//input[@id='input-payment-firstname']");
        sendTextToElement(firstName, "John");


        By lastName = By.xpath("//input[@id='input-payment-lastname']");
        sendTextToElement(lastName, "Smith");

        Thread.sleep(1000);
        By emailId = By.cssSelector("#input-payment-email");
        sendTextToElement(emailId, email);

        By telephone = By.cssSelector("#input-payment-telephone");
        sendTextToElement(telephone, "0712345678");

//        By passwordField = By.xpath("//input[@id='input-payment-password']");
//        sendTextToElement(passwordField, password);

//        By confirmPassword = By.xpath("//input[@id='input-payment-confirm']");
//        sendTextToElement(confirmPassword, password);

        By address1 = By.cssSelector("#input-payment-address-1");
        sendTextToElement(address1, "Random Street");

        By city = By.cssSelector("#input-payment-city");
        sendTextToElement(city, "London");

        By postcode = By.cssSelector("#input-payment-postcode");
        sendTextToElement(postcode, "AS3BE3");

        By country = By.cssSelector("#input-payment-country");
        selectElementFromDropDownByIndex(country, 11);

        By region = By.cssSelector("#input-payment-zone");
        selectElementFromDropDownByIndex(region, 5);

        //2.21 Click on “Continue” Button
        By continueButtonAfterBillingLocator = By.id("button-guest");
        clickOnElement(continueButtonAfterBillingLocator);

        //2.22 Add Comments About your order into text area
        By commentBoxLocator = By.name("comment");
        sendTextToElement(commentBoxLocator, "This is my comment");

        // 2.23 Check the Terms & Conditions check box
        By termsCheckBox = By.name("agree");
        clickOnElement(termsCheckBox);

        //2.24 Click on “Continue” button
        By continueButtonPaymentMethodLocator = By.id("button-payment-method");
        clickOnElement(continueButtonPaymentMethodLocator);

        //2.25 Verify the message “Warning: Payment method required!”
        By warningPaymentLocator = By.cssSelector(".alert.alert-danger.alert-dismissible");
        assertToVerifyTextContains(warningPaymentLocator, "Warning: Payment method required!");

    }
}

