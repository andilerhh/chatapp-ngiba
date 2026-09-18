import java.util.Scanner;

/**
 * Console application: registration followed by login.
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("=== Registration ===");
        System.out.print("Enter your first name: ");
        String first = in.nextLine().trim();
        System.out.print("Enter your last name: ");
        String last = in.nextLine().trim();

        Login login = new Login(first, last);

        String username;
        do {
            System.out.print("Enter a username: ");
            username = in.nextLine();
            System.out.println(login.usernameMessage(username));
        } while (!login.checkUserName(username));
        login.setUsername(username);

        String password;
        do {
            System.out.print("Enter a password: ");
            password = in.nextLine();
            System.out.println(login.passwordMessage(password));
        } while (!login.checkPasswordComplexity(password));
        login.setPassword(password);

        String cell;
        do {
            System.out.print("Enter your cell phone number (e.g. +27838968976): ");
            cell = in.nextLine();
            System.out.println(login.cellPhoneMessage(cell));
        } while (!login.checkCellPhoneNumber(cell));
        login.setCellPhone(cell);

        System.out.println(login.registerUser());

        System.out.println("\n=== Login ===");
        boolean loggedIn;
        do {
            System.out.print("Username: ");
            String u = in.nextLine();
            System.out.print("Password: ");
            String p = in.nextLine();
            loggedIn = login.loginUser(u, p);
            System.out.println(login.returnLoginStatus(loggedIn));
        } while (!loggedIn);

        in.close();
    }
}
