package com.company;

import com.company.organization.Organization;
import com.company.person.Person;

import java.util.List;

public class Writer {

    public void writePerson(Person p) {

        String personName;
        // Only want to add space between names if both are provided
        if(!p.getFirstName().equals("") && !p.getLastName().equals("")){
            personName = p.getFirstName()  + " " + p.getLastName();
        } else {
            //If on of the names is provided the format will be okay, if none of the names is provided will become ""
            personName = p.getFirstName() + p.getLastName();
        }
        String personUserName = p.getUserName();
        String personOrgName = p.getOrg().getName();
        String personExtension = (p.getExtensionNumber() == null) ? "" : p.getExtensionNumber().toString();
        String [] personDescription = { personName, personUserName, personOrgName, personExtension };

        for(int i = 0; i < personDescription.length; i++) {
            if( personDescription[i].equals("")) {
                personDescription[i] = "not provided";
            }
        }


        System.out.println("Person with name " + personDescription[0] +
                           " has username: " + personDescription[1] + " who works for " + personDescription[2] +
                           " and gets extension number: " + personDescription[3]);

    }

    public void writeOrganization(Organization o) {

        String organizationName = o.getName();
        String organizationEmail = o.getEmail();
        String organizationPhoneNumber = o.getPhoneNumber();
        String[] organizationDescrption = { organizationName, organizationEmail, organizationPhoneNumber };

        for(int i = 0; i < organizationDescrption.length; i++) {
            if( organizationDescrption[i].equals("")) {
                organizationDescrption[i] = "not provided";
            }
        }



        System.out.println("Organization " + organizationDescrption[0] + " is reachable on email at: " +
                organizationDescrption[1] + " and through phone by: " + organizationDescrption[2] + ".");
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
