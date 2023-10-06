package test.WEareSocial;

import com.telerikacademy.infrastructure.selenium.api.RestApiCreateUser;
import org.junit.jupiter.api.Test;

public class RESTAssuredTests {
    private final RestApiCreateUser apiUser = new RestApiCreateUser();
    @Test
    public void testCreateUser() {
        apiUser.createUser();

        //tests

    }
    @Test
    public void testAuthenticateUser() {
        apiUser.authenticateUser();

        //tests

    }

}
