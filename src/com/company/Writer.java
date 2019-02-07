package com.company;

import java.util.List;

public class Writer {

    public void write(List<Person> personList) {

        for (Person p : personList) {
            System.out.println("Person with name " + p.getFirstName() + " " + p.getLastName() +
                               " has username: " + p.getUserName() + " who works for " + p.getOrg() +
                               " and gets extension number: " + p.getExtensionNumber());
        }
    }

}
