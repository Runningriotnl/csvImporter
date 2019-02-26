package com.company.user;

import com.company.Parser;
import com.company.Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserReader implements Reader{

    private Parser<User> parser;

    public UserReader(Parser<User> parser) {
        this.parser = parser;
    }

    public List<User> readFile(String fileName) {
        List<User> userList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // this will read the first line
            String line;
            int counter = 1;
            while ((line = br.readLine()) != null) {
                try {
                    userList.add(parser.parse(line));
                } catch (UserParserException e) {
                    System.out.println("Skipped entry on line " + counter);
                    e.printStackTrace();
                }
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }

}


