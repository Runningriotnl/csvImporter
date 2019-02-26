package com.company.organization;

import com.company.XelionObjects.TelecomAddress;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrganizationParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void nullInputOrganizationParserTest() {
        OrganizationParser organizationParser = new OrganizationParser();
        String rawOrganization = null;
        organizationParser.parse(rawOrganization);
    }

    @Test
    public void inputMatchesFieldsTest() {
        OrganizationParser organizationParser = new OrganizationParser();
        String rawOrganization = "X,,,,Y,,Z,,,,,,,,,,,,,,,,,,";
        Organization organization = organizationParser.parse(rawOrganization);
        assertEquals("Organization name must match." , "X", organization.getName());
        assertEquals("Organization email must match." , "Y", getOrgEmail(organization));
        assertEquals("Organization phone number must match." , "Z", getOrgPhoneNumber(organization));
    }

    @Test(expected = OrganizationParserException.class)
    public void emptyStringInputTest() {
        OrganizationParser organizationParser = new OrganizationParser();
        String rawOrganization = "";
        organizationParser.parse(rawOrganization);
    }

    @Test
    public void onlyOrganizationNameTest() {
        OrganizationParser organizationParser = new OrganizationParser();
        String rawOrganization = "X,,,,,,,,,,,,,,,,,,,,,,,,";
        Organization organization = organizationParser.parse(rawOrganization);
        assertEquals("Organization name must match." , "X", organization.getName());
        assertEquals("Organization email must match." , null, getOrgEmail(organization));
        assertEquals("Organization phone number must match." , null, getOrgPhoneNumber(organization));
    }

    @Test(expected = OrganizationParserException.class)
    public void extraCommaInCSVTest() {
        OrganizationParser organizationParser = new OrganizationParser();
        String rawOrganization = ",,,,,,,,,,,,,,,,,,,,,,,,,,";
        Organization organization = organizationParser.parse(rawOrganization);
    }

    public String getOrgEmail(Organization org) {
        List<TelecomAddress> orgTelecomAddresses = org.getTelecomAddresses();
        for(TelecomAddress telecomAddress : orgTelecomAddresses) {
            if("email".equals(telecomAddress.getTelecomType())) {
                return telecomAddress.getAddress();
            }
        }
        return null;
    }

    public String getOrgPhoneNumber(Organization org) {
        List<TelecomAddress> orgTelecomAddresses = org.getTelecomAddresses();
        for(TelecomAddress telecomAddress : orgTelecomAddresses) {
            if("telephone".equals(telecomAddress.getTelecomType())) {
                return telecomAddress.getAddress();
            }
        }
        return null;
    }
}
