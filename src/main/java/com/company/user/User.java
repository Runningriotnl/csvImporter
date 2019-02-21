package com.company.user;

import com.company.Addressable;
import com.company.person.Person;

public class User extends Addressable {
    private String userName;
    private String password;
    private boolean active;
    private Person person;
    private String commonName;


    public User(String userName, String password, Person person) {
        super("UserProfile");

        this.userName = userName;
        this.password = password;
        this.person = person;
        this.commonName = userName;
        this.active = true;
    }
}
