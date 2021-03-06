package com.company.user;

import com.company.Main;
import com.company.Parser;
import com.company.person.Person;

import java.util.Objects;

public class UserParser implements Parser<User> {
    private Main.Model model;

    public UserParser(Main.Model model) {
        this.model = model;
    }

    public User parse(String string) {

        if (string == null) {
            throw new IllegalArgumentException("string must not be null.");
        }

        String[] splitUser = string.split(",", -1);

        if (splitUser.length != 12) {
            throw new UserParserException("Input format is wrong");
        }

        try {
            String userName = validateValue(splitUser[0]);
            String password = validateValue(splitUser[1]);
            String extensionNumber = validateValue(splitUser[8]);
            Person person = model.getPersonList().stream().filter(existingPerson -> Objects.equals(userName, existingPerson.getUserName())).findFirst().orElseThrow(() -> new IllegalArgumentException("Person was not found."));

            User parsedUser = new User(userName, password, extensionNumber, person);

            return parsedUser;

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new UserParserException("Could not create user from string.", e);
        } catch (IllegalArgumentException e) {
            throw new UserParserException("Could not create user from string.", e);
        }

    }

    private String validateValue(String csvInput) {
        return ("".equals(csvInput)) ? null : csvInput;
    }
}

