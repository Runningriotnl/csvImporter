package com.company;

import com.company.organization.Organization;
import com.company.person.Person;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Writer {
    Logger logger = LoggerFactory.getLogger(Writer.class);

    public void writePerson(Person p) {

        String personName = p.getLastName();
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
        String orgAsJson = new Gson().toJson(o);

        logger.info("Payload send as: " + orgAsJson);

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
