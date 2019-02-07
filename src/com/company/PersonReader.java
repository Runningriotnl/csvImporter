package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonReader {

    private PersonParser personParser = new PersonParser();

    public PersonReader() {

    }

    public List<Person> readFile(String fileName) {
        List<Person> personList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // this will read the first line
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    personList.add(personParser.parseToPerson(line));
                } catch (PersonParserException e) {
                    System.out.println("Skipped");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personList;
    }

}


