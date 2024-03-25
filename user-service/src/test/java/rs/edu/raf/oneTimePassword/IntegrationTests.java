package rs.edu.raf.oneTimePassword;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.mock.mockito.MockBean;
import rs.edu.raf.model.OneTimePassword;
import rs.edu.raf.repository.OneTimePasswordRepository;
import rs.edu.raf.servis.impl.OneTimePasswordService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegrationTests {

    @MockBean
    private OneTimePasswordService oneTimePasswordService;
    @MockBean
    OneTimePasswordRepository oneTimePasswordRepository;


    private String generatedPassword;
    private String email;
    private boolean validationResponse;
    private boolean expiredPasswordDeleted;

    @When("I generate a one-time password for email {string}")
    public void iGenerateAOneTimePasswordForEmail(String email) {
        generatedPassword = oneTimePasswordService.generateOneTimePassword(email).getPassword();
        this.email = email;
    }

    @Then("I receive a valid one-time password")
    public void iReceiveAValidOneTimePassword() {
        assertTrue(generatedPassword.matches("\\d{6}"));
    }

    @Given("I have a one-time password {string} for email {string}")
    public void iHaveAOneTimePasswordForEmail(String password, String email) {
        OneTimePassword otp = oneTimePasswordService.generateOneTimePassword(email);
        otp.setPassword(password);
        oneTimePasswordService.generateOneTimePassword(email);
    }

    @When("I validate the one-time password {string} for email {string}")
    public void iValidateTheOneTimePasswordForEmail(String password, String email) {
        validationResponse = oneTimePasswordService.validateOneTimePassword(email, password);
    }

    @Then("I receive a valid validation response")
    public void iReceiveAValidValidationResponse() {
        assertTrue(validationResponse);
    }

    @Given("I have an expired one-time password {string} for email {string}")
    public void iHaveAnExpiredOneTimePasswordForEmail(String password, String email) {
        OneTimePassword otp = oneTimePasswordService.generateOneTimePassword(email);
        otp.setPassword(password);
        otp.setExpiration(LocalDateTime.now().minusMinutes(10)); // Expired
        oneTimePasswordService.generateOneTimePassword(email);
    }

    @When("I trigger the cleanup process")
    public void iTriggerTheCleanupProcess() {
        oneTimePasswordService.cleanupOneTimePasswords();
        expiredPasswordDeleted = oneTimePasswordRepository.findByEmail(email).isEmpty();
    }

    @Then("the expired password is not deleted")
    public void theExpiredPasswordIsNotDeleted() {
        assertFalse(expiredPasswordDeleted);
    }
}


