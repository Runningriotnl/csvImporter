package com.company.person;

import com.company.Addressable;
import com.company.XelionObjects.DeliveryAddress;
import com.company.XelionObjects.Employee;
import com.company.XelionObjects.TelecomAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person extends Addressable {
    private String userName;
    private String givenName;
    private String familyName;
    private List<Employee> employments;
    private String gender;
    private List<DeliveryAddress> addresses;
    private List<TelecomAddress> telecomAddresses = new ArrayList<>();

    public Person(String userName, String givenName, String familyName, List<Employee> employments, List<DeliveryAddress> addresses, String gender, TelecomAddress email, TelecomAddress phoneNumber) {
        super("Person");

        if(userName == null) {
            throw new IllegalArgumentException("Username is required");
        }

        this.userName = userName;
        this.givenName = givenName;
        this.familyName = familyName;
        this.employments = employments;
        this.addresses = addresses;
        this.gender = gender;
        if(email != null){
            telecomAddresses.add(email);
        }
        if(phoneNumber != null){
            telecomAddresses.add(phoneNumber);
        }

    }

    public String getUserName() {
        return userName;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public List<Employee> getEmployments() {
        return employments;
    }
    public String getGender() {
        return gender;
    }

}
