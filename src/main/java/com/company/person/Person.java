package com.company.person;

import com.company.Addressable;
import com.company.organization.Organization;

import java.util.Objects;

public class Person extends Addressable implements Comparable<Person>{
    private String userName;
    private String firstName;
    private String lastName;
    private Organization org;
    private String email;
    private Integer extensionNumber;
    private String mobileNumber;
    private String privateEmail;
    private String privateMobileNumber;

    public Person(String userName, String firstName, String lastName, Organization org, String email, Integer extensionNumber, String mobileNumber) {
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

    public Person setPrivateEmail(String privateEmail) {
        this.privateEmail = privateEmail;
        return this;
    }

    public Person setPrivateMobileNumber(String privateMobileNumber) {
        this.privateMobileNumber = privateMobileNumber;
        return this;
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

    public Integer getExtensionNumber() {
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
    public String getName() {
        String personName;
        if(!getFirstName().equals("") && !getLastName().equals("")){
            personName = getFirstName()  + " " + getLastName();
        } else {
            //If on of the names is provided the format will be okay, if none of the names is provided will become ""
            personName = getFirstName() + getLastName();
        }
        return personName;
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


    public String toJsonObject(Person person) {
        String jsonObjectString;
        if(!"".equals(person.getOrg().getName())){
             jsonObjectString = "{\"objectType\":\"Person\", " +
                 "\"commonName\":\"" + person.getLastName() + "\"," +
                 "\"givenName\":\"" + person.getFirstName() + "\"," +
                 "\"employments\": [{" +
                     "\"commonName\":\"" + person.getOrg().getName() + "\"," +
                     "\"organisation\": {" +
                         "\"commonName\":\"" + person.getOrg().getName() + "\"," +
                         "\"objectType\":\"Organisation\"}}" +
             "]}";

        } else {
            jsonObjectString = "{\"objectType\":\"Person\", " +
                "\"commonName\":\"" + person.getLastName() + "\"," +
                    "\"givenName\":\"" + person.getFirstName() + "\"}";
        }
        return jsonObjectString;
    }


}
