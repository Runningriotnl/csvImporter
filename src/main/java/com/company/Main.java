package com.company;

import com.company.organization.Organization;
import com.company.organization.OrganizationParser;
import com.company.organization.OrganizationReader;
import com.company.person.Person;
import com.company.person.PersonParser;
import com.company.person.PersonReader;
import com.company.user.User;
import com.company.user.UserParser;
import com.company.user.UserReader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    private Model model;
    private ReaderManager readerManager;
    private Writer writer;
    private SendHttpRequest request;
    private PhoneLineExtension phoneLineExtension;
    private String baseUrl = "http://10.78.40.157";

    public Main() {

        model = new Model();
        readerManager = new ReaderManager();
        request = new SendHttpRequest(baseUrl);
        writer = new Writer();


    }

    public static void main(String[] args) {
	    Main m = new Main();
	    m.execute(args[0]);
    }

    private void execute(String filePath) {
        validateAllFilesPresent(filePath);

        Reader organizationReader = new OrganizationReader(new OrganizationParser());
        model.getOrganisationList().addAll(organizationReader.readFile(filePath + "/organisations.csv"));
        request.postAddressableToServer(model.getOrganisationList());
        writer.write(model.getOrganisationList());

        Reader personReader = new PersonReader(new PersonParser(model));
        model.getPersonList().addAll(personReader.readFile(filePath + "/persons.csv"));
        request.postAddressableToServer(model.getPersonList());
        writer.write(model.getPersonList());

        Reader userReader = new UserReader(new UserParser(model));
        model.getUserList().addAll(userReader.readFile(filePath + "/users.csv"));
        request.postUserToServer(model.getUserList());
        writer.write(model.getUserList());

        PhoneLineExtension phoneLineExtension = new PhoneLineExtension(model);
        phoneLineExtension.addExtensionsToPhoneLine();
    }

    public class Model {
        private final List<Organization> organisationList = new ArrayList<>();
        private final List<Person> personList = new ArrayList<>();
        private final List<User> userList = new ArrayList<>();

        public List<Organization> getOrganisationList() {
            return organisationList;
        }

        public List<Person> getPersonList() {
            return personList;
        }

        public List<User> getUserList() { return userList; }
    }

    private void validateAllFilesPresent(String filePath) {
        Path directoryPath = Paths.get(filePath);
        if (Files.isDirectory(directoryPath)) {
            List<Path> csvPaths = new ArrayList<>();
            csvPaths.add(Paths.get(filePath + "/organisations.csv"));
            csvPaths.add(Paths.get(filePath + "/persons.csv"));
            csvPaths.add(Paths.get(filePath + "/users.csv"));
            csvPaths.add(Paths.get(filePath + "/phonelines.csv"));
            csvPaths.add(Paths.get(filePath + "/phones.csv"));
            for (Path path : csvPaths) {
                if (Files.notExists(path)) {
                    throw new ReaderException("Not all necessary files are present in given filePath.");
                }
            }
        }
    }
}