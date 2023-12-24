package com.example.finalproject;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserControllerTest {
    private UserController userController;

    @Before
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
    public void testAddMultipleUsers() {
        LocalDate birthdate1 = LocalDate.of(1985, 3, 20);
        LocalDate birthdate2 = LocalDate.of(1995, 10, 8);
        assertTrue(userController.checker("Alice", "Smith", birthdate1, "alice@example.com", "9876543210", "User", "alice", "password", "password"));
        assertTrue(userController.checker("Bob", "Johnson", birthdate2, "bob@example.com", "1231231234", "Admin", "bob", "pass123", "pass123"));
        assertEquals(2, userController.getUsers().size());
    }

    @Test
    public void testCheckerWithEmptyFields() {
        assertFalse(userController.checker("", "", null, "", "", "", "", "", ""));
        assertTrue(userController.getUsers().isEmpty());
    }

    @Test
    public void testCheckerWithMismatchedPasswords() {
        assertFalse(userController.checker("Eva", "Green", LocalDate.now(), "eva@example.com", "9876543210", "User", "eva", "pass1", "pass2"));
        assertTrue(userController.getUsers().isEmpty());
    }

    @Test
    public void testCheckerWithValidFieldsButEmptyPassword() {
        LocalDate birthdate = LocalDate.of(1993, 8, 25);
        assertFalse(userController.checker("Mark", "Johnson", birthdate, "mark@example.com", "5555555555", "Admin", "mark", "", ""));
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

        // Verifying the printed output
        assertEquals("Hello World!\nUser{firstname='Sarah', lastname='Adams', birthdate=1988-12-10, email='sarah@example.com', phoneNumber='7777777777', accessLevel='User', username='sarah', password='pass123'}\n", outputStream.toString());
    }
}
