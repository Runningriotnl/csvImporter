package com.company.user;

import com.company.Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserReader implements Reader{

    private UserParser userParser;

    public UserReader(UserParser userParser) {
        this.userParser = userParser;
    }

    public List<User> readFile(String fileName) {
        List<User> userList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine(); // this will read the first line
            String line;
            int counter = 1;
            while ((line = br.readLine()) != null) {
                try {
                    userList.add(userParser.parseToUser(line));
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


