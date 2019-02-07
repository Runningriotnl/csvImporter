package com.company;

import com.company.organization.Organization;
import com.company.organization.OrganizationReader;
import com.company.person.Person;
import com.company.person.PersonReader;

public class ReaderManager {

    public Reader chooseReader(String inputFileName) {
        String cleanFilename = stripPath(inputFileName);
        Reader reader;

        switch (cleanFilename) {
            case "persons.csv":
                System.out.println("File is a csv containing persons.");
                reader = new PersonReader();
                break;
            case "organisations.csv":
                System.out.println("File is a csv containing organizations.");
                reader = new OrganizationReader();
                break;
            case "phones.csv":
                throw new UnsupportedOperationException("No implementation for phones yet.");
            case "users.csv":
                throw new UnsupportedOperationException("No implementation for users yet.");
            case "phonelines.csv":
                throw new UnsupportedOperationException("No implementation for phonelines yet.");
            default:
                throw new IllegalArgumentException("Filename must be: persons.csv, organisations.csv, phones.csv, users.csv or phonelines.csv");
        }
        return reader;
    }

    private String stripPath(String inputFileName) {
        String[] completePath = inputFileName.split("/");
        String cleanFilename = completePath[(completePath.length -1)];

        return cleanFilename;
    }
}
