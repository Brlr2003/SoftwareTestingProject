package com.example.finalproject;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginPageTest {
    private LoginPage loginPage;
    private Button mockButton;

    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage();
        mockButton = new Button();
    }

    @Test
    public void testCheckCredentialsWithAdminRole() {
        loginPage.username = "admin";
        loginPage.password = "admin";

        loginPage.checkCredentials(mockButton);

        // Assert transition to administrator interface
        Stage currentStage = (Stage) mockButton.getScene().getWindow();
        assertEquals("Administrator Interface", currentStage.getTitle());
    }

    @Test
    public void testCheckCredentialsWithLibrarianRole() {
        loginPage.username = "librarian_username";
        loginPage.password = "librarian_password";

        loginPage.checkCredentials(mockButton);

        Stage currentStage = (Stage) mockButton.getScene().getWindow();
        assertEquals("Librarian Interface", currentStage.getTitle());
    }

    @Test
    public void testCheckCredentialsWithManagerRole() {
        loginPage.username = "manager_username";
        loginPage.password = "manager_password";

        loginPage.checkCredentials(mockButton);

        Stage currentStage = (Stage) mockButton.getScene().getWindow();
        assertEquals("Manager Interface", currentStage.getTitle());
    }

    @Test
    public void testCheckCredentialsWithInvalidCredentials() {
        loginPage.username = "invalid_username";
        loginPage.password = "invalid_password";
        loginPage.checkCredentials(mockButton);

    }
}

