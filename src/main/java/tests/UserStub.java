package tests;


import java.util.ArrayList;

import application.bookstore.models.Role;
import application.bookstore.models.User;


public class UserStub extends User {

	private static final ArrayList<User> users = new ArrayList<>();

	
	 public static ArrayList<User> getUsers() {
		 users.add(new User("admin","admin",Role.ADMIN));
		 users.add(new User("manager","1234",Role.MANAGER));
		 users.add(new User("librarian1","aaa5",Role.LIBRARIAN));
		 return users;
	 }
	 
	 public UserStub(String username, String password, Role role) {
		 super(username, password, role);
	 }
	 
	 @Override
	 public boolean saveInFile() {
	        users.add(this);
	        return true;
	    }


	    @Override
	    public boolean deleteFromFile() {
	        try {
	        	users.remove(this);
	        	return true;
	        } catch (Exception exception) {
	            return false;
	        }
	    }
	    
	
}
