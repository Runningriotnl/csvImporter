package com.company.JsonBuilder;

import com.company.MakeHttpRequest;
import com.company.person.Person;

public class JsonBuilderPerson {
    private String personAsJson;
    private Person person;
    private String organizationOID;

    public JsonBuilderPerson(Person person) {
        this.person = person;
    }

    public void createPersonJson(String personAsJson) {

        personAsJson = "";

    }

    public String createOrganisationBlock() {
        String employment;
        String personOrgName = person.getOrg().getName();
        if(!"".equals(personOrgName)) {
            MakeHttpRequest makeHttpRequest = new MakeHttpRequest("http://10.78.40.157");
            organizationOID = makeHttpRequest.getOrganizationOID(person.getOrg().getName());
            employment = "\"employments\": [" +
                    "{\"commonName\":\"" + personOrgName + "\"," +
                    "\"organisation\":{" +
                    "\"commonName\":\"" + personOrgName + "\"," +
                    "\"oid\":" + organizationOID + "\"," +
                    "\"objectType\":\"Organisation\"}," +
                    "\"telecomAddresses\":" + createTelecomAddressesBlock() +
                    "}]";
        } else {
            employment = "\"employments\": []";
        }

        return employment;
    }

    public String  createTelecomAddressesBlock() {
        String telecomAddresses;
        String personOrgEmail = person.getEmail();
        String personOrgTel = person.getMobileNumber();

        if(!"".equals(personOrgEmail) && !"".equals(personOrgTel)) {
            telecomAddresses = "[{" +
                               "\"address\":\"" + personOrgEmail + "\"," +
                               "\"addressType\":\"Email\"," +
                               "\"objectType\":\"TelecomAddress\"" +
                               "},{" +
                               "\"address\":\"" + personOrgTel + "\"," +
                               "\"addressType\":\"Telephone\"," +
                               "\"objectType\":\"TelecomAddress\"" +
                               "}]";
        } else if(!"".equals(personOrgEmail)) {
            telecomAddresses = "[{" +
                               "\"address\":\"" + personOrgEmail + "\"," +
                               "\"addressType\":\"Email\"," +
                               "\"objectType\":\"TelecomAddress\"" +
                               "}]";
        } else if(!"".equals(personOrgTel)) {
            telecomAddresses = "[{" +
                               "\"address\":\"" + personOrgTel + "\"," +
                               "\"addressType\":\"Telephone\"," +
                               "\"objectType\":\"TelecomAddress\"" +
                               "}]";
        } else {
            telecomAddresses = "[]";
        }
        return telecomAddresses;
    }

}
