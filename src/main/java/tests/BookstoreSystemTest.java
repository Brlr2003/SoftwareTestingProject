package tests;

import application.bookstore.Main;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class BookstoreSystemTest {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final InputStream originalIn = System.in;

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testBookstoreSystem() {
        // Redirect System.out for capturing console output
        // System.setOut(new PrintStream(outContent));

        // Simulate User interactions or commands
        // Example:
        // 1. User enters "login" Command
        provideInput("user123\npassword123\n");
        Main.main(null);

        //capture the console output
        String loginOutput = outContent.toString().trim();

        // perform the assertions based on expected output or system state
//        assertTrue(loginOutput.contains("Welcome, user123!"));

        //reset System.out to the original output stream
        System.setOut(originalOut);
        outContent.reset();

        // Example:
        // 2. user enters "search" command with a search query
        provideInput("search\nTitle1\n");
        Main.main(null);

        // Capture the Console output
        String searchOutput = outContent.toString().trim();

        // Perform assertions based on expected output or system state
        assertTrue(searchOutput.contains("Search results for 'Title1'"));

        // Reset System.out to the original output Stream
        System.setOut(originalOut);
        outContent.reset();

        // Example:
        // 3. User enters "purchase" command for a Book
        provideInput("purchase\n1\n5\n");
        Main.main(null);

        // Capture the console output
        String purchaseOutput = outContent.toString().trim();

        // Perform assertions based on expected output or sytem state
        assertTrue(purchaseOutput.contains("Purchase successful"));

        // Reset System.out to the original output stream
        System.setOut(originalOut);
    }

    private void provideInput(String data) {
        ByteArrayInputStream inContent = new ByteArrayInputStream(data.getBytes());
        System.setIn(inContent);
    }


}
