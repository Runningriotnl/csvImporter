package com.company.person;

import com.company.organization.Organization;

public class PersonParser {

    public Person parseToPerson(String rawPerson) {
        if (rawPerson == null) {
            throw new IllegalArgumentException("rawPerson must not be null.");
        }

        String[] splitPerson = rawPerson.split(",", -1);

        if (splitPerson.length != 26) {
            throw new PersonParserException("Input format is wrong");
        }

        try {

            String userName = splitPerson[0];
            String firstName = splitPerson[2];
            String lastName = splitPerson[4];
            Organization org = new Organization(splitPerson[6]);
            String email = splitPerson[7];
            Integer extensionNumber = (splitPerson[9] == null || "".equals(splitPerson[9])) ? null : Integer.parseInt(splitPerson[9]);
            String mobileNumber = splitPerson[11];

            Person parsedPerson = new Person(userName, firstName, lastName, org, email, extensionNumber, mobileNumber);

            parsedPerson.setPrivateEmail(splitPerson[15]);
            parsedPerson.setPrivateMobileNumber(splitPerson[18]);

            return parsedPerson;

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new PersonParserException("Could not create person from rawPerson.", e);
        } catch (IllegalArgumentException e) {
            throw new PersonParserException("Could not create person from rawPerson.", e);
        }

    }

}
