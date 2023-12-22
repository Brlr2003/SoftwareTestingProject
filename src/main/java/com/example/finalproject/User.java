package com.example.finalproject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class User implements Serializable {
    private static final long SerialVersionUID = 10l;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String email;
    private String phoneNumber;
    private String accessLevel;
    private double totalBills;
    private int booksSold;
    private ArrayList<Double> totalProfit;
    private ArrayList<LocalDate> dates;
    private double filteredProfit = 0;
    private String username;
    private String password;

    public ArrayList<Double> getProfitArray() {
        return totalProfit;
    }

    public ArrayList<LocalDate> getDatesArray() {
        return dates;
    }



    public User(String firstname, String lastname, LocalDate birthdate, String email, String phoneNumber, String accessLevel, String username, String password){
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.accessLevel = accessLevel;
        this.username = username;
        this.password = password;
        totalProfit = new ArrayList<>();
        dates = new ArrayList<>();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
    public double getTotalBills() {
        return totalBills;
    }

    public void setTotalBills(String totalBills) {
        this.totalBills = Double.parseDouble(totalBills);
    }
    public void setTotalBills(double totalBills) {
        this.totalBills = totalBills;
    }

    public int getBooksSold() {
        return booksSold;
    }

    public void setBooksSold(int booksSold) {
        this.booksSold = booksSold;
    }

    public void addToTotalProfit(double value) {
        totalProfit.add(value);
    }

    public void setTotalProfit(ArrayList<Double> totalProfit) {
        this.totalProfit = totalProfit;
    }

    public void addToDates(LocalDate date) {
        dates.add(date);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getFilteredProfit() {
        return filteredProfit;
    }

    public void setFilteredProfit(double filteredProfit) {
        this.filteredProfit = filteredProfit;
    }

    public String toString() {
        return "First Name: " + firstname +
                "\nLast Name: " + lastname +
                "\nBirthdate: " + birthdate +
                "\nEmail: " + email +
                "\nPhone Number: " + phoneNumber +
                "\nAccess Level: " + accessLevel +
                "\nUsername: " + username +
                "\nPassword: " + password +
                "\nTotal Bills: " + totalBills +
                "\nTotal Books Sold: " + booksSold +
                "\n\n";
    }



}
