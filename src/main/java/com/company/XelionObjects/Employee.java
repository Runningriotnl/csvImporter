package com.company.XelionObjects;

import com.company.organization.Organization;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private final Organization organisation;
    private final List<TelecomAddress> telecomAddresses;

    public Employee(Organization organisation, List<TelecomAddress> telecomAddresses) {
        this.organisation = organisation;
        this.telecomAddresses = telecomAddresses;
    }

    public Organization getOrganisation() {
        return organisation;
    }

    public List<TelecomAddress> getTelecomAddresses() {
        return telecomAddresses;
    }
}
