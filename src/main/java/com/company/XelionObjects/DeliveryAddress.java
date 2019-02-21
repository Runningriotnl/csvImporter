package com.company.XelionObjects;

public class DeliveryAddress {
    private String street;
    private String streetNumber;
    private String zipCode;
    private String city;
    private String state;
    private String country;
    private String objectType;

    public DeliveryAddress(String street, String streetNumber, String zipCode, String city, String country, String state) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
        this.country = country;
        this.objectType = "DeliveryAddress";
    }
}
