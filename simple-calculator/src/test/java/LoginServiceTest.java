import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {


    LoginService loginService=new LoginService();
//@BeforeAll
//        static void registerLogin(){


    @Test
    void login_loginOK_passOK_shouldReturnSuccess() {

        Assertions.assertEquals("Sukces", loginService.login("login1", "Test12334"));
    }
    @Test
    void register_loginOK_passOK_shouldReturnSuccess() {

        Assertions.assertEquals("Sukces", loginService.register("login", "Test12334"));
    }

    @Test
    void registerNew_passOK_shouldNewUserBeAdded() {
        loginService.register("login", "Test12334");
        Assertions.assertEquals("Użytkownik istnieje", loginService.register("login", "Test12334"));
    }
}