package com.company;

import com.company.organization.Organization;
import com.company.organization.OrganizationReader;
import com.company.person.Person;
import com.company.person.PersonReader;

import java.util.Collections;
import java.util.List;

public class ReaderManager {
    private PersonReader personReader;
    private OrganizationReader organizationReader;

    private Writer w = new Writer();

    public void chooseReader(String inputFileName) {
        String cleanFilename = stripPath(inputFileName);

        switch (cleanFilename) {
            case "persons.csv":
                System.out.println("File is a csv containing persons.");
                personReader = new PersonReader();
                List<Person> personList = personReader.readFile(inputFileName);
                System.out.println("Sorting the list based on first names.");
                Collections.sort(personList);
                w.writePerson(personList);
                break;
            case "organisations.csv":
                System.out.println("File is a csv containing organizations.");
                organizationReader = new OrganizationReader();
                List<Organization> orgList = organizationReader.readFile(inputFileName);
                w.writeOrganization(orgList);
                break;
            case "phones.csv":
                System.out.println("No implementation for phones yet.");
                break;
            case "users.csv":
                System.out.println("No implementation for users yet.");
                break;
            case "phonelines.csv":
                System.out.println("No implementation for phonelines yet.");
                break;
            default:
                throw new IllegalArgumentException("Filename must be: persons.csv, organisations.csv, phones.csv, users.csv or phonelines.csv");
        }
    }

    private String stripPath(String inputFileName) {
        String[] completePath = inputFileName.split("/");
        String cleanFilename = completePath[(completePath.length -1)];

        return cleanFilename;
    }
}
