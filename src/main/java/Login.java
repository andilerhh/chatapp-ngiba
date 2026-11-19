import java.util.regex.Pattern;

/**
 * Handles registration and login for the chat app.
 */
public class Login {

    // Regex: '+' then a 1-3 digit country code, then at most 10 digits.
    
    
    private static final Pattern CELL_PATTERN = Pattern.compile("^\\+\\d{1,3}\\d{1,10}$");

    private String username;
    private String password;
    private String cellPhone;
    private final String firstName;
    private final String lastName;

    public Login(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setCellPhone(String cellPhone) { this.cellPhone = cellPhone; }

    /** Username must contain an underscore and be no more than five characters. */
    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    /** Password: 8+ characters, a capital letter, a number and a special character. */
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasUpper = false, hasDigit = false, hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        return hasUpper && hasDigit && hasSpecial;
    }

    /** Cell number must start with an international code which is +27. */
    public boolean checkCellPhoneNumber(String cellPhone) {
        return cellPhone != null && CELL_PATTERN.matcher(cellPhone).matches();
    }

    public String usernameMessage(String username) {
        return checkUserName(username)
                ? "Username successfully captured."
                : "Username is not correctly formatted; please ensure that your username "
                + "contains an underscore and is no more than five characters in length.";
    }

    public String passwordMessage(String password) {
        return checkPasswordComplexity(password)
                ? "Password successfully captured."
                : "Password is not correctly formatted; please ensure that the password "
                + "contains at least eight characters, a capital letter, a number, and a special character.";
    }

    public String cellPhoneMessage(String cellPhone) {
        return checkCellPhoneNumber(cellPhone)
                ? "Cell number successfully captured."
                : "Cell number is incorrectly formatted or does not contain an international code; "
                + "please correct the number and try again.";
    }

    /** Registers the user using the stored details and returns the resulting message. */
    public String registerUser() {
        if (!checkUserName(username)) {
            return usernameMessage(username);
        }
        if (!checkPasswordComplexity(password)) {
            return passwordMessage(password);
        }
        return "User has been registered successfully.";
    }

    /** Checks the entered details against the registered details. */
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return username != null && password != null
                && username.equals(enteredUsername)
                && password.equals(enteredPassword);
    }

    /** Returns the message for a successful or failed login. */
    public String returnLoginStatus(boolean loggedIn) {
        return loggedIn
                ? "Welcome " + firstName + "," + lastName + " it is great to see you."
                : "Username or password incorrect, please try again.";
    }
}
