package com.company;

import java.util.Objects;

public class Person implements Comparable<Person>{
    private String userName;
    private String firstName;
    private String lastName;
    private Organization org;
    private String email;
    private int extensionNumber;
    private String mobileNumber;
    private String privateEmail;
    private String privateMobileNumber;

    public Person(String userName, String firstName, String lastName, Organization org, String email, int extensionNumber, String mobileNumber) {
        if(userName == null) {
            throw new IllegalArgumentException("Username is required");
        }

        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.org = org;
        this.email = email;
        this.extensionNumber = extensionNumber;
        this.mobileNumber = mobileNumber;

    }

    public void setPrivateEmail(String privateEmail) {
        this.privateEmail = privateEmail;
    }

    public void setPrivateMobileNumber(String privateMobileNumber) {
        this.privateMobileNumber = privateMobileNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Organization getOrg() {
        return org;
    }

    public String getEmail() {
        return email;
    }

    public int getExtensionNumber() {
        return extensionNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getPrivateEmail() {
        return privateEmail;
    }

    public String getPrivateMobileNumber() {
        return privateMobileNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return userName.equals(person.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

    @Override
    public String toString() {
        return "Person{" +
                "userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", org=" + org +
                ", email='" + email + '\'' +
                ", extensionNumber=" + extensionNumber +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", privateEmail='" + privateEmail + '\'' +
                ", privateMobileNumber='" + privateMobileNumber + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return this.getFirstName().compareTo(o.getFirstName());
    }
}
