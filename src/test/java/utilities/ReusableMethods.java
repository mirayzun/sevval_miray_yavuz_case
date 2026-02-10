package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class ReusableMethods {
    public SoftAssert softAssert= new SoftAssert();

    //===============Explicit Wait==============//
    public static boolean isElementDisplayed(WebElement element, int timeoutInSec) {
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeoutInSec));
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            throw new TimeoutException("The element was not visible within the given time of " + timeoutInSec + " seconds.");
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while waiting for element visibility: " + e.getMessage());
        }
    }

    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            System.out.println("Waiting for page to load...");
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeOutInSeconds));
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println(
                    "Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
        }
    }

    //======Fluent Wait====//
    public static WebElement fluentWait(final WebElement webElement, int timeinsec) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver())
                .withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofSeconds(15))
                .ignoring(NoSuchElementException.class);
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return webElement;
            }
        });
        return element;
    }
    public void closeModalWithEsc() {
        List<WebElement> modals = Driver.getDriver().findElements(By.cssSelector(".ins-responsive-banner-image"));

        /*
        if (!modals.isEmpty()) {
            WebElement modal = modals.get(0);

            if (modal.isDisplayed()) {
                new Actions(Driver.getDriver())
                        .sendKeys(Keys.ESCAPE)
                        .perform();

            }
        }*/

        if(!modals.isEmpty()) {
            var driver = Driver.getDriver();
            for (WebElement modal : modals) {
                if (modal.isDisplayed()) {
                    {
                        new Actions(driver)
                                .sendKeys(Keys.ESCAPE)
                                .perform();
                    }
                }
            }
        }

    }
    public boolean isElementVisible(WebElement element) {
        closeModalWithEsc();

        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void kontrolTikla(WebElement element){
        Assert.assertTrue(element.isDisplayed(),"Element is not visible on the page!");
        element.click();
    }

    public void verifyElementContainsText(WebElement actualElement, String expectedText) {

        Assert.assertTrue(actualElement.isDisplayed(), "Element is not visible on the page!");
        String actualText = actualElement.getText().trim();
        Assert.assertTrue(actualText.contains(expectedText),
                "Verification Failed! Expected to find: [" + expectedText + "] but found: [" + actualText + "]");
    }

    public boolean verifyJobPositionsDetails(List<WebElement> positions) {

        if(positions.isEmpty()){
            return false;
        }

        for (WebElement job : positions){
            // position
            String position= job.findElement(By.xpath(".//p[@class='position-title font-weight-bold']"))
                    .getText();
            // department
            String department=job.findElement(By.xpath(".//span[contains(@class,'position-department')]"))
                    .getText();
            // location
            String location= job.findElement(By.xpath(".//div[@class='position-location text-large']"))
                    .getText();

            Assert.assertFalse(position.isEmpty());
            Assert.assertTrue(position.contains("Quality"),"The position title does not contain the word 'Quality'");
            softAssert.assertTrue(position.contains("Assurance"),"The position title does not contain the word 'Assurance'.");

            Assert.assertFalse(department.isEmpty());
            Assert.assertEquals(department, "Quality Assurance","The position department does not contain the word 'Quality Assurance'"+ position);

            Assert.assertFalse(location.isEmpty());
            Assert.assertEquals(location, "Istanbul, Turkiye","The position location does not contain the word 'Istanbul, Turkiye'");
        }

        return true;

    }

    public void switchToPage() {
        String currentHandle = Driver.getDriver().getWindowHandle();
        Set<String> allHandles = Driver.getDriver().getWindowHandles();

        for (String handle : allHandles) {
            if (!handle.equals(currentHandle)) {
                Driver.getDriver().switchTo().window(handle);
                break;
            }
        }
    }
    public void pageURLCheck() {
        String expectedURL = "lever.co";
        String actualURL = Driver.getDriver().getCurrentUrl();

        Assert.assertTrue(actualURL.contains(expectedURL), actualURL);
    }

}
