package com.company.person;

import com.company.organization.Organization;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

public class PersonParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void personParserTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = null;
        personParser.parseToPerson(rawPerson);
    }

    @Test
    public void personParserTestHappy() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "carla,C.,Carla,,,v,Fairy Tail,carla@fairytail.example.com,,208,,,,,,,,,,,Fairy Hills,,,Magnolia Town,Fiore,";
        Organization org = new Organization("Fairy Tail");
        Person person = personParser.parseToPerson(rawPerson);
        assertEquals("Username should match.", "carla", person.getUserName());
        assertEquals("Email should match.", "carla@fairytail.example.com", person.getEmail());
        assertEquals("Organization should match.", org, person.getOrg());
        assertEquals("Extension Number should match.", (Integer) 208, person.getExtensionNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyStringTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "";
        Person person = personParser.parseToPerson(rawPerson);
    }

    @Test
    public void onlyUserNameTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "carla,,,,,,,,,,,,,,,,,,,,,,,,,";
        Person person = personParser.parseToPerson(rawPerson);
        assertEquals("Username should match.", "carla", person.getUserName());
    }

    @Test
    public void onlyUserNameAndExtensionTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "carla,,,,,,,,,4,,,,,,,,,,,,,,,,";
        Person person = personParser.parseToPerson(rawPerson);
        assertEquals("Username should match.", "carla", person.getUserName());
        assertEquals("Extension Number should match.", (Integer) 4, person.getExtensionNumber());
    }

    @Test(expected = PersonParserException.class)
    public void extensionIsNotAnIntTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "carla,C.,Carla,,,v,Fairy Tail,carla@fairytail.example.com,,four,,,,,,,,,,,Fairy Hills,,,Magnolia Town,Fiore,";
        Person person = personParser.parseToPerson(rawPerson);
    }

    @Test
    public void userNameHasDigitFirstTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "455carla,C.,Carla,,,v,Fairy Tail,carla@fairytail.example.com,,208,,,,,,,,,,,Fairy Hills,,,Magnolia Town,Fiore,";
        Person person = personParser.parseToPerson(rawPerson);
        assertEquals("Username should match.", "455carla", person.getUserName());
    }

    @Test
    public void userNameIsOnlyDigitsTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "455,C.,Carla,,,v,Fairy Tail,carla@fairytail.example.com,,208,,,,,,,,,,,Fairy Hills,,,Magnolia Town,Fiore,";
        Person person = personParser.parseToPerson(rawPerson);
        assertEquals("Username should match.", "455", person.getUserName());
    }

    @Test
    public void fieldContainQuotesTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "carla,C.,Carla,,,v,\"Fairy\" Tail,carla@fairytail.example.com,,208,,,,,,,,,,,Fairy Hills,,,Magnolia Town,Fiore,";
        Organization org = new Organization("\"Fairy\" Tail");
        Person person = personParser.parseToPerson(rawPerson);
        assertEquals("Organization should match.", org, person.getOrg());
    }

    @Test(expected = IllegalArgumentException.class)
    public void commaAddedBeforeExtensionInFieldsTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "carla,C.,Carla,,,v,Fairy, Tail,carla@fairytail.example.com,,208,,,,,,,,,,,Fairy Hills,,,Magnolia Town,Fiore,";
        Person person = personParser.parseToPerson(rawPerson);
    }

    @Test(expected = IllegalArgumentException.class)
    public void commaAddedAfterExtensionInFieldsTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "carla,C.,Carla,,,v,Fairy Tail,carla@fairytail.example.com,,208,,06738,434,,,,test@fake.com,,,,,Fairy Hills,,,Magnolia Town,Fiore,";
        Person person = personParser.parseToPerson(rawPerson);
    }


}
