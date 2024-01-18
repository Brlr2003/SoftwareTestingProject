package tests;
import application.bookstore.models.Role;
import application.bookstore.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

public class UserIntegrationTest {

    private final String testFilePath = "data/test_users.ser";
    private final File testFile = new File(testFilePath);

    @BeforeEach
    public void setUp() {
        // Clean up the test file before each test
        if (testFile.exists()) {
            assertTrue(testFile.delete());
        }
    }

    @AfterEach
    public void tearDown() {
        // Clean up the test file after each test
        if (testFile.exists()) {
            assertTrue(testFile.delete());
        }
    }
    @Test
    public void testUserIntegration() {


        // Create a User instance
        User user = new User("user12"," ", Role.ADMIN);

        assertNotNull(user);



    }


}
