package com.company;

import com.company.JsonBuilder.JsonBuilderPerson;
import com.company.organization.Organization;
import com.company.organization.OrganizationParser;
import com.company.person.Person;
import com.company.person.PersonParser;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

public class MakeHttpRequestTest {
    Logger logger = LoggerFactory.getLogger(MakeHttpRequestTest.class);

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
    public void jsonBuilderPersonTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "X,,A,,B,,Y,Z,,1,,,,,,,,,,,,,,,,";
        Person person = personParser.parseToPerson(rawPerson);
        JsonBuilderPerson jsonBuilderPerson = new JsonBuilderPerson(person);
        String jsonPerson = jsonBuilderPerson.createPersonJson();
        logger.info(jsonPerson);
    }
}
