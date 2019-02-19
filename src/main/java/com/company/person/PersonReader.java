package com.company.person;

import com.company.Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonReader implements Reader {

    private PersonParser personParser;

    public PersonReader(PersonParser personParser) {
        this.personParser = personParser;
    }

    public List<Person> readFile(String fileName) {
        List<Person> personList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // this will read the first line
            String line;
            int counter = 1;
            while ((line = br.readLine()) != null) {
                try {
                    personList.add(personParser.parseToPerson(line));
                } catch (PersonParserException e) {
                    System.out.println("Skipped entry on line " + counter);
                    e.printStackTrace();
                }
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personList;
    }

}
