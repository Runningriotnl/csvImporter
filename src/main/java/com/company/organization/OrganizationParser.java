package com.company.organization;

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
            String name = splitOrg[0];
            String email = splitOrg[4];
            String phoneNumber = splitOrg[6];

            Organization parsedOrganization = new Organization(name, email, phoneNumber);

            return parsedOrganization;

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new OrganizationParserException("Could not create Organization from rawOrg.", e);
        } catch (IllegalArgumentException e) {
            throw new OrganizationParserException("Could not create Organization from rawOrg.", e);
        }

    }
}
