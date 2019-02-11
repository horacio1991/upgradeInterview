package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.util.logging.*;

public class Registration {

    private final static Logger LOGGER = Logger.getLogger(DemoTest.class.getName());

    public static void create_account(WebDriver driver, String username, String password) {
        WebElement user = driver.findElement(By.name("username"));
        user.click();
        user.sendKeys(username);
        LOGGER.log(Level.FINE, username +" was enter successfully");
        WebElement pass = driver.findElement(By.name("password"));
        pass.click();
        pass.sendKeys(password);
        LOGGER.log(Level.FINE, "password was enter successfully");
        //Accept Terms of Use
        driver.findElement(By.cssSelector(".sc-elJkPf.fTrOMC.sc-bYSBpT.kRvujO")).click();
        LOGGER.log(Level.FINE, "Terms of use was accepted successfully");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-auto=submitPersonalInfo]")));
        driver.findElement(By.cssSelector("button[data-auto=submitPersonalInfo]")).click();
        LOGGER.log(Level.FINE, "Create account was submitted successfully");

    }

    public static void fill_basic_information(WebDriver driver, String name, String borrower_last_name, String birth_date,String borrower_street,int income, int additional_income ) {
        WebElement first_name = driver.findElement(By.cssSelector(".sc-cMljjf.gkTTew"));
        first_name.click();
        first_name.sendKeys(name);
        LOGGER.log(Level.FINE, name + " was entered as name successfully");
        WebElement last_name = driver.findElement(By.name("borrowerLastName"));
        last_name.click();
        last_name.sendKeys(borrower_last_name);
        LOGGER.log(Level.FINE, borrower_last_name + "  was enter as last name successfully");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.name("borrowerStreet")));
        WebElement street = driver.findElement(By.name("borrowerStreet"));
        street.sendKeys(borrower_street);
        street.sendKeys(Keys.ENTER);
        LOGGER.log(Level.FINE, borrower_street + " was enter as borrower street successfully");
        wait.until(ExpectedConditions.elementToBeClickable(By.name("borrowerDateOfBirth")));
        WebElement dateOfBirth = driver.findElement(By.name("borrowerDateOfBirth"));
        dateOfBirth.click();
        dateOfBirth.sendKeys(birth_date);
        dateOfBirth.sendKeys(Keys.ENTER);
        LOGGER.log(Level.FINE, birth_date + " as birth date was entered successfully");
        wait.until(ExpectedConditions.elementToBeClickable(By.name("borrowerIncome")));
        WebElement anual_income = driver.findElement(By.name("borrowerIncome"));
        anual_income.click();
        anual_income.sendKeys(Integer.toString(income));
        anual_income.sendKeys(Keys.ENTER);
        LOGGER.log(Level.FINE, (Integer.toString(income)) +" was entered as Borrower Income");
        wait.until(ExpectedConditions.elementToBeClickable(By.name("borrowerIncome")));
        WebElement add_income = driver.findElement(By.name("borrowerAdditionalIncome"));
        add_income.click();
        add_income.sendKeys(Integer.toString(additional_income));
        add_income.sendKeys(Keys.ENTER);
        LOGGER.log(Level.FINE,Integer.toString(additional_income) +  " was entered as Additional Borrower Income");

    }
}