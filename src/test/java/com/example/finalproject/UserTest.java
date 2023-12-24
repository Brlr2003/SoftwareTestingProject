package com.example.finalproject;

import com.example.finalproject.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("John", "Doe", LocalDate.of(1990, 5, 15), "john@example.com", "1234567890", "Admin", "johndoe", "password");
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("John", user.getFirstname());
        assertEquals("Doe", user.getLastname());
        assertEquals(LocalDate.of(1990, 5, 15), user.getBirthdate());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("1234567890", user.getPhoneNumber());
        assertEquals("Admin", user.getAccessLevel());
        assertEquals("johndoe", user.getUsername());
        assertEquals("password", user.getPassword());

        user.setFirstname("Jane");
        assertEquals("Jane", user.getFirstname());

        user.setLastname("Smith");
        assertEquals("Smith", user.getLastname());

        LocalDate newBirthdate = LocalDate.of(1988, 3, 25);
        user.setBirthdate(newBirthdate);
        assertEquals(newBirthdate, user.getBirthdate());

        user.setEmail("jane@example.com");
        assertEquals("jane@example.com", user.getEmail());

        user.setPhoneNumber("9876543210");
        assertEquals("9876543210", user.getPhoneNumber());

        user.setAccessLevel("User");
        assertEquals("User", user.getAccessLevel());

        user.setUsername("janesmith");
        assertEquals("janesmith", user.getUsername());

        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    public void testTotalBills() {
        user.setTotalBills("100.50");
        assertEquals(100.50, user.getTotalBills());

        user.setTotalBills(150.75);
        assertEquals(150.75, user.getTotalBills());
    }

    @Test
    public void testBooksSold() {
        user.setBooksSold(50);
        assertEquals(50, user.getBooksSold());
    }

    @Test
    public void testTotalProfitAndDates() {
        ArrayList<Double> profit = new ArrayList<>();
        profit.add(50.25);
        profit.add(75.50);

        ArrayList<LocalDate> dates = new ArrayList<>();
        dates.add(LocalDate.of(2023, 5, 10));
        dates.add(LocalDate.of(2023, 6, 15));

        user.setTotalProfit(profit);
        user.setTotalBills(125.75);
        user.setBooksSold(30);
        user.setFilteredProfit(25.00);
        user.addToDates(LocalDate.of(2023, 4, 20));

        assertEquals(profit, user.getProfitArray());
        assertEquals(125.75, user.getTotalBills());
        assertEquals(30, user.getBooksSold());
        assertEquals(25.00, user.getFilteredProfit());
        assertEquals(1, user.getDatesArray().size());
    }

    @Test
    public void testAddToTotalProfitAndDates() {
        user.addToTotalProfit(100.50);
        user.addToTotalProfit(150.75);

        ArrayList<Double> expectedProfit = new ArrayList<>();
        expectedProfit.add(100.50);
        expectedProfit.add(150.75);

        assertEquals(expectedProfit, user.getProfitArray());
        assertEquals(2, user.getProfitArray().size());

        user.addToDates(LocalDate.of(2023, 7, 20));
        user.addToDates(LocalDate.of(2023, 8, 15));

        ArrayList<LocalDate> expectedDates = new ArrayList<>();
        expectedDates.add(LocalDate.of(2023, 7, 20));
        expectedDates.add(LocalDate.of(2023, 8, 15));

        assertEquals(expectedDates, user.getDatesArray());
        assertEquals(2, user.getDatesArray().size());
    }

    @Test
    public void testToStringMethod() {
        String expectedString = "First Name: John\n" +
                "Last Name: Doe\n" +
                "Birthdate: 1990-05-15\n" +
                "Email: john@example.com\n" +
                "Phone Number: 1234567890\n" +
                "Access Level: Admin\n" +
                "Username: johndoe\n" +
                "Password: password\n" +
                "Total Bills: 0.0\n" +
                "Total Books Sold: 0\n\n";

        assertEquals(expectedString, user.toString());
    }

    @Test
    public void testFilteredProfit() {
        user.setFilteredProfit(50.75);
        assertEquals(50.75, user.getFilteredProfit());

        user.setFilteredProfit(75.25);
        assertEquals(75.25, user.getFilteredProfit());
    }
}
