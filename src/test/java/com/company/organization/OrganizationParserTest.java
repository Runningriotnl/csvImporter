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
        String rawOrganization = "Blue Pegasus,,,,guild@bluepegasus.example.com,,152511411,,,,,,,,,,,,,Blue Pegasus Guild,,,,,";
        Organization organization = organizationParser.parseToOrg(rawOrganization);
        assertEquals("Organization name must match." , "Blue Pegasus", organization.getName());
        assertEquals("Organization email must match." , "guild@bluepegasus.example.com", organization.getEmail());
        assertEquals("Organization phone number must match." , "152511411", organization.getPhoneNumber());
    }

    @Test
    public void emptyStringInputTest() {
        OrganizationParser organizationParser = new OrganizationParser();
    }
}
