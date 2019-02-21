package com.company.organization;

import com.company.Addressable;
import com.company.XelionObjects.DeliveryAddress;
import com.company.XelionObjects.TelecomAddress;

import java.util.ArrayList;
import java.util.List;

public class Organization extends Addressable {
    private String name;
    private List<TelecomAddress> telecomAddresses = new ArrayList<>();
    private List<DeliveryAddress> addresses;

    public Organization(String name) {
        this(name, null, null, null);
    }

    public Organization(String name, TelecomAddress email, TelecomAddress phoneNumber, List<DeliveryAddress> addresses) {
        super("Organisation");

        if(name == null) {
            throw new IllegalArgumentException("Organization name is required");
        }

        this.name = name;
        telecomAddresses.add(email);
        telecomAddresses.add(phoneNumber);
        this.addresses = addresses;
    }

    public String getName() {
        return name;
    }

}
