package com.company.person;

import com.company.Parser;
import com.company.Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonReader implements Reader {

    private Parser<Person> parser;

    public PersonReader(Parser<Person> parser) {
        this.parser = parser;
    }

    public List<Person> readFile(String fileName) {
        List<Person> personList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // this will read the first line
            String line;
            int counter = 1;
            while ((line = br.readLine()) != null) {
                try {
                    personList.add(parser.parse(line));
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
