package com.company;

import com.company.organization.Organization;
import com.company.person.Person;

import java.util.List;

public class Writer {

    public void writePerson(Person p) {


        System.out.println("Person with name " + p.getFirstName() + " " + p.getLastName() +
                           " has username: " + p.getUserName() + " who works for " + p.getOrg() +
                           " and gets extension number: " + p.getExtensionNumber());

    }

    public void writeOrganization(Organization o) {


        System.out.println("Organization " + o.getName() + " is reachable on email at: " +
                           o.getEmail() + " and through phone by: " + o.getPhoneNumber() + ".");
    }

    public void write(List list) {
        for(Object o : list) {
            if(o instanceof Person) {
                writePerson((Person) o);
            }
            else if(o instanceof Organization) {
                writeOrganization((Organization) o);
            }

        }
    }

}
