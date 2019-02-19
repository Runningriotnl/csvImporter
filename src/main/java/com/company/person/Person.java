package com.company.person;

import com.company.Addressable;
import com.company.XelionObjects.Employee;

import java.util.List;
import java.util.Objects;

public class Person extends Addressable {
    private String userName;
    private String givenName;
    private String familyName;
    private List<Employee> employments;
    private String gender;
//    private String email;
//    private Integer extensionNumber;
//    private String mobileNumber;
//    private String privateEmail;
//    private String privateMobileNumber;



    public Person(String userName, String givenName, String familyName, List<Employee> employments, String gender) {
        super("Person");

        if(userName == null) {
            throw new IllegalArgumentException("Username is required");
        }

        this.userName = userName;
        this.givenName = givenName;
        this.familyName = familyName;
        this.employments = employments;
        this.gender = gender;
//        this.email = email;
//        this.extensionNumber = extensionNumber;
//        this.mobileNumber = mobileNumber;

    }

//    public Person setPrivateEmail(String privateEmail) {
//        this.privateEmail = privateEmail;
//        return this;
//    }
//
//    public Person setPrivateMobileNumber(String privateMobileNumber) {
//        this.privateMobileNumber = privateMobileNumber;
//        return this;
//    }

    public String getUserName() {
        return userName;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public List<Employee> getEmployments() {
        return employments;
    }
    public String getGender() {
        return gender;
    }


    //    public String getEmail() {
//        return email;
//    }
//
//    public Integer getExtensionNumber() {
//        return extensionNumber;
//    }
//
//    public String getMobileNumber() {
//        return mobileNumber;
//    }
//
//    public String getPrivateEmail() {
//        return privateEmail;
//    }
//
//    public String getPrivateMobileNumber() {
//        return privateMobileNumber;
//    }

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
                ", givenName='" + givenName + '\'' +
                ", familyName='" + familyName + '\'' +
               // ", org=" + org +
//                ", email='" + email + '\'' +
//                ", extensionNumber=" + extensionNumber +
//                ", mobileNumber='" + mobileNumber + '\'' +
//                ", privateEmail='" + privateEmail + '\'' +
//                ", privateMobileNumber='" + privateMobileNumber + '\'' +
                '}';
    }

}
