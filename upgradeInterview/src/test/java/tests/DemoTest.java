package tests;

import com.github.javafaker.Faker;
import com.google.gson.*;
import environment.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoTest {

    private final static Logger LOGGER = Logger.getLogger(DemoTest.class.getName());

    @BeforeEach
    public void startBrowser() {
        EnvironmentManager.initWebDriver();
    }

    @Test
    public void ask_for_credit() {
        WebDriver driver = RunEnvironment.getWebDriver();
        driver.manage().window().maximize();
        int amount = 2000;
        String purpose = "OTHER";
        String username = "candidate" + Integer.toString ((int) (Math.random() * 490 + 1)) + "@upgrade-challenge.com";
        String password = "1Default";
        Faker faker = new Faker();
        String name = faker.name().firstName();
        String borrower_last_name = faker.name().lastName();
        String birth_date = "01/01/1930";
        String borrower_street = "3501 Nicollet Avenue, Minneapolis, MN, USA";
        int income = 100000;
        int additional_income = 5000;
        CreditOffer.check_rate(driver, amount, purpose);
        LOGGER.info("Check Rate for " + Integer.toString(amount) + " and " + purpose + " was executed successfully");
        Registration.fill_basic_information(driver, name, borrower_last_name, birth_date, borrower_street, income, additional_income);
        LOGGER.info("Profile information was enter successfully for " + name + " " + borrower_last_name);
        Registration.create_account(driver, username, password);
        LOGGER.info("Account was created successfully for " + username);
        CreditOffer.validate_offer(driver, username, password);
        LOGGER.info("The offer is correct");

    }
    @Test
    public void get_states() throws IOException, URISyntaxException {
        LOGGER.info("----- Start to Test GET States on API -------");
        JsonElement json_response = Common.execute_get_request();
        JsonArray states_array = ((JsonObject) json_response).get("states").getAsJsonArray();
        assertEquals(48, states_array.size(), "Size Response is different than expected value");
        LOGGER.info("Amount of States is correct");
        States.state_validation(states_array);
        LOGGER.info("States are correctly validated");
        LOGGER.info("----- End Test GET States on API -------");
    }

    @AfterEach
    public void tearDown() {
        EnvironmentManager.shutDownDriver();
    }


}
