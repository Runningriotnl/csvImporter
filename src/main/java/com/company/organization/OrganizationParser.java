package com.company.organization;

import com.company.XelionObjects.TelecomAddress;

public class OrganizationParser {
    public Organization parseToOrg(String rawOrg) {
        if (rawOrg == null) {
            throw new IllegalArgumentException("rawOrg must not be null.");
        }

        String[] splitOrg = rawOrg.split(",", -1);

        if (splitOrg.length != 25) {
            throw new OrganizationParserException("Input format is wrong");
        }

        try {
            String name = validateValue(splitOrg[0]);
            String emailAddress = validateValue(splitOrg[4]);
            TelecomAddress email = new TelecomAddress("email", emailAddress);
            String phoneNumber = validateValue(splitOrg[6]);
            TelecomAddress phone = new TelecomAddress("telephone", phoneNumber);

            Organization parsedOrganization = new Organization(name, email, phone);

            return parsedOrganization;

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new OrganizationParserException("Could not create Organization from rawOrg.", e);
        } catch (IllegalArgumentException e) {
            throw new OrganizationParserException("Could not create Organization from rawOrg.", e);
        }

    }

    private String validateValue(String csvInput) {
        return ("".equals(csvInput)) ? null : csvInput;
    }
}
