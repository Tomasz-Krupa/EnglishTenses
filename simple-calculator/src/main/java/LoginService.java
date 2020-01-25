import java.util.HashMap;
import java.util.Map;

public class LoginService {

    private static final String EMPTY_LOGIN_OR_PASSWORD = "Nie podano hasła lub loginu";
    private static final String SUCCESS = "Sukces";
    private static final String USER_NOT_FOUND = "Nie znaleziono użytkownika";
    private static final String WRONG_PASSWORD = "Złe hasło";
    private static final String USER_EXISTS = "Użytkownik istnieje";
    private static final String PASSWORD_IS_NOT_ALLOWED = "Podane hasło nie jest dozwolone";
    private Map<String, String> usersMap = new HashMap<>();

    public LoginService() {
        usersMap.put("login1", "Test12334");
        usersMap.put("login2", "Test12334");
        usersMap.put("adam", "adam12334");
        usersMap.put("ewa", "ewa12334");
    }

    public String login(String login, String pass) {

        if (login == null || pass == null || login.isEmpty() || pass.isEmpty())
            return EMPTY_LOGIN_OR_PASSWORD;

        if (!usersMap.containsKey(login))
            return USER_NOT_FOUND;

        if (usersMap.get(login) == null || !usersMap.get(login).equals(pass))
            return WRONG_PASSWORD;
        return SUCCESS;
    }

    public String register(String login, String pass) {

        if (login == null || pass == null || login.isEmpty() || pass.isEmpty())
            return EMPTY_LOGIN_OR_PASSWORD;

        if (usersMap.containsKey(login))
            return USER_EXISTS;

        if (!pass.matches("(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]{8,})"))
            return PASSWORD_IS_NOT_ALLOWED;

        usersMap.put(login,pass);
        return SUCCESS;
    }

}
