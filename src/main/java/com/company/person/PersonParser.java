package com.company.person;

import com.company.Main;
import com.company.XelionObjects.Employee;
import com.company.XelionObjects.TelecomAddress;
import com.company.organization.Organization;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PersonParser {

    private Main.Model model;

    public PersonParser(Main.Model model) {
        this.model = model;
    }

    public Person parseToPerson(String rawPerson) {

        if (rawPerson == null) {
            throw new IllegalArgumentException("rawPerson must not be null.");
        }

        String[] splitPerson = rawPerson.split(",", -1);

        if (splitPerson.length != 26) {
            throw new PersonParserException("Input format is wrong");
        }

        try {

            String userName = validateValue(splitPerson[0]);
            String firstName = validateValue(splitPerson[2]);
            String lastName = validateValue(splitPerson[4]);
            String organisationName = validateValue(splitPerson[6]);
            Organization organization = model.getOrganisationList().stream().filter(organisation -> Objects.equals(organisationName, organisation.getName())).findFirst().orElseThrow(() -> new IllegalArgumentException("Organisation not found."));
            List<TelecomAddress> telecomAddresses = new ArrayList<>();
            String organisationEmailAddress = validateValue(splitPerson[7]);
            TelecomAddress organisationEmail = new TelecomAddress("email", organisationEmailAddress);
            String organisationPhoneNumber = validateValue(splitPerson[9]);
            TelecomAddress organisationPhone = new TelecomAddress("telephone", organisationPhoneNumber);
            telecomAddresses.add(organisationEmail);
            telecomAddresses.add(organisationPhone);
            List<Employee> employments = new ArrayList<>();
            employments.add(new Employee(organization, telecomAddresses));
            String gender = translateGender(validateValue(splitPerson[5]));
            //TODO figure out how to validate and parse this value
            //Integer extensionNumber = (splitPerson[9] == null || "".equals(splitPerson[9])) ? null : Integer.parseInt(splitPerson[9]);

            Person parsedPerson = new Person(userName, firstName, lastName, employments, gender);

            return parsedPerson;

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new PersonParserException("Could not create person from rawPerson.", e);
        } catch (IllegalArgumentException e) {
            throw new PersonParserException("Could not create person from rawPerson.", e);
        }

    }

    private String validateValue(String csvInput) {
        return ("".equals(csvInput)) ? null : csvInput;
    }

    private String translateGender(String gender) {
        return ("m".equals(gender)) ? "Male" : "Female";
    }

}
