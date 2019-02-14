package com.company.JsonBuilder;

import com.company.MakeHttpRequest;
import com.company.person.Person;

public class JsonBuilderPerson {
    String personAsJson;
    Person person;
    String organizationOID;

    public JsonBuilderPerson(Person person) {
        this.person = person;
    }

    public void createPersonJson(String personAsJson) {

        personAsJson = "";

    }

    public String createOrganisationBlock() {
        if(!"".equals(person.getOrg().getName())) {
            MakeHttpRequest makeHttpRequest = new MakeHttpRequest("http://10.78.40.157");
            organizationOID = makeHttpRequest.getOrganizationOID(person.getOrg().getName());

        }
        return organizationOID;
    }

}
