package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MyStepdefs {

    private WebDriver driver;

    @Given("I have chosen the browser {string}")
    public void iHaveChosenTheBrowser(String browser) {
        switch (browser){
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "fireFox":
                driver = new FirefoxDriver();
                break;
            default:
                break;
        }
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    @And("I enter my date of birth")
    public void iEnterMyDateOfBirth() {
        click(driver, By.id("dp"));
        click(driver, By.cssSelector(".datepicker-days .datepicker-switch"));
        click(driver, By.cssSelector(".datepicker-months .datepicker-switch"));
        click(driver, By.cssSelector(".datepicker-years .prev"));
        click(driver, By.cssSelector(".datepicker-years .prev"));
        click(driver, By.cssSelector(".year:nth-child(2)"));
        click(driver, By.cssSelector(".month:nth-child(1)"));
        click(driver, By.cssSelector("tr:nth-child(1) > .day:nth-child(7)"));
    }

    @And("I enter first name {string} and last name {string}")
    public void iEnterFirstNameAndLastName(String fName, String lName) {
        driver.findElement(By.id("member_firstname")).sendKeys(fName);
        driver.findElement(By.id("member_lastname")).sendKeys(lName);
    }

    @And("I enter a email")
    public void iEnterAEmail() {
        String email = "test" + System.currentTimeMillis() + "@gmail.com";
        driver.findElement(By.id("member_emailaddress")).sendKeys(email);
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys(email);
    }

    @And("I choose the password {string} and confirmed with {string}")
    public void iChooseThePasswordAndConfirmedWith(String password, String cPassword) {
        driver.findElement(By.id("signupunlicenced_password")).sendKeys(password);
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys(cPassword);
    }

    @And("I {string} the terms and conditions")
    public void iTheTermsAndConditions(String choice) {
        if(choice.equals("accept")) {
            click(driver, By.cssSelector("label[for='sign_up_25']"));

            if (driver.findElements(By.cssSelector(".md-checkbox input[type=checkbox]:checked~label>.check")).isEmpty()) {
                System.out.println("didnt click");
                click(driver, By.cssSelector("label[for='sign_up_25']"));
            }
        }
    }

    @And("I'm over 18 and agree to the code of ethics")
    public void iMOver18AndAgreeToTheCodeOfEthics() {
        click(driver, By.cssSelector(".md-checkbox:nth-child(2) > label > .box"));
        click(driver, By.cssSelector(".md-checkbox:nth-child(7) .box"));
    }

    @When("I click confirm and join")
    public void iClickConfirmAndJoin() {
        click(driver, By.name("join"));
    }

    @Then("I get the result {string}")
    public void iGetTheResult(String result) {
        switch (result){
            case "accepted":
                assertEquals("THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND", driver.findElement(By.cssSelector(".bold:nth-child(1)")).getText());
                break;
            case "rejected":
                List<WebElement> error = driver.findElements(By.cssSelector(".warning > span"));
                assert(!error.isEmpty());
                break;
            default:
                break;
        }

//        switch (result){
//            case "accepted":
//                assertEquals("THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND", driver.findElement(By.cssSelector(".bold:nth-child(1)")).getText());
//                break;
//            case "You must confirm that you have read and accepted our Terms and Conditions":
//                List<WebElement> termsError = driver.findElements(By.cssSelector(".warning > span"));
//                assertEquals(result, termsError.get(0).getText());
//                break;
//            case "Password did not match":
//                List<WebElement> passError = driver.findElements(By.cssSelector(".warning > span"));
//                assertEquals(result, passError.get(0).getText());
//                break;
//            case "Last Name is required":
//                List<WebElement> lNameError = driver.findElements(By.cssSelector(".warning > span"));
//                assertEquals(result, lNameError.get(0).getText());
//                break;
//            default:
//                break;
//        }
    }

    private static void click(WebDriver driver,By by){
        new WebDriverWait(driver,Duration.ofSeconds(4)).until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }


}
