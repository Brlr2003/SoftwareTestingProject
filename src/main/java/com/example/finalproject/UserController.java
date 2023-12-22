package com.example.finalproject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class UserController implements Serializable {
    private ArrayList<User> users;
    public UserController(){
        users = new ArrayList<>();
    }
    public UserController(User x){
        users = new ArrayList<>();
        users.add(x);
    }
    public ArrayList<User> getUsers(){
        return users;
    }
    public void setUsers(ArrayList<User> users){
        this.users = users;
    }
    public boolean checker(String firstname, String lastname, LocalDate birthdate, String email, String phoneNumber, String accessLevel, String username, String password, String verifyPassword){
        if(firstname.equals("") || lastname.equals("") || birthdate.equals("") || email.equals("") || phoneNumber.equals("") || accessLevel.equals("")){
            return false;
        }
        else if(password.equals(verifyPassword)){
//            if(firstname.matches("[A-Z]*") &&
//            lastname.matches("[A-Z]*") &&
//            phoneNumber.matches("\\d*")){
//
//            }
            User user = new User(firstname, lastname, birthdate, email, phoneNumber, accessLevel, username, password);
            users.add(user);
            return true;
        }
        return false;
    }

    public void printUsers(){
        System.out.println("Hello World!");       ;
        for(int i=0; i<users.size(); i++){
            System.out.println(users.get(i));
        }
    }

}
