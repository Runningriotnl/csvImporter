package com.company.organization;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrganizationParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void nullInputOrganizationParserTest() {
        OrganizationParser organizationParser = new OrganizationParser();
        String rawOrganization = null;
        organizationParser.parseToOrg(rawOrganization);
    }

    @Test
    public void inputMatchesFieldsTest() {
        OrganizationParser organizationParser = new OrganizationParser();
        String rawOrganization = "X,,,,Y,,Z,,,,,,,,,,,,,,,,,,,";
        Organization organization = organizationParser.parseToOrg(rawOrganization);
        assertEquals("Organization name must match." , "X", organization.getName());
        assertEquals("Organization email must match." , "Y", organization.getEmail());
        assertEquals("Organization phone number must match." , "Z", organization.getPhoneNumber());
    }

    @Test(expected = OrganizationParserException.class)
    public void emptyStringInputTest() {
        OrganizationParser organizationParser = new OrganizationParser();
        String rawOrganization = "";
        organizationParser.parseToOrg(rawOrganization);
    }

    @Test
    public void onlyOrganizationNameTest() {
        OrganizationParser organizationParser = new OrganizationParser();
        String rawOrganzation = "X,,,,,,,,,,,,,,,,,,,,,,,";
        Organization organization = organizationParser.parseToOrg(rawOrganzation);
        assertEquals("Organization name must match." , "X", organization.getName());
        assertEquals("Organization email must match." , "", organization.getEmail());
        assertEquals("Organization phone number must match." , "", organization.getPhoneNumber());

    }
}
