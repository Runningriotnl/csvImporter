package com.company.organization;

import com.company.Addressable;
import com.company.XelionObjects.TelecomAddress;

import java.util.ArrayList;
import java.util.List;

public class Organization extends Addressable {
    private String name;
    private List<TelecomAddress> telecomAddresses = new ArrayList<>();

    public Organization(String name) {
        this(name, null, null);
    }

    public Organization(String name, TelecomAddress email, TelecomAddress phoneNumber) {
        super("Organisation");

        if(name == null) {
            throw new IllegalArgumentException("Organization name is required");
        }

        this.name = name;
        telecomAddresses.add(email);
        telecomAddresses.add(phoneNumber);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "name='" + name + '\'' +
                ", telecomAddresses=" + telecomAddresses +
                "} " + super.toString();
    }
}
