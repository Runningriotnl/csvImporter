package com.company.person;

import com.company.Main;
import com.company.Parser;
import com.company.XelionObjects.DeliveryAddress;
import com.company.XelionObjects.Employee;
import com.company.XelionObjects.TelecomAddress;
import com.company.organization.Organization;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PersonParser implements Parser<Person> {

    private Main.Model model;

    public PersonParser(Main.Model model) {
        this.model = model;
    }

    public Person parse(String string) {

        if (string == null) {
            throw new IllegalArgumentException("string must not be null.");
        }

        String[] splitPerson = string.split(",", -1);

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
            String street = validateValue(splitPerson[20]);
            String streetNumber = validateValue(splitPerson[21]);
            String zipCode = validateValue(splitPerson[22]);
            String city = validateValue(splitPerson[23]);
            String country = validateValue(splitPerson[24]);
            String state = validateValue(splitPerson[25]);
            DeliveryAddress deliveryAddress = new DeliveryAddress(street, streetNumber, zipCode, city, country, state);
            List<DeliveryAddress> addresses = new ArrayList<>();
            addresses.add(deliveryAddress);
            String gender = translateGender(validateValue(splitPerson[5]));
            String emailAddress = validateValue(splitPerson[15]);
            TelecomAddress email = new TelecomAddress("email", emailAddress);
            String phoneNumber = validateValue(splitPerson[16]);
            TelecomAddress phone = new TelecomAddress("telephone", phoneNumber);
            Person parsedPerson = new Person(userName, firstName, lastName, employments, addresses, gender, email, phone);

            return parsedPerson;

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new PersonParserException("Could not create person from string.", e);
        } catch (IllegalArgumentException e) {
            throw new PersonParserException("Could not create person from string.", e);
        }

    }

    private String validateValue(String csvInput) {
        return ("".equals(csvInput)) ? null : csvInput;
    }

    private String translateGender(String gender) {
        if(gender != null) {
            switch (gender) {
                case "m":
                    return "Male";
                case "v":
                    return "Female";
                default:
                    throw new IllegalArgumentException("Unknown gender '" + gender + "'.");
            }
        } else {
            return null;
        }
    }

}
