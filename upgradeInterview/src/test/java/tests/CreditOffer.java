package tests;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditOffer {

    private final static Logger LOGGER = Logger.getLogger(DemoTest.class.getName());

    public static void check_rate(WebDriver driver, int amount, String purpose) {
        driver.get("https://www.credify.tech/phone/nonDMFunnel");
        WebDriverWait wait = new WebDriverWait(driver, 3000);
        wait.until(ExpectedConditions.elementToBeClickable(By.name("desiredAmount")));
        WebElement loan = driver.findElement(By.name("desiredAmount"));
        loan.click();
        loan.sendKeys(Integer.toString(amount));
        LOGGER.log(Level.FINE, Integer.toString(amount) + " on Loan amount was enter successfully");
        driver.findElement(By.cssSelector("select[data-auto=dropLoanPurpose]")).sendKeys(purpose);
        LOGGER.log(Level.FINE, purpose + " was enter as purpose successfully");
        driver.findElement(By.cssSelector(".sc-kpOJdX.cYqHrH")).click();
        LOGGER.log(Level.FINE, "Check Rate was sent");
    }

    public static void validate_offer(WebDriver driver, String username, String password){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.textToBePresentInElement(By.cssSelector(".sc-kfGgVZ.kcRigs"), "Your Loan Amount"));
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.log(Level.FINE, "Waiting for Offer is displayed");
        String loan_amount = driver.findElement(By.cssSelector("span[data-auto=userLoanAmount]")).getText();
        LOGGER.log(Level.FINE, loan_amount+ " was shown as offer amount");
        String apr = driver.findElement(By.cssSelector("div[data-auto=defaultMoreInfoAPR]")).getText();
        LOGGER.log(Level.FINE, apr + " is APR value offered");
        String loan_term = driver.findElement(By.className("section--xs")).getText();
        LOGGER.log(Level.FINE, loan_term + " is the term offered");
        String monthly_payment = driver.findElement(By.cssSelector("div[data-auto=defaultLoanTerm")).getText();
        LOGGER.log(Level.FINE, monthly_payment + " is the monthly payment offered");
        System.out.println(monthly_payment);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("header-nav")));
        driver.findElement(By.className("header-nav")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/phone/logout']")));
        driver.findElement(By.xpath("//a[@href='/phone/logout']")).click();
        LOGGER.log(Level.FINE, "Log out Session");
        driver.get("https://www.credify.tech/portal/login");
        LOGGER.log(Level.FINE, "Logged on " + driver.getCurrentUrl());
        wait.until(ExpectedConditions.elementToBeClickable(By.name("username")));
        driver.findElement(By.name("username")).sendKeys(username);
        LOGGER.log(Level.FINE, "Logged with " + username);
        driver.findElement(By.name("password")).sendKeys(password);
        LOGGER.log(Level.FINE, "Logged with password xxxxxxx");
        driver.findElement(By.cssSelector(".sc-fBuWsC.jLFwFP")).click();
        LOGGER.log(Level.FINE, "Logged");
        wait.until(ExpectedConditions.textToBePresentInElement(By.cssSelector(".sc-kfGgVZ.kcRigs"), "Your Loan Amount"));
        assertEquals(driver.getCurrentUrl(), "https://www.credify.tech/phone/offer-page", "URL is not Offer Page");
        assertEquals(loan_amount,  driver.findElement(By.cssSelector("span[data-auto=userLoanAmount]")).getText(), "Loan amount are different");
        assertEquals(loan_term, driver.findElement(By.className("section--xs")).getText(), "Loan term are different");
        assertEquals(apr, driver.findElement(By.cssSelector("div[data-auto=defaultMoreInfoAPR]")).getText(), "APR are different");
        assertEquals(monthly_payment, driver.findElement(By.cssSelector("div[data-auto=defaultLoanTerm")).getText(), "Month are different");

    }
}

