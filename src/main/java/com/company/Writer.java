package com.company;

import com.company.organization.Organization;
import com.company.person.Person;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Writer {
    private Logger logger = LoggerFactory.getLogger(Writer.class);

    private  void writePerson(Person person) {
        String personAsJson = new Gson().toJson(person);
        
        logger.info("Payload send as: " + personAsJson);

    }

    private void writeOrganization(Organization organization) {
        String orgAsJson = new Gson().toJson(organization);

        logger.info("Payload send as: " + orgAsJson);

    }

    public void write(List list) {
        for(Object object : list) {
            if(object instanceof Person) {
                writePerson((Person) object);
            }
            else if(object instanceof Organization) {
                writeOrganization((Organization) object);
            }

        }
    }

}
