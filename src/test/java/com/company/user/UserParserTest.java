package com.company.user;

import com.company.Main;
import com.company.organization.Organization;
import com.company.person.Person;
import com.company.person.PersonParser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserParserTest {

    private Main.Model model;
    private Person person;

    @Before
    public void setUp() throws Exception {
        Main mainTest = new Main();
        model = mainTest.new Model();
        PersonParser personParser = new PersonParser(model);
        String rawPerson = "X,,,,,,Y,Z,,1,,,,,,,,,,,,,,,,";
        Organization org = new Organization("Y");
        model.getOrganisationList().add(org);
        person = personParser.parse(rawPerson);
        model.getPersonList().add(person);
    }

    @Test
    public void inputMatchesFieldsTest() {
        UserParser userParser = new UserParser(model);
        String rawUser = "X,Y,,,,,,,1,,,";
        User user = userParser.parse(rawUser);
        assertEquals("Username should match.", "X", user.getUserName());
        assertEquals("Password should match.", "Y", user.getPassword());
        assertEquals("Extensions Number should match.", "1", user.getExtensionNumber());
    }

    @Test
    public void userNameShouldHaveMatchingPersonTest() {
        UserParser userParser = new UserParser(model);
        String rawUser = "X,Y,,,,,,,1,,,";
        User user = userParser.parse(rawUser);
        assertEquals("Person object should match.", person, user.getPerson());
    }

    @Test(expected = UserParserException.class)
    public void cannotMakeUserWrongNumberOfFieldsTest() {
        UserParser userParser = new UserParser(model);
        String rawUser = "X,Y,,,,,,,1,,,,";
        User user = userParser.parse(rawUser);
    }

    @Test(expected = UserParserException.class)
    public void userNameCannotBeNullTest() {
        UserParser userParser = new UserParser(model);
        String rawUser = ",Y,,,,,,,1,,,,";
        User user = userParser.parse(rawUser);
    }
}
