package com.company;

import java.util.List;

public class Writer {

    public void writePerson(List<Person> personList) {

        for (Person p : personList) {
            System.out.println("Person with name " + p.getFirstName() + " " + p.getLastName() +
                               " has username: " + p.getUserName() + " who works for " + p.getOrg() +
                               " and gets extension number: " + p.getExtensionNumber());
        }
    }

    public void writeOrganization(List<Organization> orgList) {

        for (Organization o : orgList) {
            System.out.println("Organization " + o.getName() + " is reachable on email at: " +
                               o.getEmail() + " and through phone by: " + o.getPhoneNumber() + ".");
        }
    }

}
