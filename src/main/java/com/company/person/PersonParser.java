package com.company.person;

import com.company.organization.Organization;

public class PersonParser {

    public Person parseToPerson(String rawPerson) {
        if (rawPerson == null) {
            throw new IllegalArgumentException("rawPerson must not be null.");
        }

        String[] splitPerson = rawPerson.split(",", -1);

        try {

            String userName = splitPerson[0];
            String firstName = splitPerson[2];
            String lastName = splitPerson[4];
            Organization org = new Organization(splitPerson[6]);
            String email = splitPerson[7];
            Integer extensionNumber;
            if(splitPerson[9] == null || splitPerson[9].equals("")){
                extensionNumber = null;
            }
            else {
                extensionNumber = Integer.parseInt(splitPerson[9]);
            }
            String mobileNumber = splitPerson[11];

            Person parsedPerson = new Person(userName, firstName, lastName, org, email, extensionNumber, mobileNumber);

            if (splitPerson.length > 15) {
                parsedPerson.setPrivateEmail(splitPerson[15]);
            }
            if (splitPerson.length > 18) {
                parsedPerson.setPrivateMobileNumber(splitPerson[18]);
            }

            return parsedPerson;

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new PersonParserException("Could not create person from rawPerson.", e);
        } catch (IllegalArgumentException e) {
            throw new PersonParserException("Could not create person from rawPerson.", e);
        }

    }

}
