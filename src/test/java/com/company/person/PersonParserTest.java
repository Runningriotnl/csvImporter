package com.company.person;

import com.company.organization.Organization;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void personParserTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = null;
        personParser.parseToPerson(rawPerson);
    }

    @Test
    public void inputMatchesFieldsTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "X,,,,,,Y,Z,,1,,,,,,,,,,,,,,,,";
        Organization org = new Organization("Y");
        Person person = personParser.parseToPerson(rawPerson);
        assertEquals("Username should match.", "X", person.getUserName());
        assertEquals("Email should match.", "Z", person.getEmail());
        assertEquals("Organization should match.", org, person.getOrg());
        assertEquals("Extension Number should match.", (Integer) 1, person.getExtensionNumber());
    }

    @Test(expected = PersonParserException.class)
    public void emptyStringTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "";
        Person person = personParser.parseToPerson(rawPerson);
    }

    @Test
    public void onlyUserNameTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "X,,,,,,,,,,,,,,,,,,,,,,,,,";
        Person person = personParser.parseToPerson(rawPerson);
        assertEquals("Username should match.", "X", person.getUserName());
    }

    @Test
    public void onlyUserNameAndExtensionTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "X,,,,,,,,,1,,,,,,,,,,,,,,,,";
        Person person = personParser.parseToPerson(rawPerson);
        assertEquals("Username should match.", "X", person.getUserName());
        assertEquals("Extension Number should match.", (Integer) 1, person.getExtensionNumber());
    }

    @Test(expected = PersonParserException.class)
    public void extensionIsNotAnIntTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "X,,,,,,Y,Z,,A,,,,,,,,,,,,,,,,";
        Person person = personParser.parseToPerson(rawPerson);
    }

    @Test
    public void userNameHasDigitFirstTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "1X,,,,,,Y,Z,,1,,,,,,,,,,,,,,,,";
        Person person = personParser.parseToPerson(rawPerson);
        assertEquals("Username should match.", "1X", person.getUserName());
    }

    @Test
    public void userNameIsOnlyDigitsTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "1,,,,,,Y,Z,,1,,,,,,,,,,,,,,,,";
        Person person = personParser.parseToPerson(rawPerson);
        assertEquals("Username should match.", "1", person.getUserName());
    }

    @Test
    public void fieldContainQuotesTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "X,,,,,,\"Y\",Z,,1,,,,,,,,,,,,,,,,";
        Organization org = new Organization("\"Y\"");
        Person person = personParser.parseToPerson(rawPerson);
        assertEquals("Organization should match.", org, person.getOrg());
    }

    @Test(expected = PersonParserException.class)
    public void commaAddedBeforeExtensionInFieldsTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "X,,,,,,,Y,Z,,1,,,,,,,,,,,,,,,,";
        Person person = personParser.parseToPerson(rawPerson);
    }

    @Test(expected = PersonParserException.class)
    public void commaAddedAfterExtensionInFieldsTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "X,,,,,,Y,Z,,1,,,,,,,,,,,,,,,,,";
        Person person = personParser.parseToPerson(rawPerson);
    }


}
