package com.company;

import com.company.organization.Organization;
import com.company.organization.OrganizationParser;
import com.company.person.Person;
import com.company.person.PersonParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class WriterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void printCorrectPersonFieldsTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "X,,A,,B,,Y,Z,,1,,,,,,,,,,,,,,,,";
        Person person = personParser.parseToPerson(rawPerson);
        Writer writer = new Writer();
        writer.writePerson(person);
        assertEquals("Output should match input","Person with name A B has username: X who works for Y and gets extension number: 1\r\n", outContent.toString());
    }

    @Test
    public void missedFieldsAreReplacedByNotProvidedForPersonTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "X,,,,,,,,,,,,,,,,,,,,,,,,,";
        Person person = personParser.parseToPerson(rawPerson);
        Writer writer = new Writer();
        writer.writePerson(person);
        assertEquals("Output should match input","Person with name not provided has username: X who works for not provided and gets extension number: not provided\r\n", outContent.toString());
    }

    @Test
    public void printWorksWithQuotesForPersonTest() {
        PersonParser personParser = new PersonParser();
        String rawPerson = "\"X\",,A,,B,,Y,Z,,1,,,,,,,,,,,,,,,,";
        Person person = personParser.parseToPerson(rawPerson);
        Writer writer = new Writer();
        writer.writePerson(person);
        assertEquals("Output should match input","Person with name A B has username: \"X\" who works for Y and gets extension number: 1\r\n", outContent.toString());
    }

    @Test
    public void printCorrectOrganizationFieldsTest() {
        OrganizationParser organizationParser = new OrganizationParser();
        String rawOrg = "X,,,,Y,,Z,,,,,,,,,,,,,,,,,,,";
        Organization organization = organizationParser.parseToOrg(rawOrg);
        Writer writer = new Writer();
        writer.writeOrganization(organization);
        assertEquals("Output should match input", "Organization X is reachable on email at: Y and through phone by: Z.\r\n", outContent.toString());
    }

    @Test
    public void missedFieldsAreReplacedByNotProvidedForOrganizationTest() {
        OrganizationParser organizationParser = new OrganizationParser();
        String rawOrg = ",,,,,,,,,,,,,,,,,,,,,,,,,";
        Organization organization = organizationParser.parseToOrg(rawOrg);
        Writer writer = new Writer();
        writer.writeOrganization(organization);
        assertEquals("Output should match input", "Organization not provided is reachable on email at: not provided and through phone by: not provided.\r\n", outContent.toString());
    }

}
