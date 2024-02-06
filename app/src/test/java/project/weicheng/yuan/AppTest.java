package project.weicheng.yuan;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    public void testGetGreeting() {
        App app = new App();
        assertEquals("Hello World!", app.getGreeting(), "App should return 'Hello World!'");
    }
}

