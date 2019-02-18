package com.company;

import com.company.JsonBuilder.JsonBuilderPerson;
import com.company.organization.Organization;
import com.company.organization.OrganizationParser;
import com.company.person.Person;
import com.company.person.PersonParser;
import com.google.gson.Gson;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

public class SendHttpRequestTest {
    Logger logger = LoggerFactory.getLogger(SendHttpRequestTest.class);

    @Test
    public void personJsonStringTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "X,,A,,B,,Y,Z,,1,,,,,,,,,,,,,,,,";
        Person person = personParser.parseToPerson(rawPerson);
        String personAsJson = person.toJsonObject();
        assertEquals("Json string should match.", "{\"objectType\":\"Person\", \"commonName\":\"B\"}", personAsJson);
    }

    @Test
    public void objectJsonStringTest() {
        OrganizationParser organizationParser = new OrganizationParser();
        String rawOrg = "X,,,,,,,,,,,,,,,,,,,,,,,,";
        Organization organization = organizationParser.parseToOrg(rawOrg);
        String orgAsJson = organization.toJsonObject();
        assertEquals("Json string should match.", "{\"objectType\":\"Organisation\", \"commonName\":\"X\"}", orgAsJson);
    }

    @Test
    public void objectGsonStringTest() {
        OrganizationParser organizationParser = new OrganizationParser();
        String rawOrg = "X,,,,,,,,,,,,,,,,,,,,,,,,";
        Organization organization = organizationParser.parseToOrg(rawOrg);
        String orgAsJson = new Gson().toJson(organization);
        assertEquals("Json string should match.", "{\"name\":\"X\",\"objectType\":\"Organisation\"}", orgAsJson);
    }

    @Test
    public void jsonBuilderPersonTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "X,,A,,B,,Y,Z,,1,,,,,,,,,,,,,,,,";
        Person person = personParser.parseToPerson(rawPerson);
        JsonBuilderPerson jsonBuilderPerson = new JsonBuilderPerson(person);
        String jsonPerson = jsonBuilderPerson.createPersonJson();
        logger.info(jsonPerson);
    }
}
