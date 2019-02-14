package com.company.organization;

import com.company.Addressable;

import java.util.Objects;

public class Organization extends Addressable {
    private String name;
    private String email;
    private String phoneNumber;

    public Organization(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Organization name is required");
        }

        this.name = name;
    }

    public Organization(String name, String email, String phoneNumber) {
        if(name == null) {
            throw new IllegalArgumentException("Organization name is required");
        }

        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }

    public String toJsonObject() {
        String jsonObjectString = "{\"objectType\":\"Organisation\", \"commonName\":\"" + getName() + "\"}";
        return jsonObjectString;
    }

}
