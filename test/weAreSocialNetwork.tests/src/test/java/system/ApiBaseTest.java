package system;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;

public class ApiBaseTest {
    protected Gson gson;

    @BeforeEach
    public void beforeEach(){
        gson = new Gson();
    }
}
