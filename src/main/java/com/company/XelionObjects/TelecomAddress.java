package com.company.XelionObjects;

public class TelecomAddress {
    private String telecomType;
    private String address;

    public TelecomAddress(String telecomType, String address) {
        this.telecomType = telecomType;
        this.address = address;
    }

    @Override
    public String toString() {
        return "TelecomAddress{" +
                "telecomType='" + telecomType + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
