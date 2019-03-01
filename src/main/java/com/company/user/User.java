package com.company.user;

import com.company.Addressable;
import com.company.person.Person;

public class User extends Addressable {
    private String userName;
    private String password;
    private boolean active;
    private Person person;
    private String commonName;
    private String extensionNumber;
    private String phoneLineOID;
    private boolean userLicense;


    public User(String userName, String password, String extensionNumber, Person person) {
        super("UserProfile");

        this.userName = userName;
        this.password = password;
        this.extensionNumber = extensionNumber;
        this.person = person;
        this.commonName = userName;
        this.active = true;
        this.userLicense = true;
        this.phoneLineOID = null;
    }

    public String getExtensionNumber() {
        return extensionNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }

    public Person getPerson() {
        return person;
    }

    public String getCommonName() {
        return commonName;
    }

    public String getPhoneLineOID() {
        return phoneLineOID;
    }

    public boolean isUserLicense() {
        return userLicense;
    }
}
