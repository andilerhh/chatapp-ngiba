import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginTest {

    private Login login;

    @BeforeEach
    void setUp() {
        login = new Login("Kyle", "Smith");
        login.setUsername("kyl_1");
        login.setPassword("Ch&&sec@ke99!");
    }

    // ---- assertEquals tests ----
    @Test
    void usernameCorrectlyFormatted() {
        assertEquals("Username successfully captured.", login.usernameMessage("kyl_1"));
    }

    @Test
    void usernameIncorrectlyFormatted() {
        assertEquals("Username is not correctly formatted; please ensure that your username "
                + "contains an underscore and is no more than five characters in length.",
                login.usernameMessage("kyle!!!!!!!"));
    }

    @Test
    void passwordMeetsComplexity() {
        assertEquals("Password successfully captured.", login.passwordMessage("Ch&&sec@ke99!"));
    }

    @Test
    void passwordDoesNotMeetComplexity() {
        assertEquals("Password is not correctly formatted; please ensure that the password "
                + "contains at least eight characters, a capital letter, a number, and a special character.",
                login.passwordMessage("password"));
    }

    @Test
    void cellPhoneCorrectlyFormatted() {
        assertEquals("Cell number successfully captured.", login.cellPhoneMessage("+27838968976"));
    }

    @Test
    void cellPhoneIncorrectlyFormatted() {
        assertEquals("Cell number is incorrectly formatted or does not contain an international code; "
                + "please correct the number and try again.", login.cellPhoneMessage("08966553"));
    }

    @Test
    void welcomeMessageOnSuccessfulLogin() {
        assertEquals("Welcome Kyle,Smith it is great to see you.", login.returnLoginStatus(true));
    }

    // ---- assertTrue / assertFalse tests ----
    @Test
    void loginSuccessful() {
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    void loginFailed() {
        assertFalse(login.loginUser("kyl_1", "wrongPass1!"));
    }

    @Test
    void usernameCorrectlyFormattedBoolean() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    void usernameIncorrectlyFormattedBoolean() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    void passwordComplexityBoolean() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    void cellPhoneBoolean() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
}
