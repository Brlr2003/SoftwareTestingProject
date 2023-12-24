package com.example.finalproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest {
    private UserController userController;

    @BeforeEach
    public void setUp() {
        userController = new UserController();
    }

    @Test
    public void testGetUsersInitiallyEmpty() {
        assertTrue(userController.getUsers().isEmpty());
    }

    @Test
    public void testAddSingleUser() {
        LocalDate birthdate = LocalDate.of(1990, 5, 15);
        assertTrue(userController.checker("John", "Doe", birthdate, "john@example.com", "1234567890", "Admin", "johndoe", "password", "password"));
        assertEquals(1, userController.getUsers().size());
    }

    @Test
    public void testCheckerWithEmptyFields() {
        assertFalse(userController.checker("", "", null, "", "", "", "", "", ""));
        assertTrue(userController.getUsers().isEmpty());
    }

    @Test
    public void testAddMultipleUsers() {
        LocalDate birthdate1 = LocalDate.of(1985, 3, 20);
        LocalDate birthdate2 = LocalDate.of(1995, 10, 8);
        assertTrue(userController.checker("Alice", "Smith", birthdate1, "alice@example.com", "9876543210", "User", "alice", "password", "password"));
        assertTrue(userController.checker("Bob", "Johnson", birthdate2, "bob@example.com", "1231231234", "Admin", "bob", "pass123", "pass123"));
        assertEquals(2, userController.getUsers().size());
    }

    @Test
    public void testCheckerWithMismatchedPasswords() {
        assertFalse(userController.checker("Eva", "Green", LocalDate.now(), "eva@example.com", "9876543210", "User", "eva", "pass1", "pass2"));
        assertTrue(userController.getUsers().isEmpty());
    }

    @Test
    public void testPrintUsers() {
        // Adding a user
        LocalDate birthdate = LocalDate.of(1988, 12, 10);
        userController.checker("Sarah", "Adams", birthdate, "sarah@example.com", "7777777777", "User", "sarah", "pass123", "pass123");

        // Redirecting System.out to capture printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Printing users
        userController.printUsers();

        // Verifying the printed output contains certain information
        String printedOutput = outputStream.toString();
        assertTrue(printedOutput.contains("Hello World!")); // Check for the "Hello World!" message
        assertTrue(printedOutput.contains("Sarah")); // Check if user information is present (e.g., Sarah's name)
        assertTrue(printedOutput.contains("sarah@example.com")); // Check for the email address
        assertTrue(printedOutput.contains("7777777777")); // Check for the phone number
        assertTrue(printedOutput.contains("User")); // Check for the access level
        assertTrue(printedOutput.contains("pass123")); // Check for the password (or its hash, etc.)
    }

    @Test
    public void testSetAndGetUsers() {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User("Alex", "Smith", LocalDate.of(1987, 6, 5), "alex@example.com", "5555555555", "Admin", "alex", "password"));
        userList.add(new User("Emily", "Johnson", LocalDate.of(1992, 9, 18), "emily@example.com", "9999999999", "User", "emily", "pass123"));

        userController.setUsers(userList);

        assertEquals(userList, userController.getUsers());
    }

}
