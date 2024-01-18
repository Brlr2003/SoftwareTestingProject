package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


import application.bookstore.models.Role;
import application.bookstore.models.User;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertFalse;


class TestUser {

    @Test
    public void testUserSetter_setsProperly1()  {
        User user = new User();
        user.setUsername("omar");
        assertEquals(user.getUsername(), "omar");
    }

    @Test
    public void testUserSetter_setsProperly2()  {
        User user = new User();
        user.setUsername("1234");
        assertNotEquals(user.getUsername(), "omar");
    }

    @Test
    public void testUserGetter_getsProperly1()  {
        User user = new User("admin","123",Role.ADMIN);
        assertEquals(user.getUsername(), "admin");
    }

    @Test
    public void testUserGetter_getsProperly2()  {
        User user = new User();
        user.setUsername("admin");
        assertEquals(user.getUsername(), "admin");
    }


    @Test
    public void test_setRole() {
        User user = new User();
        user.setRole(Role.ADMIN);
        assertEquals(user.getRole(), Role.ADMIN);
    }

    @Test
    public void test_getRole() {
        User user = new User();
        user.setRole(Role.ADMIN);
        assertNotEquals(user.getRole(), Role.LIBRARIAN);
    }

    @Test
    public void test_user_constructor() {
        User user = new User("user1","user123",Role.MANAGER);
        assertEquals("user1",user.getUsername());
        assertEquals("user123",user.getPassword());
        assertEquals(Role.MANAGER,user.getRole());

    }

    @Test
    public void test_userDoesntExists() {
        UserStub userStub = new UserStub("omar","123",Role.ADMIN);
        Assertions.assertFalse(userStub.ifusernameExists());

    }

    @Test
    public void test_userExists() {
        UserStub userStub = new UserStub("admin","admin",Role.ADMIN);
        assertTrue(userStub.ifusernameExists());
    }


    @Test
    public void test_getUserNull() {
        UserStub potentialUser = new UserStub("admin3","admin",Role.ADMIN);
        User user = new User();
        User checkUser = user.getUser(potentialUser);
        assertNull(checkUser);
    }


    @Test
    public void test_getSearchResult() {
        ArrayList<User> searchResults;
        User user = new User();
        String searchText = "ad";
        searchResults = user.getSearchResults(searchText);
        assertNotNull(searchResults);
    }

    @Test
    public void test_get1SearchResult() {
        ArrayList<User> searchResults;
        User user = new User();
        String searchText = "ad";
        searchResults = user.getSearchResults(searchText);
        assertEquals(1,searchResults.size());
    }


    @Test
    public void test_isUsernameAndPassValid() {
        User user = new User("user12","oopjava",Role.LIBRARIAN);
        assertTrue(user.isValid());
    }

    @Test
    public void test_isUsernameAndNotPassValid() {
        User user = new User("user12"," ",Role.ADMIN);
        Assertions.assertFalse(user.isValid());
    }

    @Test
    public void test_isNotUsernameAndPassValid() {
        User user = new User(" ","oopjava123",Role.LIBRARIAN);
        Assertions.assertFalse(user.isValid());
    }

    @Test
    public void test_isNotUsernameAndNotPassValid() {
        User user = new User(" "," ",Role.ADMIN);
        Assertions.assertFalse(user.isValid());
    }




}
