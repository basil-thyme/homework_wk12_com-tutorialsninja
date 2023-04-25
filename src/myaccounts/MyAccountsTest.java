package myaccounts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.Utility;

import java.util.List;

public class MyAccountsTest extends Utility {

    static String email;
    static String password = "Password1";

    @Before
    public void setUp() {
        String baseUrl = "http://tutorialsninja.com/demo/index.php?";
        openBrowser(baseUrl);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }


    //● Create package myaccounts
//1. Create the class MyAccountsTest
//1.1 create method with name "selectMyAccountOptions" it has one parameter name "option" of type string
    public void selectMyAccountOptions(String option) {
        clickOnElement(By.linkText("My Account"));
        List<WebElement> optionsList = driver.findElements(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//li/a"));
        for (WebElement element : optionsList) {
            if (element.getText().equalsIgnoreCase(option)) {
                clickOnElement(element);
                break;
            }
        }
    }

    /**
     * //1. Test name verifyUserShouldNavigateToRegisterPageSuccessfully()
     * //1.1 Click on My Account Link.
     * //1.2 Call the method “selectMyAccountOptions” method and pass the parameter “Register”
     * //            1.3 Verify the text “Register Account”.
     */
    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() {
        selectMyAccountOptions("Register");
        assertToVerify(By.xpath("//h1[normalize-space()='Register Account']"), "Register Account");
    }

    /**
     * 2. Test name verifyUserShouldNavigateToLoginPageSuccessfully()
     * 2.1 Clickr on My Account Link.
     * 2.2 Call the method “selectMyAccountOptions” method and pass the parameter
     * “Login”
     * 2.3 Verify the text “Returning Customer”.
     */

    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {
        selectMyAccountOptions("Login");
        assertToVerify(By.xpath("//h2[normalize-space()='Returning Customer']"), "Returning Customer");
    }

    /**
     * 3. Test name verifyThatUserRegisterAccountSuccessfully()
     * 3.1 Clickr on My Account Link.
     * 3.2 Call the method “selectMyAccountOptions” method and pass the parameter
     * “Register”
     * 3.3 Enter First Name
     * 3.4 Enter Last Name
     * 3.5 Enter Email
     * 3.6 Enter Telephone
     * 3.7 Enter Password
     * 3.8 Enter Password Confirm
     * 3.9 Select Subscribe Yes radio button
     * 3.10 Click on Privacy Policy check box
     * 3.11 Click on Continue button
     * 3.12 Verify the message “Your Account Has Been Created!”
     * 3.13 Click on Continue button
     * 3.14 Clickr on My Account Link.
     * 3.15 Call the method “selectMyAccountOptions” method and pass the parameter
     * “Logout”
     * 3.16 Verify the text “Account Logout”
     * 3.17 Click on Continue button
     */
    @Test
    public void verifyThatUserRegisterAccountSuccessfully() {
//        * 3.1 Clickr on My Account Link.
//                * 3.2 Call the method “selectMyAccountOptions” method and pass the parameter
//                * “Register”
        selectMyAccountOptions("Register");
//     * 3.3 Enter First Name
        sendTextToElement(By.cssSelector("#input-firstname"), "Moka");
//                * 3.4 Enter Last Name
        sendTextToElement(By.cssSelector("#input-lastname"), "Masi");
//                * 3.5 Enter Email
        MyAccountsTest.email = doGetRandomString(3) + "_999@example.com";
        sendTextToElement(By.cssSelector("#input-email"), MyAccountsTest.email);
//     * 3.6 Enter Telephone
        sendTextToElement(By.cssSelector("#input-telephone"), "01253644587");
//     * 3.7 Enter Password
        sendTextToElement(By.cssSelector("#input-password"), MyAccountsTest.password);
//     * 3.8 Enter Password Confirm
        sendTextToElement(By.cssSelector("#input-confirm"), MyAccountsTest.password);
//                * 3.9 Select Subscribe Yes radio button
        clickOnElement(By.cssSelector("input[value='1'][name='newsletter']"));
//                * 3.10 Click on Privacy Policy check box
        clickOnElement(By.xpath("//input[@type='checkbox']"));
//     * 3.11 Click on Continue button
        clickOnElement(By.cssSelector(".btn.btn-primary"));
//     * 3.12 Verify the message “Your Account Has Been Created!”
        assertToVerify(By.xpath("//h1[normalize-space()='Your Account Has Been Created!']"), "Your Account Has Been Created!");
//     * 3.13 Click on Continue button
        clickOnElement(By.cssSelector(".btn.btn-primary"));
//     * 3.14 Clickr on My Account Link.
//                * 3.15 Call the method “selectMyAccountOptions” method and pass the parameter
//                * “Logout”
        selectMyAccountOptions("Logout");
//     * 3.16 Verify the text “Account Logout”
        assertToVerify(By.xpath("//h1[normalize-space()='Account Logout']"), "Account Logout");
//     * 3.17 Click on Continue button
        clickOnElement(By.cssSelector(".btn.btn-primary"));
    }

    /**
     * 4. Test name verifyThatUserShouldLoginAndLogoutSuccessfully()
     * 4.1 Clickr on My Account Link.
     * 4.2 Call the method “selectMyAccountOptions” method and pass the parameter
     * “Login”
     * 4.3 Enter Email address
     * 4.4 Enter Last Name
     * 4.5 Enter Password
     * 4.6 Click on Login button
     * 4.7 Verify text “My Account”
     * 4.8 Clickr on My Account Link.
     * 4.9 Call the method “selectMyAccountOptions” method and pass the parameter
     * “Logout”
     * 4.10 Verify the text “Account Logout”
     * 4.11 Click on Continue button
     */

    @Test
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() {
//          * 4.1 Clickr on My Account Link.
//                * 4.2 Call the method “selectMyAccountOptions” method and pass the parameter
//                * “Login”
        selectMyAccountOptions("Login");
//     * 4.3 Enter Email address
        sendTextToElement(By.cssSelector("#input-email"), MyAccountsTest.email);
        System.out.println("Generated Email = " + MyAccountsTest.email);
//                * 4.5 Enter Password
        sendTextToElement(By.cssSelector("#input-password"), MyAccountsTest.password);
        System.out.println("Generated Password = " + MyAccountsTest.password);
//     * 4.6 Click on Login button
        clickOnElement(By.xpath("//input[@class='btn btn-primary' and @value='Login']"));
//     * 4.7 Verify text “My Account”
        assertToVerify(By.xpath("//div[@id='content']/h2[1]"), "My Account");
//     * 4.8 Clickr on My Account Link.
//                * 4.9 Call the method “selectMyAccountOptions” method and pass the parameter
//                * “Logout”
        selectMyAccountOptions("Logout");
//     * 4.10 Verify the text “Account Logout”
        assertToVerify(By.xpath("//h1[normalize-space()='Account Logout']"), "Account Logout");
//     * 4.11 Click on Continue button
        clickOnElement(By.cssSelector(".btn.btn-primary"));
    }
}
