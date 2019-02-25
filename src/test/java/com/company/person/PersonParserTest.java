package com.company.person;

import com.company.Main;
import com.company.XelionObjects.Employee;
import com.company.XelionObjects.TelecomAddress;
import com.company.organization.Organization;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PersonParserTest {

    private Main.Model model;

    @Before
    public void setUp() throws Exception {
        Main mainTest = new Main();
        model = mainTest.new Model();
    }

    @Test(expected = IllegalArgumentException.class)
    public void personParserTest() {
        PersonParser personParser = new PersonParser(model);
        String rawPerson = null;
        personParser.parseToPerson(rawPerson);
    }

    @Test
    public void inputMatchesFieldsTest() {
        PersonParser personParser = new PersonParser(model);
        String rawPerson = "X,,,,,,Y,Z,,1,,,,,,,,,,,,,,,,";
        Organization org = new Organization("Y");
        model.getOrganisationList().add(org);
        Person person = personParser.parseToPerson(rawPerson);
        assertEquals("Username should match.", "X", person.getUserName());
        assertEquals("Email should match.", "Z", getEmploymentEmail(person));
        assertEquals("PhoneNumber should match.", "1", getEmploymentPhoneNumber(person));
        assertEquals("Organization should match.", org, getEmploymentOrganisation(person));
    }

    @Test(expected = PersonParserException.class)
    public void emptyStringTest() {
        PersonParser personParser = new PersonParser(model);
        String rawPerson = "";
        Person person = personParser.parseToPerson(rawPerson);
    }

    @Test(expected = PersonParserException.class)
    public void onlyUserNameTest() {
        PersonParser personParser = new PersonParser(model);
        String rawPerson ="X,,,,,,,,,,,,,,,,,,,,,,,,,";
        Person person = personParser.parseToPerson(rawPerson);
        assertEquals("Username should match.", "X", person.getUserName());
    }

    @Test
    public void onlyUserNameOrganisationAndExtensionTest() {
        PersonParser personParser = new PersonParser(model);
        String rawPerson = "X,,,,,,Y,,,1,,,,,,,,,,,,,,,,";
        Organization org = new Organization("Y");
        model.getOrganisationList().add(org);
        Person person = personParser.parseToPerson(rawPerson);
        assertEquals("Username should match.", "X", person.getUserName());
        assertEquals("Employment Phone Number should match.", "1", getEmploymentPhoneNumber(person));
    }

    @Test(expected = PersonParserException.class)
    public void extensionIsNotAnIntTest() {
        PersonParser personParser = new PersonParser(model);
        String rawPerson = "X,,,,,,Y,Z,,A,,,,,,,,,,,,,,,,";
        Person person = personParser.parseToPerson(rawPerson);
    }

    @Test
    public void userNameHasDigitFirstTest() {
        PersonParser personParser = new PersonParser(model);
        String rawPerson = "1X,,,,,,Y,,,,,,,,,,,,,,,,,,,";
        Organization org = new Organization("Y");
        model.getOrganisationList().add(org);
        Person person = personParser.parseToPerson(rawPerson);
        assertEquals("Username should match.", "1X", person.getUserName());
    }

    @Test
    public void userNameIsOnlyDigitsTest() {
        PersonParser personParser = new PersonParser(model);
        String rawPerson = "1,,,,,,Y,,,,,,,,,,,,,,,,,,,";
        Organization org = new Organization("Y");
        model.getOrganisationList().add(org);
        Person person = personParser.parseToPerson(rawPerson);
        assertEquals("Username should match.", "1", person.getUserName());
    }

    @Test
    public void fieldContainQuotesTest() {
        PersonParser personParser = new PersonParser(model);
        String rawPerson = "X,,,,,,\"Y\",Z,,1,,,,,,,,,,,,,,,,";
        Organization org = new Organization("\"Y\"");
        model.getOrganisationList().add(org);
        Person person = personParser.parseToPerson(rawPerson);
        assertEquals("Organization should match.", org, getEmploymentOrganisation(person));
    }

    @Test(expected = PersonParserException.class)
    public void commaAddedBeforeExtensionInFieldsTest() {
        PersonParser personParser = new PersonParser(model);
        String rawPerson = "X,,,,,,,Y,Z,,1,,,,,,,,,,,,,,,,";
        Person person = personParser.parseToPerson(rawPerson);
    }

    @Test(expected = PersonParserException.class)
    public void commaAddedAfterExtensionInFieldsTest() {
        PersonParser personParser = new PersonParser(model);
        String rawPerson = "X,,,,,,Y,Z,,1,,,,,,,,,,,,,,,,,";
        Person person = personParser.parseToPerson(rawPerson);
    }

    public String getEmploymentEmail(Person person) {
        List<Employee> employments = person.getEmployments();
        for(Employee employment : employments) {
            for(TelecomAddress telecomAddress : employment.getTelecomAddresses()) {
                if("email".equals(telecomAddress.getTelecomType())) {
                    return telecomAddress.getAddress();
                }
            }
        }
        return null;
    }

    public String getEmploymentPhoneNumber(Person person) {
        List<Employee> employments = person.getEmployments();
        for(Employee employment : employments) {
            for(TelecomAddress telecomAddress : employment.getTelecomAddresses()) {
                if("telephone".equals(telecomAddress.getTelecomType())) {
                    return telecomAddress.getAddress();
                }
            }
        }
        return null;
    }

    public Organization getEmploymentOrganisation(Person person) {
        List<Employee> employments = person.getEmployments();
        for(Employee employement : employments) {
            return employement.getOrganisation();
        }
        return null;
    }

}
